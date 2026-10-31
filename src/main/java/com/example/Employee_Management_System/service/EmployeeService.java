package com.example.Employee_Management_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Employee_Management_System.entity.Employee;
import com.example.Employee_Management_System.repository.EmployeeRepository;

import tools.jackson.databind.node.StringNode;


@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Object fetchEmployeeById(String email) {
	    Optional<Employee> emp = employeeRepository.findById(email);
	    if (emp.isPresent()) return emp.get();
	    return "No data found with email " + email;
	}
	
	public List<Employee> fetchAllEmployee(Employee employee) {
		return employeeRepository.findAll();
		
	}
	public String deleteById(String email) {          // void -> String
	    if (employeeRepository.existsById(email)) {
	        employeeRepository.deleteById(email);
	        return "Data deleted by email " + email;
	    }
	    return "No employee found with email " + email;
	}
	public void deleteAllEmployee(Employee employee) {
		 employeeRepository.deleteAll();
		
	}
	
	public String updateEmployeeById(String email,Employee employee) {
		Employee existingEmployee=employeeRepository.findById(email).get();
		
		existingEmployee.setName(employee.getName());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setSalary(employee.getSalary());
		existingEmployee.setDepartment(employee.getDepartment());
		
		employeeRepository.save(existingEmployee);
		
		return "Employee data updated Sucessfully";
		}
}
