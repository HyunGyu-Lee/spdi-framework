package com.spdiframework.webmvc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T fromJson(String jsonString, Class<T> type) {
		try {
			return objectMapper.readValue(jsonString, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
