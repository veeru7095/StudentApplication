package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.UserEmailAlreadyExists;
import com.example.demo.exceptions.UserEmailNotFound;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping("/register")
	public ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto){
		if(studentRepo.findByEmail(registerDto.getEmail()).isPresent()) {
			throw new UserEmailAlreadyExists("user email exists");
		}
		return new ResponseEntity<>(studentService.register(registerDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		try {
			Authentication authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return new ResponseEntity<String>("login successful", HttpStatus.OK);
		}
		catch(Exception e) {
			throw new UserEmailNotFound("user Not found with email or password is wrong");
		}
	}

}
