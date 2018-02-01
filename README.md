# spring-boot-jackson-perf
Application to showcase how misuse of object creation for fasterxml/jackson ObjectMapper can hamper the performance. Streamed large result set from MySQL using Java 8 and then creating a json string using new ObjectMapper, Static ObjectMapper and Autowired ObjectMapper to show the performance results.

## Getting Started

Git clone the project on your local machine and add it to your workspace

### Prerequisites

For runnning this, you will need
- Java 1.8
- Gradle support - In Eclipse editor, goto help -> eclipse marketplace -> search for buildship (buildship gradle integration) and install it.

## Brief
I have loaded MySQL database with half a million records('500, 000') of **UserDetail** model. You can download the sample data from [Sample-SQL-File-500000rows.sql](http://www.sample-videos.com/sql/Sample-SQL-File-500000rows.sql). I have also added schema **[create.sql]** and records file **[Sample-SQL-File-500000-Rows.csv]** inside [src/main/resources] folder <br/>
Then I have added three methods in **UserController**, one for writing json with new ObjectMapper (writeDataAsJsonUsingNewObjectMapper), Other for writing json with static ObjectMapper (writeDataAsJsonUsingStaticObjectMapper) and last for writing json with Autowire ObjectMapper (writeDataAsJsonUsingAutowireObjectMapper)

## Running the app
For running the app,
- Open **application.properties** file and update "spring.datasource.*" properties as per your configurations.
- Once, changes are done in **application.properties**, open **"Runner.Java"** file and select run/debug.
- If app starts successfully, hit. (I did some sort of perf monitoring (threads used 10), attaching perf results for both requests.)<br/><br/><br/>
**GET** http://localhost:9091/api/user/stream/new
<br/><br/><br/>
1. This shows stats for CPU, Heap, Classes and Threads when using New ObjectMapper() <br/>
![alt text](https://github.com/greyseal/spring-boot-jackson-perf/blob/master/src/main/resources/with_new_mapper_heap_cpu.png "Using New ObjectMapper Monitor")
<br/><br/><br/>
2. This shows stats for Visual GC when using New ObjectMapper()<br/>
![alt text](https://github.com/greyseal/spring-boot-jackson-perf/blob/master/src/main/resources/with_new_mapper_gc.png "Using New ObjectMapper GC")
<br/><br/><br/>
**GET** http://localhost:9091/api/user/stream/static
<br/><br/><br/>
1. This shows stats for CPU, Heap, Classes and Threads when using Static ObjectMapper <br/>
![alt text](https://github.com/greyseal/spring-boot-jackson-perf/blob/master/src/main/resources/with_static_mapper_heap_cpu.png "Using Static ObjectMapper Monitor")
<br/><br/><br/>
2. This shows stats for Visual GC when using Static ObjectMapper<br/>
![alt text](https://github.com/greyseal/spring-boot-jackson-perf/blob/master/src/main/resources/with_static_mapper_gc.png "Using Static ObjectMapper GC")
<br/><br/><br/>

**GET** http://localhost:9091/api/user/stream/autowire
<br/><br/><br/>
1. This shows stats for CPU, Heap, Classes and Threads when using Autowire ObjectMapper <br/>
![alt text](https://github.com/greyseal/spring-boot-jackson-perf/blob/master/src/main/resources/with_autowire_mapper_heap_cpu.png "Using Autowire ObjectMapper Monitor")
<br/><br/><br/>
2. This shows stats for Visual GC when using Autowire ObjectMapper<br/>
![alt text](https://github.com/greyseal/spring-boot-jackson-perf/blob/master/src/main/resources/with_autowire_mapper_gc.png "Using Autowire ObjectMapper GC")
<br/>

**Note** :  Looking at **[new_mapper_gc.png]**, the old generation is at its max capacity for a period of time. Heap increases gradually and looks choked up for a certain duration until HTTP response is sent back to the client. In case of any new HTTP request/s during this critical period, we are highly likely to get **OutOfMemoryError**. If we look at the [new_mapper_heap_cpu.png], the CPU was busy most of the time and was running at max capacity and the process took longer to complete. <br/><br/>
Now, if we look at **[static_mapper_gc.png]**, the Old generation is just a flat line with a relatively very low spike in the beginning (no objects are getting tenured) and thus allowing room to handle any other incoming HTTP requests smartly. If we look at [static_mapper_heap_cpu.png], CPU usage was high but comparatively for a less timeframe and so the Heap size. <br/><br/>
Lastly, if we look at **[autowire_mapper_gc.png]**, the Old generation is just a flat line with a relatively very low spike in the beginning (no objects are getting tenured) and thus allowing room to handle any other incoming HTTP requests smartly but slightly higher than the static mapper. If we look at [autowire_mapper_heap_cpu.png], CPU usage was high but comparatively for a less timeframe similar to static mapper but higher heap size than the static mapper. <br/><br/>

ObjectMapper has a default constructor which internally calls a parameterized constructor with parameters as null, which then initializes multiple other classes. Calling new ObjectMapper() every time initializes the default settings causing the problem. Thus, we have to be mindful of such scenarios. A smaller ease of use can cause performance hits.

## Built With
* [Spring Boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management
