package com.srs.useCases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.srs.models.Course;
import com.srs.utility.DBUtil;

public abstract class User {
	
	String name, userTyp, userId, email, pswd;
	
	abstract void showMethods();
	
	void getUser() {
		System.out.println("Current User: " + this.name);
	}
	
	public void viewCourses(){
		List <Course> courses = new ArrayList<>();
		
				Course course = null;
				try(Connection conn = DBUtil.getConnection()) {
					PreparedStatement ps = conn.prepareStatement("select * from COURSES");
					ResultSet rs = ps.executeQuery();
					System.out.println("-------------------------------------------------");
					System.out.println("| COURSE ID | COURSE NAME | DESCRIPTION | FEE |");
					while(rs.next()) {
						int cId = rs.getInt("COURSEID");
						String cN = rs.getString("COURSE");
						String cDesc = rs.getString("COURSE_DESC");
						int cFee = rs.getInt("FEE");
//						System.out.println("r: " + ", n: " + n + ", a: " + a + ", m: " + m);
						courses.add(new Course(cId, cN, cDesc, cFee));
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				
				for (int i = 0; i < courses.size(); i++) {
					System.out.println(courses.get(i));
				}
	}
	
	public void sorry() {
		System.out.println("Sorry! The feature is under construction.");
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", userTyp=" + userTyp + ", userId=" + userId + ", email=" + email + ", pswd="
				+ pswd + "]";
	}
	
}
