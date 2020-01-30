package com.eranda.training.salesmanager.service;

import java.util.List;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeRepository employeeRepository;
	
	
//	Create constructor to inject dependency
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

}
