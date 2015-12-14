package com.journaldev.spring.controller;

import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.dao.StudentDB;
import com.journaldev.spring.model.Student;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping(value = StudentURIConstants.GET_Student, method = RequestMethod.GET)
	public @ResponseBody List<Student> getStudent(@PathVariable("name") String name) throws ClassNotFoundException, SQLException {
		logger.info("Start getStudent. ID="+name);
		
		StudentDB studentDB = new StudentDB();
		return studentDB.retrieveStudentByname(name);
	}
	
	@RequestMapping(value = StudentURIConstants.GET_ALLStudents, method = RequestMethod.GET)
	public @ResponseBody List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
		logger.info("Start getAllStudents.");
		List<Student> stu = null; //new ArrayList<Student>();

		
		StudentDB studentDB = new StudentDB();
		
		stu = studentDB.retrieveAllstudentsList();
		
		logger.debug("Successfully fetched the data. Student Count {}."+stu.size());
		
		return stu;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = StudentURIConstants.CREATE_Student, method = RequestMethod.PUT)
	public @ResponseBody Student createStudent(@RequestBody Student student) throws ClassNotFoundException, SQLException {
		
		logger.info("Start createStudent.");
		
		StudentDB studentDB = new StudentDB();
		studentDB.addStudent(student);
		return student;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = StudentURIConstants.DELETE_Student, method = RequestMethod.DELETE)
	public @ResponseBody List<Student> deleteStudent(@PathVariable("name") String name) throws ClassNotFoundException, SQLException {
		logger.info("Start deleteStudent.");
		StudentDB studentDB = new StudentDB();
		
		List<Student> StudentListToDelete = studentDB.retrieveStudentByname(name);
		
		studentDB.deleteStudent(name);
		
		return StudentListToDelete;
	}
	

//	@RequestMapping(value = "/headerParamEx", method = RequestMethod.GET)
//	public @ResponseBody String headerParamEx(@RequestHeader("userType") String userType) throws ClassNotFoundException, SQLException {
//		logger.info("headerParamEx..");
//		
//		return "headerParamEx {}"+userType;
//	}
//	
//	@RequestMapping(value = "/requestParamEx", method = RequestMethod.GET)
//	public @ResponseBody String requestParam(@RequestParam("userType") String userType) throws ClassNotFoundException, SQLException {
//		logger.info("requestParam..");
//		
//		return "requestParam {}"+userType;
//	}
//	
//	@RequestMapping(value = "/pathParamEx/{name}", method = RequestMethod.GET)
//	public @ResponseBody String pathParamEx(@PathVariable("name") String name) throws ClassNotFoundException, SQLException {
//		logger.info("pathParamEx..");
//		
//		return "pathParamEx {}"+name;
//	}
	
	
	 
}

