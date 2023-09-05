package com.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.dao.EmployeeDao;
import com.restapi.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> findAll() {

		return employeeDao.findAll();
	}

	@Override
	public Employee findById(int myId) {
		return employeeDao.findById(myId);
		
	}
	
	@Transactional
	@Override
	public Employee save(Employee thEmployee) {
		return employeeDao.save(thEmployee);
	}

	@Transactional
	@Override
	public void delete(int myId) {
		employeeDao.delete(myId);
		
	}

}
