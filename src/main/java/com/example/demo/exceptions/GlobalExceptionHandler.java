package com.example.demo.exceptions;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserEmailNotFound.class)
	public ResponseEntity<Map<String ,Object>> 	handdleUserEmailNotFound(UserEmailNotFound ex){
		Map<String ,Object> message=new HashMap<>();
		message.put("status", HttpStatus.NOT_FOUND.value());
		message.put("error", ex.getMessage());
		return new ResponseEntity<Map<String,Object>>(message,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserEmailAlreadyExists.class)
	public ResponseEntity<Map<String ,Object>> handleUserEmailAlreadyExists(UserEmailAlreadyExists ex){
		Map<String ,Object> map=new HashMap<>();
		map.put("status", HttpStatus.FOUND.value());
		map.put("error", ex.getMessage());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.FOUND);
	}

}
