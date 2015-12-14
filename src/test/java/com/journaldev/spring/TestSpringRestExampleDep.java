 package com.journaldev.spring;


import junit.framework.Assert;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.journaldev.spring.controller.DepRestURIConstants;
import com.journaldev.spring.dataobjects.DepatmentData;
import com.journaldev.spring.model.Department;
import com.walmart.services.exceltoobjects.testng.DataConvertorException;
import com.walmart.services.exceltoobjects.testng.ExcelDataToObjectConvertor;

public class TestSpringRestExampleDep {

	public static final String SERVER_URI = "http://localhost:8090/SpringRestExample";
	
	Integer departmentId = null;
	
	@Test //(dependsOnMethods = { "testCreateDepartment2" })
	public  void testGetAllDepartments() {
	RestTemplate restTemplate = new RestTemplate();
		Department[] deps = restTemplate.getForObject(SERVER_URI+DepRestURIConstants.GET_ALL_DEP, Department[].class);
		System.out.println("Department Count{}"+deps.length);
		printDepData(deps);
	}

	@Test(dataProvider = "departmentData")
    public void testCreateDepartment(DepatmentData departmentData) {
    RestTemplate restTemplate = new RestTemplate();
    Department dep = departmentData.getDepRequest();
          
          
		HttpEntity<Department> requestEntity = new HttpEntity<Department>(dep);
		ResponseEntity<Department>departmentResponse=restTemplate.exchange(SERVER_URI+DepRestURIConstants.CREATE_DEP, HttpMethod.PUT,requestEntity, Department.class);
		 printDepData(departmentResponse.getBody());

           Assert.assertEquals(departmentData.getExpectedId(), departmentResponse.getBody().getDepId());
           Assert.assertEquals(departmentData.getExpectedName(), departmentResponse.getBody().getDepname());
          
    }
        @DataProvider(name = "departmentData")
        public static Object[][] departmentData() throws DataConvertorException {
          
            return  ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/DepartmentData.xlsx", "departmentData", "1-11", DepatmentData.class, false);
        }
       
        @DataProvider(name = "departmentDataNegative")
        public static Object[][] departmentDataNegative() throws DataConvertorException {
          
            return  ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/DepartmentData.xlsx", "departmentData", "12", DepatmentData.class, false);
        }
   
        @Test(dataProvider = "departmentDataNegative" ,expectedExceptions={HttpClientErrorException.class})
           public void testNegativeCreateDepartment(DepatmentData depatmentData) {
                  RestTemplate restTemplate = new RestTemplate();
                  Department dep = depatmentData.getDepRequest();
                 
                 
                  HttpEntity<Department> requestEntity = new HttpEntity<Department>(dep);
                  ResponseEntity<Department> departmentResponse = restTemplate.exchange(SERVER_URI+DepRestURIConstants.CREATE_DEP, HttpMethod.PUT,requestEntity, Department.class);
                  printDepData(departmentResponse.getBody());

                 
           }
        @Test
    	public void testCreateDepartment2() {
    		
    		RestTemplate restTemplate = new RestTemplate();
    		
    		Department dep = new Department();
    		dep.setDepId(89);dep.setDepname("MPC");
    		
    		HttpEntity<Department> requestEntity = new HttpEntity<Department>(dep);
    		
    		
    		ResponseEntity<Department> departmentResponse = restTemplate.exchange(SERVER_URI+DepRestURIConstants.CREATE_DEP, HttpMethod.PUT,requestEntity, Department.class);
    		printDepData(departmentResponse.getBody());
    		
    		departmentId = departmentResponse.getBody().getDepId();
    			
    	}
    	@Test(dependsOnMethods = { "testCreateDepartment2" })
    	public void testGetDepartment() {
    		RestTemplate restTemplate = new RestTemplate();
    		Department[] deps = restTemplate.getForObject(SERVER_URI+"/rest/dep/1", Department[].class);
    		printDepData(deps);
    	}
    	
    	@Test(dependsOnMethods = { "testCreateDepartment2" })
    	public void testGetDepartment2() {
    		RestTemplate restTemplate = new RestTemplate();
    		Department[] deps = restTemplate.getForObject(SERVER_URI+"/rest/dep/"+departmentId, Department[].class);
    		printDepData(deps);
    	}

/*	@SuppressWarnings("unused")
	private static void testCreateDepartment() {
		RestTemplate restTemplate = new RestTemplate();
		Department dep = new Department();
		dep.setDepId(1);dep.setDepname("cse");
		Department response = restTemplate.postForObject(SERVER_URI+DepRestURIConstants.CREATE_DEP, dep, Department.class);
		printDepData(response);
	}

	@SuppressWarnings("unused")
	private static void testGetDepartment() {
		RestTemplate restTemplate = new RestTemplate();
		Department dep = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", Department.class);
		printDepData(dep);
	}
	*/
	public static void printDepData(Department[] deps){
		for (Department dep:deps){
		System.out.println("ID="+dep.getDepId()+",Name="+dep.getDepname()+",CreatedDate="+dep.getCreatedDate());
	}
	}

	public static void printDepData(Department dep){
		System.out.println("ID="+dep.getDepId()+",Name="+dep.getDepname()+",CreatedDate="+dep.getCreatedDate());
	}

}
