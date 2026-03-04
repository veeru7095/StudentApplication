package com.example.demo.exceptions;

public class UserEmailNotFound extends RuntimeException {
	private String message;
	
	public UserEmailNotFound(String message) {
		super(message);
	}

}
