package com.journaldev.spring.model;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Employee implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;
	
	private int empid;
	private String empname;
	private Date created_date;
	
	public int getId() {
		return empid;
	}
	public void setId(int id) {
		this.empid = id;
	}
	public String getName() {
		return empname;
	}
	public void setName(String name) {
		this.empname = name;
	}
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return created_date;
	}
	public void setCreatedDate(Date createdDate) {
		this.created_date = createdDate;
	}
	
}

