package com.example.Employee_Management_System.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Employee_Management_System.dto.RegisterRequest;
import com.example.Employee_Management_System.dto.VerifyOtpRequest;
import com.example.Employee_Management_System.entity.User;
import com.example.Employee_Management_System.repository.UserRepository;
import com.example.Employee_Management_System.util.OtpGenerator;

@Service
public class UserService {

	private UserRepository userRepository;
	private EmailService emailService;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) { // add
																													// param
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.passwordEncoder = passwordEncoder;
	}

	public String registerUser(RegisterRequest registerRequest) {
		Optional<User> op = userRepository.findByEmail(registerRequest.getEmail());
		if (op.isPresent()) {
			return "Email already exists";
		} else {
			User user = new User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole("USER");
			user.setVerified(false);

			String otp = OtpGenerator.generateOtp();
			user.setOtp(otp);

			user.setOtpexpiry(LocalDateTime.now().plusMinutes(5));

			userRepository.save(user);

			emailService.sendOtp(registerRequest.getEmail(), otp);
			return "Please check your email for OTP";
		}
	}

	public String resendOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> ou = userRepository.findByEmail(verifyOtpRequest.getEmail());
		if (ou.isPresent()) {
			User user = ou.get();
			String otp = OtpGenerator.generateOtp();
			user.setOtp(otp);
			user.setOtpexpiry(LocalDateTime.now().plusMinutes(1));
			userRepository.save(user);
			emailService.sendOtp(verifyOtpRequest.getEmail(), otp);
			return "otp sent";
		} else {
			return "User not found";
		}

	}

}
