package com.eranda.training.salesmanager.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.eranda.training.salesmanager.model.Employee;

@Repository("you-can-use-any-name")
public class HibernateEmployeeRepositoryImpl implements EmployeeRepository {
	
	public List<Employee> getEmployees(){
		
		List<Employee> employees = new ArrayList<>();
		
		Employee employee = new Employee();
		employee.setEmployeeeName("Eranda");
		employee.setEmployeeLocation("Matara");
		employees.add(employee);
		
		return employees;
	}

}
