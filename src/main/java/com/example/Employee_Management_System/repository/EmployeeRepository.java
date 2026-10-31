package com.example.Employee_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Employee_Management_System.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
