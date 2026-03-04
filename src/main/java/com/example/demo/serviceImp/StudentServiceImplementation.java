package com.example.demo.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImplementation implements StudentService{
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public RegisterDto register(RegisterDto registerDto) {
		registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		Student student=modelMapper.map(registerDto, Student.class);
		studentRepo.save(student);
		return modelMapper.map(student, RegisterDto.class);
	}



}
