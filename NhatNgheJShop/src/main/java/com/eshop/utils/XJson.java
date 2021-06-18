package com.eshop.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XJson {
	private static ObjectMapper mapper = new ObjectMapper();
	public static <T> T toObject(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json) {
		return toObject(json, Map.class);
	}
	
	
}
