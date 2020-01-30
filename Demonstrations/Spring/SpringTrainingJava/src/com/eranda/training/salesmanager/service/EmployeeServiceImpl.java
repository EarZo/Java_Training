package com.eranda.training.salesmanager.service;

import java.util.List;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl() {
		System.out.println("Default constructor executed!");
	}

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		System.out.println("Overloaded constructor executed!");
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
}
