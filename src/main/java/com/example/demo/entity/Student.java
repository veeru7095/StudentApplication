package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private long id;
	
	@Column(name="student_name",unique=false, nullable=false)
	private String name;
	
	@Column(name="student_Email",unique=true, nullable=false)
	private String email;
	
	@Column(name="student_password",unique=false, nullable=false)
	private String password;
	
	@Column(name="student_address",unique=true, nullable=false)
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(name="roles",nullable = false)
	private String role;
}
