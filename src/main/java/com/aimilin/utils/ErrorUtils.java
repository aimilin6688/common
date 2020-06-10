package com.aimilin.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorUtils {

	public static Map<String, String> toMap(BindingResult result) {
		Map<String, String> map = new HashMap<String, String>();
		List<FieldError> list = result.getFieldErrors();
		for (FieldError error : list) {
			map.put(error.getField(), error.getDefaultMessage());
		}
		return map;
	}

	public static List<String> toList(BindingResult result) {
		List<String> errors = new ArrayList<>();
		List<FieldError> list = result.getFieldErrors();
		for (FieldError error : list) {
			errors.add(error.getDefaultMessage());
		}
		return errors;
	}
	
	public static String firstError(BindingResult result) {
		List<String> results = toList(result);
		return results.get(0);
	}
}
