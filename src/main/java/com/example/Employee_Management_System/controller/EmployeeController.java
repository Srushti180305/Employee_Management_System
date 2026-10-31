package com.example.Employee_Management_System.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee_Management_System.entity.Employee;
import com.example.Employee_Management_System.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")

public class EmployeeController {
		
		private EmployeeService employeeService;
		
		public EmployeeController(EmployeeService employeeService) {
			this.employeeService = employeeService;
		}
		
		
        @PostMapping
		public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
//        	return "inserted successfully";
		}
        
        
        @GetMapping("/{email}")
		public Object fetchEmployeeById(@PathVariable String email) {
			return employeeService.fetchEmployeeById(email);	
		}
        
        @GetMapping
		public List<Employee> fetchAllEmployee(@RequestBody Employee employee) {
			return employeeService.fetchAllEmployee(employee);	
		}
        
        @DeleteMapping("/{email}")
		public String deleteById(@PathVariable String email) {
			  return employeeService.deleteById(email);	
		}
        
        @DeleteMapping
		public void deleteAllEmployee(@RequestBody Employee employee) {
			 employeeService.deleteAllEmployee(employee);
			
		}
        
        @PutMapping("/{email}")
        public String updateEmployeeById(@PathVariable String email,@Valid @RequestBody Employee employee) {
			return employeeService.updateEmployeeById(email,employee);
		}
	

}
