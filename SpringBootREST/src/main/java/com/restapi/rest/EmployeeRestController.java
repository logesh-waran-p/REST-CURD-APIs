package com.restapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.entity.Employee;
import com.restapi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	// inject employee dao
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// "/employees" get request
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{myId}")
	public Employee findById(@PathVariable int myId) {
		Employee thEmployee = employeeService.findById(myId);

		if (thEmployee == null) {
			throw new RuntimeException("Employee Id not found");
		}
		return thEmployee;

	}

	@PostMapping("/employees")
	public Employee savEmployee(@RequestBody Employee myEmployee) {
		return employeeService.save(myEmployee);
	}
	
	@PutMapping("/employees")
	public Employee updatEmployee(@RequestBody Employee myEmployee) {
		return employeeService.save(myEmployee);
		
	}

	@DeleteMapping("/employees/{myId}")
	public String deleteById(@PathVariable int myId) {
		Employee employee = employeeService.findById(myId);
		
		if(employee==null) {
			throw new RuntimeException("Employee Id not Found "+myId);
		}
		employeeService.delete(myId);
		return "employee Id deleted "+myId;

	}
	
	

}
