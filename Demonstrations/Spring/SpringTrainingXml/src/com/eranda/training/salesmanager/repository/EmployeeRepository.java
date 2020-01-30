package com.eranda.training.salesmanager.repository;

import java.util.List;

import com.eranda.training.salesmanager.model.Employee;

public interface EmployeeRepository {

	List<Employee> getEmployees();

}