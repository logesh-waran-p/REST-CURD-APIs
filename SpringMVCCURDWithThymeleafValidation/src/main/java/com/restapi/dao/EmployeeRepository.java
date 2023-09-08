package com.restapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//find order by first name
	public List<Employee> findAllByOrderByFirstNameAsc();
}
