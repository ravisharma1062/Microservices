package com.application.jpa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ControllerUtil {
	public static Map<String, Object> getErrorResponseMap(BindingResult bindingResult) {
		Map<String, Object> errorMap = new HashMap<>();
		for(FieldError error : bindingResult.getFieldErrors()) {
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		return errorMap;
	}
	
	public static ResponseEntity<Object> getFieldErrorResponse(String message, Object error) {
		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("status", HttpStatus.BAD_REQUEST);
		errorMap.put("fieldError", error);
		errorMap.put("message", message);
		return new ResponseEntity<Object>(errorMap, HttpStatus.BAD_REQUEST);
	}
}
