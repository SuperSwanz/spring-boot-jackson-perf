package com.sample.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static final ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}