package com.example.Employee_Management_System.dto;

import lombok.Data;

@Data
public class VerifyOtpRequest {
	
	private String email;
	private String otp;

}
