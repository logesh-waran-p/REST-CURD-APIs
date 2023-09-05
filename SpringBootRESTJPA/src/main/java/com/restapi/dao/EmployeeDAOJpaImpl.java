package com.restapi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.restapi.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDao {

	// define field for entity manager
	private EntityManager entityManager;

	// set up constructor injection
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		// create a query
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();

		// return result

		return employees;
		
	}

	@Override
	public Employee findById(int myId) {
		// find employee
		Employee findEmployee = entityManager.find(Employee.class, myId);
		//return employee
		return findEmployee;
		
	}

	@Override
	public Employee save(Employee thEmployee) {
		// save employee
		Employee dbEmployee = entityManager.merge(thEmployee);
		
		//return employee
		return dbEmployee;
	}

	@Override //don't use @transactional at DAO layer it will be handled by Service layer
	public void delete(int myId) {
		//find employee id
		Employee findEmployee = entityManager.find(Employee.class, myId);
		
		//remove that id
		entityManager.remove(findEmployee);
		
		
		
	}

}
