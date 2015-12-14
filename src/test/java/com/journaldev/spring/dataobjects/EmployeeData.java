package com.journaldev.spring.dataobjects;

import com.journaldev.spring.model.Employee;

public class EmployeeData {
	
	public Employee empRequest;
	
	public String description;
	
	public int expectedId;
	
	public String expectedName;
	
	public Employee getEmpRequest() {
		return empRequest;
	}

	public void setEmpRequest(Employee empRequest) {
		this.empRequest = empRequest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExpectedId() {
		return expectedId;
	}

	public void setExpectedId(int expectedId) {
		this.expectedId = expectedId;
	}

	public String getExpectedName() {
		return expectedName;
	}

	public void setExpectedName(String expectedName) {
		this.expectedName = expectedName;
	}

}
