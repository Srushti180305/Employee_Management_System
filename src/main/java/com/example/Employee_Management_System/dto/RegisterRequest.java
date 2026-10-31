package com.example.Employee_Management_System.dto;

import lombok.Data;

@Data
public class RegisterRequest {

	private String name;
	private String email;
	private String password;
}
