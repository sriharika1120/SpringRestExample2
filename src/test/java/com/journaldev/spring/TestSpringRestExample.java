 package com.journaldev.spring;

import junit.framework.Assert;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.journaldev.spring.controller.EmpRestURIConstants;
import com.journaldev.spring.dataobjects.EmployeeData;
import com.journaldev.spring.model.Employee;
import com.walmart.services.exceltoobjects.testng.DataConvertorException;
import com.walmart.services.exceltoobjects.testng.ExcelDataToObjectConvertor;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:8090/SpringRestExample";
	
//	public static void main(String args[]){
//		
//		testGetDummyEmployee();
//		System.out.println("*****");
//		testCreateEmployee();
//		System.out.println("*****");
//		testGetEmployee();
//		System.out.println("*****");
//		testGetAllEmployee();
//	}

	@Test //(dependsOnMethods = { "testCreateEmployee2" })
	public  void testGetAllEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Employee> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		Employee[] emps = restTemplate.getForObject(SERVER_URI+EmpRestURIConstants.GET_ALL_EMP, Employee[].class);
		System.out.println("Employee Count{}"+emps.length);
		printEmpData(emps);
	}

	@Test(dataProvider = "employeeData")
	public void testCreateEmployee(EmployeeData employeeData) {
		
//		RestTemplate restTemplate = new RestTemplate();
//		Employee emp = new Employee();
//		emp.setId(1);emp.setName("Harika");
//		Employee employee = restTemplate.postForObject(SERVER_URI+EmpRestURIConstants.CREATE_EMP, emp, Employee.class);
//		printEmpData(employee);
		
		
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = employeeData.getEmpRequest();
		
		
		HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(emp);
		ResponseEntity<Employee> employeeResponse = restTemplate.exchange(SERVER_URI+EmpRestURIConstants.CREATE_EMP, HttpMethod.PUT,requestEntity, Employee.class);
 		printEmpData(employeeResponse.getBody());

		Assert.assertEquals(employeeData.getExpectedId(), employeeResponse.getBody().getId());
		Assert.assertEquals(employeeData.getExpectedName(), employeeResponse.getBody().getName());
		
	}
	
	@Test(dataProvider = "employeeData")
	public void testDeleteEmployee(EmployeeData employeeData) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = employeeData.getEmpRequest();
		
		HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(emp);
		ResponseEntity<Employee> employeeResponse = restTemplate.exchange(SERVER_URI+EmpRestURIConstants.CREATE_EMP, HttpMethod.PUT,requestEntity, Employee.class);
 		printEmpData(employeeResponse.getBody());

		Assert.assertEquals(employeeData.getExpectedId(), employeeResponse.getBody().getId());
		Assert.assertEquals(employeeData.getExpectedName(), employeeResponse.getBody().getName());
		
		//test delete employee
		//HttpEntity<Employee> delRequestEntity = new HttpEntity<Employee>(emp);
		//ResponseEntity<Employee> delEmployeeResponse = restTemplate.exchange(SERVER_URI+EmpRestURIConstants.DELETE_EMP, HttpMethod.POST,requestEntity, Employee.class);
 		//printEmpData(employeeResponse.getBody());
		
 
        //To do the delete get the employee by id. The employee should not be returned.		
		//Employee[] emps = restTemplate.getForObject(SERVER_URI+"/rest/emp/"+emp.getId(), Employee[].class);
		
 		//Check the delete response..
		//Assert.assertEquals(0, emps.length);
	}
	
	
	    @DataProvider(name = "employeeData")
	    public static Object[][] employeeData() throws DataConvertorException {
	    	
	        return  ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/EmployeeData.xlsx", "employeeData", "1-11", EmployeeData.class, false);
	    }
	    
	    @DataProvider(name = "employeeDataNegative")
	    public static Object[][] employeeDataNegative() throws DataConvertorException {
	    	
	        return  ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/EmployeeData.xlsx", "employeeData", "12", EmployeeData.class, false);
	    }
	
	
	    @Test(dataProvider = "employeeDataNegative" ,expectedExceptions={HttpClientErrorException.class})
		public void testNegativeCreateEmployee(EmployeeData employeeData) {
			
//			RestTemplate restTemplate = new RestTemplate();
//			Employee emp = new Employee();
//			emp.setId(1);emp.setName("Harika");
//			Employee employee = restTemplate.postForObject(SERVER_URI+EmpRestURIConstants.CREATE_EMP, emp, Employee.class);
//			printEmpData(employee);
			
			RestTemplate restTemplate = new RestTemplate();
			Employee emp = employeeData.getEmpRequest();
			
			
			HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(emp);
			ResponseEntity<Employee> employeeResponse = restTemplate.exchange(SERVER_URI+EmpRestURIConstants.CREATE_EMP, HttpMethod.PUT,requestEntity, Employee.class);
			printEmpData(employeeResponse.getBody());

//			Assert.assertEquals(employeeData.getExpectedId(), employeeResponse.getBody().getId());
//			Assert.assertEquals(employeeData.getExpectedName(), employeeResponse.getBody().getName());
			
		}
	
	@Test
	public void testCreateEmployee2() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Employee emp = new Employee();
		emp.setId(2);emp.setName("Satish");
		
		HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(emp);
		ResponseEntity<Employee> employeeResponse = restTemplate.exchange(SERVER_URI+EmpRestURIConstants.CREATE_EMP, HttpMethod.PUT,requestEntity, Employee.class);
		printEmpData(employeeResponse.getBody());
			
	}

	@Test(dependsOnMethods = { "testCreateEmployee2" })
	public void testGetEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee[] emps = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", Employee[].class);
		printEmpData(emps);
	}
	
	@Test(dependsOnMethods = { "testCreateEmployee2" })
	public void testGetEmployee2() {
		RestTemplate restTemplate = new RestTemplate();
		Employee[] emps = restTemplate.getForObject(SERVER_URI+"/rest/emp/2", Employee[].class);
		printEmpData(emps);
	}

	
	@Test
	public  void testGetDummyEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.getForObject(SERVER_URI+EmpRestURIConstants.DUMMY_EMP, Employee.class);
		printEmpData(emp);
		
		Assert.assertEquals(9999, emp.getId());
		Assert.assertEquals("Dummy", emp.getName());

	}
	
	public static void printEmpData(Employee[] emps){
		
		for(Employee emp:emps) {
			System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",CreatedDate="+emp.getCreatedDate());
		}
		
	}
	
   public static void printEmpData(Employee emp){
		
			System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",CreatedDate="+emp.getCreatedDate());
		
	}
}
