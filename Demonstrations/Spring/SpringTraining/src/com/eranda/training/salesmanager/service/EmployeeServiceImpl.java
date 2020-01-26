package com.eranda.training.salesmanager.service;

import java.util.List;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.repository.EmployeeRepository;
import com.eranda.training.salesmanager.repository.HibernateEmployeeRepositoryImpl;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository = new HibernateEmployeeRepositoryImpl();
	
	public List<Employee> getEmployees(){
		return employeeRepository.getEmployees();
	}

}
