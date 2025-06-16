package com.example.recipe_rest_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController{
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Map<String, Object>> handlerNotFound(ResourceNotFound exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
				"timestamp",LocalDateTime.now(),
				"status",404,
				"error","Not Found",
				"message",exception.getMessage()
				));
	}
}
