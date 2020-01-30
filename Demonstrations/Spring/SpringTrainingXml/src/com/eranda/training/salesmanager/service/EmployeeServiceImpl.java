package com.eranda.training.salesmanager.service;

import java.util.List;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.repository.EmployeeRepository;
import com.eranda.training.salesmanager.repository.HibernateEmployeeRepositoryImpl;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	//Create setter to inject dependency
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

}
