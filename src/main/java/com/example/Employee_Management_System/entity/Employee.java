package com.example.Employee_Management_System.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Employee {
	
	@Id
	@Email(message = "Enter valid email")
	private String email;
	@NotBlank
	private String name;
	@Positive
	private double salary;
	@NotBlank
	private String department;

}
