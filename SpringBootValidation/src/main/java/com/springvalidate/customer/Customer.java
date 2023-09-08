package com.springvalidate.customer;

import com.springvalidate.validations.CourseCode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Customer {

	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName = "";

	@NotNull(message = "is required")
	@Min(value = 0, message = "must be greater then or equal to zero")
	@Max(value = 10, message = "must be less than or equal to ten")
	private Integer freePasses;

	@NotNull(message = "is required")
	@Pattern(regexp = "^[a-zA-Z0-9]{6}", message = "only 6 chars/digits")
	private String postalCode;
	
	@NotNull(message = "is required")
	@CourseCode(value = "IND", message="must starts with IND")
	private String courseCode;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String courseCode, String firstName, String lastName, Integer freePasses, String postalCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.freePasses = freePasses;
		this.postalCode = postalCode;
		this.courseCode = courseCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getFreePasses() {
		return freePasses;
	}

	public void setFreePasses(Integer freePasses) {
		this.freePasses = freePasses;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}
