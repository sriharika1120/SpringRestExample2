
	 package com.journaldev.spring;

	import junit.framework.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.journaldev.spring.controller.StudentURIConstants;
import com.journaldev.spring.dataobjects.StudentData;
	import com.journaldev.spring.model.Student;
    import com.walmart.services.exceltoobjects.testng.DataConvertorException;
    import com.walmart.services.exceltoobjects.testng.ExcelDataToObjectConvertor;


	public class TestSpringRestStudent {
	public static final String SERVER_URI = "http://localhost:8090/SpringRestExample";

	 	@Test // (dependsOnMethods = { "testCreateStudent2" })
	 	public  void testGetAllStudent() {
	 		
	 		RestTemplate restTemplate = new RestTemplate();
	 		Student[] students = restTemplate.getForObject(SERVER_URI+StudentURIConstants.GET_ALLStudents, Student[].class);
	 		System.out.println("Student Count{}"+students.length);
	 		printStudentData(students);
	 	}

	 	@Test(dataProvider = "studentData")
	 	public void testCreateStudent(StudentData studentData) {
	 		
	 		RestTemplate restTemplate = new RestTemplate();
	 		Student student = studentData.getStuRequest();
	 		
	 		
	 		HttpEntity<Student> requestEntity = new HttpEntity<Student>(student);
	 		ResponseEntity<Student> StudentResponse = restTemplate.exchange(SERVER_URI+StudentURIConstants.CREATE_Student, HttpMethod.PUT,requestEntity, Student.class);
	 		printStudentData(StudentResponse.getBody());

	 		Assert.assertEquals(studentData.getExpectedName(), StudentResponse.getBody().getName());
	 		Assert.assertEquals(studentData.getExpectedAge(), StudentResponse.getBody().getAge());
	 		Assert.assertEquals(studentData.getExpectedSection(), StudentResponse.getBody().getSection());
	 		Assert.assertEquals(studentData.getExpectedCourse(), StudentResponse.getBody().getCourse());
	 		Assert.assertEquals(studentData.getExpectedYear(), StudentResponse.getBody().getYear());

	 	}
	 	
	 	
	 	    @DataProvider(name = "studentData")
	 	    public static Object[][] studentData() throws DataConvertorException {
	 	    	
	 	        return  ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/StudentData.xlsx", "studentData", "1-10", StudentData.class, false);
	 	    }
	 	    
	 	    @DataProvider(name = "studentDataNegative")
	 	    public static Object[][] studentDataNegative() throws DataConvertorException {
	 	    	
	 	        return  ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/StudentData.xlsx", "studentData", "11", StudentData.class, false);
	 	    }
	 	
	 	
	 	    @Test(dataProvider = "studentDataNegative" ,expectedExceptions={HttpClientErrorException.class})
	 		public void testNegativeCreatestudent(StudentData studentData) {
	 		
	 			
	 			RestTemplate restTemplate = new RestTemplate();
	 			Student  student = studentData.getStuRequest();
	 			
	 			
	 			HttpEntity<Student> requestEntity = new HttpEntity<Student>(student);
	 			ResponseEntity<Student> StudentResponse = restTemplate.exchange(SERVER_URI+StudentURIConstants.CREATE_Student, HttpMethod.PUT,requestEntity, Student.class);
	 			printStudentData(StudentResponse.getBody());
	 		}
	 	
	 	@Test
	 	public void testCreatStudent2() {
	 		
	 		RestTemplate restTemplate = new RestTemplate();
	 		
	 		Student student = new Student();
	 		student.setAge(2);student.setName("Satish");
	 		
	 		HttpEntity<Student> requestEntity = new HttpEntity<Student>(student);
	 		ResponseEntity<Student> StudentResponse = restTemplate.exchange(SERVER_URI+StudentURIConstants.CREATE_Student, HttpMethod.PUT,requestEntity, Student.class);
 			printStudentData(StudentResponse.getBody());
	 			
	 	}

	 	@Test(dependsOnMethods = { "testCreateStudent2" })
	 	public void testGetStudent() {
	 		RestTemplate restTemplate = new RestTemplate();
	 		Student[] students = restTemplate.getForObject(SERVER_URI+"/rest/student/1", Student[].class);
	 		printStudentData(students);
	 	}
	 	
	 	@Test(dependsOnMethods = { "testCreateStudent2" })
	 	public void testGetStudent2() {
	 		RestTemplate restTemplate = new RestTemplate();
	 		Student[] students = restTemplate.getForObject(SERVER_URI+"/rest/student/2", Student[].class);
	 		printStudentData(students);
	 	}

	 	
	 	public static void printStudentData(Student[] students){
	 		
	 		for(Student student:students) {
	 			System.out.println("Name="+student.getName()+",Age="+student.getAge()+",Section="+student.getSection()+",Course="+student.getCourse()+",Year="+student.getYear());
	 		}
	 		
	 	}
	 	
	    public static void printStudentData(Student student){
	 		
 			System.out.println("Name="+student.getName()+",Age="+student.getAge()+",Section="+student.getSection()+",Course="+student.getCourse()+",Year="+student.getYear());
	 		
	 	}
	 }

