package com.example.Employee_Management_System.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee_Management_System.dto.RegisterRequest;
import com.example.Employee_Management_System.dto.VerifyOtpRequest;
import com.example.Employee_Management_System.entity.User;
import com.example.Employee_Management_System.service.UserService;
import com.example.Employee_Management_System.service.VerfyOtpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	private VerfyOtpService verfyOtpService;
	
	public UserController(UserService userService, VerfyOtpService verfyOtpService) {
		this.userService = userService;
		this.verfyOtpService = verfyOtpService;
	}

	@PostMapping("/register")
	public String registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
		return userService.registerUser(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public void otpVerification(@Valid @RequestBody VerifyOtpRequest verfyOtpRequest) {
		verfyOtpService.otpVerification(verfyOtpRequest);
	}
	
	@PostMapping("/resend-otp")
	public String resendOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		return userService.resendOtp(verifyOtpRequest);
	}

	
}
