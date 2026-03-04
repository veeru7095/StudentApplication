package com.example.demo.payload;

import lombok.Data;

@Data
public class RegisterDto {
	
	private long id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String role;
}
