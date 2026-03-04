package com.example.demo.exceptions;

public class UserEmailAlreadyExists extends RuntimeException{
	
	private String meassage;
	public UserEmailAlreadyExists(String message) {
		super(message);
	}

}
