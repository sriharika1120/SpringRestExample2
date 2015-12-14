 package com.journaldev.spring;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.controller.DepRestURIConstants;
import com.journaldev.spring.model.Department;

public class TestSpringRestExampleDepartment {

	public static final String SERVER_URI = "http://localhost:9090/SpringRestExample";

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private static void testGetAllDepartments() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Employee> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> dep = restTemplate.getForObject(SERVER_URI+DepRestURIConstants.GET_ALL_DEP, List.class);
		System.out.println(dep.size());
		for(LinkedHashMap map : dep){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	@SuppressWarnings("unused")
	private static void testCreateDepartment() {
		RestTemplate restTemplate = new RestTemplate();
		Department dep = new Department();
		dep.setDepId(1);dep.setDepname("Pankaj Kumar");
		Department response = restTemplate.postForObject(SERVER_URI+DepRestURIConstants.CREATE_DEP, dep, Department.class);
		printEmpData(response);
	}

	@SuppressWarnings("unused")
	private static void testGetDepartment() {
		RestTemplate restTemplate = new RestTemplate();
		Department dep = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", Department.class);
		printEmpData(dep);
	}
	
	public static void printEmpData(Department dep){
		System.out.println("ID="+dep.getDepId()+",Name="+dep.getDepname()+",CreatedDate="+dep.getCreatedDate());
	}
}
