package com.restapi.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restapi.entity.Employee;
import com.restapi.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	
	//removing white space using initBinding and trimming method
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}


	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		// get employees from database
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee thEmployee = new Employee();
		
		theModel.addAttribute("employee", thEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		//get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		
		//set the employee in the model pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		//send over to our form
		return "employees/employee-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteEmployeeById(@RequestParam("employeeId") int theId) {
		//delete the employee by id
		employeeService.delete(theId);
		
		
		//delete the employee in the model
		return "redirect:/employees/list";
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee theEmployee, BindingResult theBindingResult) {
		
		//save the employee
		
		if (theBindingResult.hasErrors()) {
			return "employees/employee-form";
		} else {
			employeeService.save(theEmployee);
			return "redirect:/employees/list";
		}
	
	}  
	
}
