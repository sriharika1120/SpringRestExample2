package com.journaldev.spring.dao;

import java.util.*;
import java.sql.*;

import com.journaldev.spring.model.Student;

	public class StudentDB {
	private static Connection con;

	    public StudentDB() throws ClassNotFoundException, SQLException {
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
	    
		public ArrayList<Student> retrieveStudentByname(String name) throws SQLException {
	        ArrayList<Student> studentsList = new ArrayList<Student>();

	        String sql = " SELECT * FROM studentspring where name = ? ";
	        PreparedStatement pStmt = con.prepareStatement(sql);
	        
	        pStmt.setString(1, name);

	        ResultSet rs = pStmt.executeQuery();

	        String Name;
	        int Age;
	        String Course;
	        int Year;
	        String Section;
	        
	        while (rs.next()) {
	        	
	        	Name = rs.getString("Name");
	        	Age = rs.getInt("Age");
	        	Course=rs.getString("Course");
	        	Year=rs.getInt("Year");
	        	Section=rs.getString("Section ");
	        	
	            // creating a StudentInfo object
	            Student StuBean = new Student();
	            StuBean.setName(Name);
	            StuBean.setAge(Age);
	            StuBean.setCourse(Course);
	            StuBean.setYear(Year);
	            StuBean.setSection(Section);
	            
	            // adding a bean to arraylist
	            studentsList.add(StuBean);
	        }
	        return studentsList;
	    }
	    
	    public ArrayList<Student>retrieveAllstudentsList() throws SQLException {
	        ArrayList<Student>studentsList = new ArrayList<Student>();

	        String sql = " SELECT * FROM studentspring";
	        PreparedStatement pStmt = con.prepareStatement(sql);

	        ResultSet rs = pStmt.executeQuery();

	        String Name;
	        int Age;
	        String Course;
	        int Year;
	        String Section;
	        
	        while (rs.next()) {
	        	
	        	Name = rs.getString("Name");
	        	Age = rs.getInt("Age");
	        	Course=rs.getString("Course");
	        	Year=rs.getInt("Year");
	        	Section=rs.getString("Section ");
	        	
	            
	        	// creating a StudentInfo object
	            Student StuBean = new Student();
	            StuBean.setName(Name);
	            StuBean.setAge(Age);
	            StuBean.setCourse(Course);
	            StuBean.setYear(Year);
	            StuBean.setSection(Section);
	            
	            // adding a bean to arraylist
	            studentsList.add(StuBean);
	        }
	        return studentsList;
	    }

	    // this method accepts an object of StudentInfo, and stores it into the database
	   
	    public static void addStudent(Student student) throws SQLException {
	        String sql = " INSERT INTO Studentspring VALUES (?,?,?,?,?)";
	        PreparedStatement pStmt = con.prepareStatement(sql);

	        String Name=student.getName();
	        int Age=student.getAge();
	        String Course=student.getCourse();
	        int Year=student.getYear();
	        String Section=student.getSection();
	        
	        pStmt.setString(1, Name);
	        pStmt.setInt(2,Age);
	        pStmt.setString(3,Course);
	        pStmt.setInt(4,Year );
	        pStmt.setString(5,Section);

	        pStmt.executeUpdate();
	      
	    }
	    
	    
	    public static void deleteStudent(String Name) throws SQLException {
	        String sql = " DELETE from studentspring where Name = ?";
	        PreparedStatement pStmt = con.prepareStatement(sql);
	        
	        pStmt.setString(1, Name);

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