package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.sample.app" })
@EnableJpaRepositories(basePackages = { "com.sample.app" })
@EnableConfigurationProperties
@EnableAutoConfiguration
public class Runner {
	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}
}