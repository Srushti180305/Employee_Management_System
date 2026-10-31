package com.example.Employee_Management_System.exception;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> invalidData(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
	        errors.put(fe.getField(), fe.getDefaultMessage());
	    }
	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFound(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<String>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(OtpExpiredException.class)
	public ResponseEntity<String> otpExpired(OtpExpiredException otpExpiredException){
		return new ResponseEntity<String>(otpExpiredException.getMessage(),HttpStatus.REQUEST_TIMEOUT);
	}
	
	@ExceptionHandler(OtpAlreadyVerifiedException.class)
	public ResponseEntity<String> otpAlreadyVerified(OtpAlreadyVerifiedException otpAlreadyVerifiedException){
		return new ResponseEntity<String> (otpAlreadyVerifiedException.getMessage(),HttpStatus.TOO_MANY_REQUESTS);
	}
	
	@ExceptionHandler(OtpNotMatchingException.class)
	public ResponseEntity<String> otpNotMatching(OtpNotMatchingException otpNotMatchingException){
		return new ResponseEntity<String>(otpNotMatchingException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OtpNotPresentException.class)
	public ResponseEntity<String> otpNotPresent(OtpNotPresentException otpNotPresentException){
		return new ResponseEntity<String>(otpNotPresentException.getMessage(),HttpStatus.NOT_FOUND);
	}

}
