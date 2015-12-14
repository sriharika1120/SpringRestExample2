package com.journaldev.spring.dataobjects;

import com.journaldev.spring.model.Student;

public class StudentData {
	
	public Student StuRequest;
	
	public String description;
		
	public String expectedName;

	public int expectedAge;

	public String expectedSection;

	public String expectedCourse;

	public String expectedYear;


	public Student getStuRequest() {
		return StuRequest;
	}

	public void setStuRequest(Student stuRequest) {
		StuRequest = stuRequest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpectedName() {
		return expectedName;
	}

	public void setExpectedName(String expectedName) {
		this.expectedName = expectedName;
	}

	public int getExpectedAge() {
		return expectedAge;
	}

	public void setExpectedAge(int expectedAge) {
		this.expectedAge = expectedAge;
	}
	public String getExpectedSection() {
		return expectedSection;
	}

	public void setExpectedSection(String expectedSection) {
		this.expectedSection = expectedSection;
	}
	public String getExpectedCourse() {
		return expectedCourse;
	}

	public void setExpectedCourse(String expectedCourse) {
		this.expectedCourse = expectedCourse;
	}

	public String getExpectedYear() {
		return expectedYear;
	}

	public void setExpectedYear(String expectedYear) {
		this.expectedYear = expectedYear;
	}
}
