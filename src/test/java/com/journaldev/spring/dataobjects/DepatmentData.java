package com.journaldev.spring.dataobjects;

import com.journaldev.spring.model.Department;


public class DepatmentData {
	
    public Department depRequest;
	
	public String description;
	
	public int expectedId;
	
	public Department getDepRequest() {
		return depRequest;
	}

	public void setDepRequest(Department depRequest) {
		this.depRequest = depRequest;
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

	public String expectedName;
	

}
