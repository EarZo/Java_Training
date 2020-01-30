package com.eranda.training.salesmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	// Default (when no other constructor is defined) or No-args constructor is called. Uses reflection to initialize member variable (i.e. no setter method is called when initializing)
	EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl() {
		System.out.println("No-args constructor executed!");
	}

//	@Autowired
	// Using @Autowired in this constructor makes the caller to use this constructor specifically
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		System.out.println("Overloaded constructor executed!");
		this.employeeRepository = employeeRepository;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

//	@Autowired
	// Default (when no other constructor is defined) or No-args constructor is called
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		System.out.println("Setter executed!");
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
	}

}
