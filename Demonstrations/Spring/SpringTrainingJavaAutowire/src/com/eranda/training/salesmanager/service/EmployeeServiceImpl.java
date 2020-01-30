package com.eranda.training.salesmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
//	@Autowired
	//uses Member variable Injection with reflection (i.e. no setter method is called when initializing)
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl() {
		System.out.println("No-args constructor executed!");
	}

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		System.out.println("Overloaded constructor executed!");
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
	}

	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		System.out.println("Setter executed!");
		this.employeeRepository = employeeRepository;
	}
}
