package com.restapi.service;

import java.util.List;

import com.restapi.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(int myId);

	Employee save(Employee thEmployee);

	void delete(int myId);

}
