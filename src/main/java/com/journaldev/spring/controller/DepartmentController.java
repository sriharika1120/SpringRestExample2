package com.journaldev.spring.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.dao.DepartmentDB;
import com.journaldev.spring.model.Department;

/**
 * Handles requests for the Department service.
 */
@Controller
public class DepartmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	//Map to store Department, ideally we should use database
	Map<Integer, Department> depData = new HashMap<Integer, Department>();
	
		@RequestMapping(value = DepRestURIConstants.GET_DEP, method = RequestMethod.GET)
	public @ResponseBody List<Department> getDepartment(@PathVariable("id") int depId)throws ClassNotFoundException,SQLException  {
		logger.info("Start getDepartment. ID="+depId);
		
		DepartmentDB departmentDB = new DepartmentDB();		
		return departmentDB.retrieveDepartmentByDepId(depId);
	}
	
	@RequestMapping(value = DepRestURIConstants.GET_ALL_DEP, method = RequestMethod.GET)
	public @ResponseBody List<Department> getAllDepartments() throws ClassNotFoundException, SQLException {
		
		logger.info("Start getAllDepartments.");
		List<Department> dep = null;
		DepartmentDB departmentDB = new DepartmentDB();		
		dep=departmentDB.retrieveAllDepartmentList();
		return dep;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = DepRestURIConstants.CREATE_DEP, method = RequestMethod.PUT)
	public @ResponseBody Department createDepartment(@RequestBody Department dep)throws ClassNotFoundException, SQLException {
		
		logger.info("Start createDepartment.");
		dep.setCreatedDate(new Date());
		
		DepartmentDB departmentDB = new DepartmentDB();		
		departmentDB.addDepartment(dep);
		
		return dep;
	}
	
	@RequestMapping(value = DepRestURIConstants.DELETE_DEP, method = RequestMethod.DELETE)
	public @ResponseBody List< Department> deleteDepartment(@PathVariable("id") int depId) throws SQLException, ClassNotFoundException {
		logger.info("Start deleteDepartment.");
		
		DepartmentDB departmentDB = new DepartmentDB();		
		List<Department> departmentListToDelete = departmentDB.retrieveDepartmentByDepId(depId);	
		departmentDB.deleteDepartment(depId);
		
		return departmentListToDelete;
	}

}

