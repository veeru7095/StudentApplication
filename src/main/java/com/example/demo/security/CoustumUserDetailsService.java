package com.example.demo.security;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.UserEmailNotFound;
import com.example.demo.repo.StudentRepo;

@Service
public class CoustumUserDetailsService  implements UserDetailsService{
	
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Student student=studentRepo.findByEmail(email).orElseThrow(
				()-> new UserEmailNotFound(String.format("user not found with Username", email)));
		
		return new org.springframework.security.core.userdetails
				.User(student.getEmail(), student.getPassword(),
					List.of(new SimpleGrantedAuthority(student.getRole())) );
	}

}
