package com.journaldev.spring.dao;

import java.util.*;
import java.util.Date;
import java.sql.*;

import com.journaldev.spring.model.Employee;

public class EmployeeDB {

private static Connection con;

    public EmployeeDB() throws ClassNotFoundException, SQLException {
        establishConnection();
    }

    // method used to establish connection with database
    private void establishConnection() throws ClassNotFoundException, SQLException {
    		
		Class.forName("oracle.jdbc.driver.OracleDriver");
        String conUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String uname = "system";
		String pwd = "sathar205";
        con = DriverManager.getConnection(conUrl,uname,pwd);
    }
    
	public ArrayList<Employee> retrieveEmployeesByEmpId(int id) throws SQLException {
        ArrayList<Employee> empList = new ArrayList<Employee>();

        String sql = " SELECT * FROM employeespring where EmpId = ? ";
        PreparedStatement pStmt = con.prepareStatement(sql);
        
        pStmt.setInt(1, id);

        ResultSet rs = pStmt.executeQuery();

        int empid;
        String empname;
		Date created_date = null;;
		java.sql.Date sqlDate = null;
        
        while (rs.next()) {
        	
            empid = rs.getInt("EmpId");
            empname = rs.getString("EmployeeName");
            sqlDate=rs.getDate("DateCreated");
            
            if(sqlDate!=null) {
            	created_date = new Date(sqlDate.getTime());
            }
           
            // creating a EmployeeInfo object
            Employee empBean = new Employee();
            empBean.setId(empid);
            empBean.setName(empname);
            empBean.setCreatedDate(created_date);
            // adding a bean to arraylist
            empList.add(empBean);
        }
        return empList;
    }
    
    public ArrayList<Employee> retrieveAllEmployeesList() throws SQLException {
        ArrayList<Employee> empList = new ArrayList<Employee>();

        String sql = " SELECT * FROM employeespring";
        PreparedStatement pStmt = con.prepareStatement(sql);

        ResultSet rs = pStmt.executeQuery();

        int empid;
        String empname;
        Date created_date = null;;
		java.sql.Date sqlDate = null;
        
        while (rs.next()) {
        	
            empid = rs.getInt("EmpId");
            empname = rs.getString("EmployeeName");
            sqlDate=rs.getDate("DateCreated");
            
            if(sqlDate!=null) {
            	created_date = new Date(sqlDate.getTime());
            }
            
            // creating a EmployeeInfo object
            Employee empBean = new Employee();
            empBean.setId(empid);
            empBean.setName(empname);
            
            empBean.setCreatedDate(created_date);
            // adding a bean to arraylist
            empList.add(empBean);
        }
        
        return empList;
    }

    // this method accepts an object of EmployeeInfo, and stores it into the database
    public static void addEmployee(Employee employee) throws SQLException {
        String sql = " INSERT INTO employeespring VALUES (?,?,?)";
        PreparedStatement pStmt = con.prepareStatement(sql);

        int empid = employee.getId();
        String empname = employee.getName();
        Date created_date=employee.getCreatedDate();
        
        java.sql.Date sqlDate = new java.sql.Date(created_date.getTime());
        
        pStmt.setInt(1, empid);
        pStmt.setString(2, empname);
        pStmt.setDate(3,sqlDate);
        pStmt.executeUpdate();
      
    }
    
    
    public static void deleteEmployee(int  empId) throws SQLException {
        String sql = " DELETE from employeespring where empid = ?";
        PreparedStatement pStmt = con.prepareStatement(sql);
        
        pStmt.setInt(1, empId);

        pStmt.executeUpdate();
      
    }

    // overriding finalize method to release acquired resources
    @Override
	public void finalize() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException sqlex) {
            System.out.println(sqlex);
        }
    }

}
