package com.example.Employee_Management_System.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Employee_Management_System.dto.VerifyOtpRequest;
import com.example.Employee_Management_System.entity.User;
import com.example.Employee_Management_System.exception.OtpAlreadyVerifiedException;
import com.example.Employee_Management_System.exception.OtpExpiredException;
import com.example.Employee_Management_System.exception.OtpNotMatchingException;
import com.example.Employee_Management_System.exception.OtpNotPresentException;
import com.example.Employee_Management_System.exception.UserNotFoundException;
import com.example.Employee_Management_System.repository.UserRepository;

@Service
public class VerfyOtpService {
	
	private UserRepository userRepository;

	public VerfyOtpService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public String otpVerification(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> op=userRepository.findByEmail(verifyOtpRequest.getEmail());
		if(op.isPresent()) {
			User user=op.get();
			
			//1.Already verified..?
			if(user.getVerified()) {
				throw new OtpAlreadyVerifiedException("User with email: " + verifyOtpRequest.getEmail() + " is already verified.");
			}
			
			//2.OTP exist?
			if(user.getOtp()==null) {
				throw new OtpNotPresentException("Otp not present for user with email: " + verifyOtpRequest.getEmail());
			}
			
			//OTP Matching
			if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
				throw new OtpNotMatchingException("Otp not matching for user with email: " + verifyOtpRequest.getEmail());
			}
			
			//4.Otp Expiry
			if(LocalDateTime.now().isAfter(user.getOtpexpiry())) {
				throw new OtpExpiredException("Otp expired for user with email: " + verifyOtpRequest.getEmail());
			}
			
			//real user verification
			else {
			user.setVerified(true);
			user.setOtp(null);
			user.setOtpexpiry(null);
			userRepository.save(user);
			return "otp verified successfully";
		}
		}
		else {
			throw new UserNotFoundException("User with email: " + verifyOtpRequest.getEmail() + " not found.");
		}
	}

}
