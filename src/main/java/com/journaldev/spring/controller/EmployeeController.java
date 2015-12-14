package com.journaldev.spring.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.dao.EmployeeDB;
import com.journaldev.spring.model.Employee;

/**
 * Handles requests for the Employee service.
 * @param <Employee>
 */
@Controller
public class EmployeeController {
		
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//Map to store employees, ideally we should use database
	//Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	@RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		//empData.put(9999, emp);
		return emp;
	}
	
	@RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployee(@PathVariable("id") int empId) throws ClassNotFoundException, SQLException {
		logger.info("Start getEmployee. ID="+empId);
		
		EmployeeDB employeeDB = new EmployeeDB();
		
		
		
		return employeeDB.retrieveEmployeesByEmpId(empId);
	}
	
	@RequestMapping(value = EmpRestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() throws ClassNotFoundException, SQLException {
		logger.info("Start getAllEmployees.");
		List<Employee> emps = null; //new ArrayList<Employee>();
//		Set<Integer> empIdKeys = empData.keySet();
//		for(Integer i : empIdKeys){
//			emps.add(empData.get(i));
//		}
		
		
		EmployeeDB employeeDB = new EmployeeDB();
		
		emps = employeeDB.retrieveAllEmployeesList();
		
		logger.debug("Successfully fetched the data. Employee Count {}."+emps.size());
		
		return emps;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = EmpRestURIConstants.CREATE_EMP, method = RequestMethod.PUT)
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) throws ClassNotFoundException, SQLException {
		
		logger.info("Start createEmployee.");
		emp.setCreatedDate(new  Date());
		EmployeeDB employeeDB = new EmployeeDB();
		employeeDB.addEmployee(emp);
		return emp;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = EmpRestURIConstants.DELETE_EMP, method = RequestMethod.DELETE)
	public @ResponseBody List<Employee> deleteEmployee(@PathVariable("id") int empId) throws ClassNotFoundException, SQLException {
		logger.info("Start deleteEmployee.");
		EmployeeDB employeeDB = new EmployeeDB();
		
		List<Employee> employeeListToDelete = employeeDB.retrieveEmployeesByEmpId(empId);
		
		employeeDB.deleteEmployee(empId);
		
		return employeeListToDelete;
	}
	

	@RequestMapping(value = "/headerParamEx", method = RequestMethod.GET)
	public @ResponseBody String headerParamEx(@RequestHeader("userType") String userType) throws ClassNotFoundException, SQLException {
		logger.info("headerParamEx..");
		
		return "headerParamEx {}"+userType;
	}
	
	@RequestMapping(value = "/requestParamEx", method = RequestMethod.GET)
	public @ResponseBody String requestParam(@RequestParam("userType") String userType) throws ClassNotFoundException, SQLException {
		logger.info("requestParam..");
		
		return "requestParam {}"+userType;
	}
	
	@RequestMapping(value = "/pathParamEx/{id}", method = RequestMethod.GET)
	public @ResponseBody String pathParamEx(@PathVariable("id") String id) throws ClassNotFoundException, SQLException {
		logger.info("pathParamEx..");
		
		return "pathParamEx {}"+id;
	}
	
	
	 
}
