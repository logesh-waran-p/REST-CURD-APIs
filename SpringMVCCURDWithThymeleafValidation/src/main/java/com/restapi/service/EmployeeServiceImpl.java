package com.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.dao.EmployeeRepository;
import com.restapi.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findAll() {

		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public Employee findById(int myId) {
		Optional<Employee> result = employeeRepository.findById(myId);
		Employee employee = null;
		
		if(result.isPresent()) {
			employee = result.get();
		}else {
			throw new RuntimeException("employee Id not found: "+myId);
		}
		return employee;

	}

	@Override
	public Employee save(Employee thEmployee) {
		return employeeRepository.save(thEmployee);
	}

	@Override
	public void delete(int myId) {
		employeeRepository.deleteById(myId);

	}

}
