 package com.journaldev.spring.dao;

import java.util.*;
import java.util.Date;
import java.sql.*;

import com.journaldev.spring.model.Department;

public class DepartmentDB {

private static Connection con;

    public DepartmentDB() throws ClassNotFoundException, SQLException {
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
    
    public ArrayList<Department>retrieveDepartmentByDepId(int id) throws SQLException {
        ArrayList<Department> DepList = new ArrayList<Department>();

        String sql = " SELECT * FROM Departmentspring where DepartmentId = ? ";
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, id);

        ResultSet rs = pStmt.executeQuery();
               
        int Depid;
    	String Depname;
		Date createdDate=null;
		java.sql.Date sqlDate = null;


        while (rs.next()) {
        	
        	Depid = rs.getInt("DepartmentId");
            Depname = rs.getString("DepartmentName");
            sqlDate=rs.getDate("DateCreated");
            
            if(sqlDate!=null) {
            	createdDate = new Date(sqlDate.getTime());
            }
            // creating a EmployeeInfo object
            Department DepBean = new Department();
            DepBean.setDepId(Depid);
            DepBean.setDepname(Depname);
            DepBean.setCreatedDate(createdDate);
            DepList.add(DepBean);
        }
        return DepList;
    }
    
    public ArrayList<Department> retrieveAllDepartmentList() throws SQLException {
        ArrayList<Department> DepList = new ArrayList<Department>();

        String sql = " SELECT * FROM Departmentspring";
        PreparedStatement pStmt = con.prepareStatement(sql);

        ResultSet rs = pStmt.executeQuery();

        int Depid;
        String Depname;
        Date createdDate=null;
		java.sql.Date sqlDate = null;


        while (rs.next()) {
        	
        	Depid = rs.getInt("DepartmentId");
            Depname = rs.getString("DepartmentName");
            sqlDate=rs.getDate("DateCreated");
            
            if(sqlDate!=null) {
            	createdDate = new Date(sqlDate.getTime());
            }
         
            // creating a EmployeeInfo object
            Department DepBean = new Department();
            DepBean.setDepId(Depid);
            DepBean.setDepname(Depname);
            DepBean.setCreatedDate(createdDate);
            // adding a bean to arraylist
            DepList.add(DepBean);
        }
        
        return DepList;
    }

    public static void addDepartment(Department department) throws SQLException {
    	
    	String sql = " INSERT INTO Departmentspring VALUES (?,?,?)";
        PreparedStatement pStmt = con.prepareStatement(sql);

        int Depid = department.getDepId();
        String Depname = department.getDepname();
        Date created_date=department.getCreatedDate();
        
        java.sql.Date sqlDate = new java.sql.Date(created_date.getTime());
        pStmt.setInt(1, Depid);
        pStmt.setString(2, Depname);
        pStmt.setDate(3,sqlDate);
        pStmt.executeUpdate();
        

    }
    
    public void deleteDepartment(int  depId) throws SQLException {
        String sql = " DELETE from departmentspring where depid = ?";
        PreparedStatement pStmt = con.prepareStatement(sql);
        
        pStmt.setInt(1, depId);

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

