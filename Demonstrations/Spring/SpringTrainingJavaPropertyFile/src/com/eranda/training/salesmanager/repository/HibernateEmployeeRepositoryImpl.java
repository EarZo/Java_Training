package com.eranda.training.salesmanager.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.eranda.training.salesmanager.model.Employee;

public class HibernateEmployeeRepositoryImpl implements EmployeeRepository {
	
	@Value("${name}")
	private String employeeName;
	@Value("${city}")
	private String employeeLocation;
	
	public List<Employee> getEmployees(){
		
		List<Employee> employees = new ArrayList<>();
		
		Employee employee = new Employee();
		employee.setEmployeeeName(employeeName);
		employee.setEmployeeLocation(employeeLocation);
		employees.add(employee);
		
		return employees;
	}

}
