package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Department implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;
	
	private int Depid;
	private String Depname;
	private Date createdDate;
	
	public int getDepId() {
		return Depid;
	}
	public void setDepId(int id) {
		this.Depid = id;
	}
	public String getDepname() {
		return Depname;
	}
	public void setDepname(String depname) {
		Depname = depname;
	}

	
	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
		
	
}
