package com.restapi.dao;

import java.util.List;

import com.restapi.entity.Employee;

public interface EmployeeDao {
	
	List<Employee> findAll();
	
	Employee findById(int myId);
	
	Employee save(Employee thEmployee);
	
	void delete(int myId);

}
