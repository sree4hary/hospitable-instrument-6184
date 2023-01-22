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

public class Admin extends User {
	
	Admin(String userId, String email, String pswd, String userTyp, String name) {
		this.userId = userId;
		this.email = email;
		this.pswd = pswd;
		this.userTyp = userTyp;
		this.name = name;
	}
	
	@Override
	void showMethods() {
		Scanner input = new Scanner(System.in);
		System.out.println(" 1.Add new course \n 2.Update course fee \n 3.Delete course \n 4.Search course info \n 5.Create batch \n 6.Update total seats \n 7.View students batchwise \n 8.View courses \n 9.Sign Out");
		System.out.println("-------------------------------------------------");
		System.out.print("Enter your input: ");
		
		int choice = input.nextInt();
		switch(choice) {
		case 1: addCourse();
		break;
		case 2: updateFee();
		break;
		case 3: deleteCourse();
		break;
		case 4: Course res = searchCourseInfo();
				if(res == null) {
					System.out.println("The course not found for the entered details.");
				} else System.out.println(res);
				break;
		case 5: createBatch();
		break;
		case 6: updateSeats();
		break;
		case 7: viewStudentsBatchwise();
		break;
		case 8: viewCourses();
		break;
		case 9: signOut();
		break;
		}
	}
	
	void printList(List<Object> ls) {
		sorry();
	}
	
	private void viewStudentsBatchwise() {
		sorry();
	}

	private void updateSeats() {
		sorry();
	}

	private void createBatch() {
		sorry();
	}

	private Course searchCourseInfo() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the course name: ");
		String course = input.next();
		Course courseInfo = null;
		
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from COURSES where COURSE = ?");
			ps.setString(1, course);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int cId = rs.getInt("COURSEID");
				String cName = rs.getString("COURSE");
				String courseDesc = rs.getString("COURSE_DESC");
				int fee = rs.getInt("FEE");
				courseInfo = new Course(cId, course, courseDesc, fee);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} return courseInfo;
	}

	private void deleteCourse() {
		sorry();
	}

	private void updateFee() {
		Course res = searchCourseInfo();
		if(res == null) {
			System.out.println("The course not found for the entered details.");
		} else {
			System.out.println("The entered course details: ");
			System.out.println(res);
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the new fee: ");
			int newFee = input.nextInt();
			String course = res.getCourse();
			String message = "Sorry! The fee couldn't be updated.";
			try(Connection conn = DBUtil.getConnection()){
				PreparedStatement ps = conn.prepareStatement("UPDATE COURSES SET FEE = ? WHERE COURSE = ?");
				ps.setInt(1, newFee);
				ps.setString(2, course);
				int x = ps.executeUpdate();
				if(x > 0) {
					message = "The fee has been updated successfully.";
				}
			} catch(SQLException e) {
				e.printStackTrace();
				message = e.getMessage();
			} System.out.println(message);
		}
	}

	private void addCourse() {
		String course = "", course_desc = "";
		int fee = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the Course name: ");
		course = input.next();
		System.out.print("Enter the Course Description: ");
		course_desc = input.next();
//		input.close();
		input.next();
		input = new  Scanner(System.in);
		System.out.print("Enter the Course fee: ");
		fee = input.nextInt();
			String message = "Sorry! The entered course cannot be added.";
			try(Connection conn = DBUtil.getConnection()){
				PreparedStatement ps = conn.prepareStatement("insert into COURSES(COURSE, COURSE_DESC, FEE) values(?, ?, ?)");
				ps.setString(1, course);
				ps.setString(2, course_desc);
				ps.setInt(3, fee);
				int x = ps.executeUpdate();
				if(x > 0) {
					message = "The course has been added successfully.";
				}
			} catch(SQLException e) {
				e.printStackTrace();
				message = e.getMessage();
			} System.out.println(message);
	}

	void signOut() {
		Main.currentUser =  new Guest();
		System.out.println("The user logged out successfully.");
	}
	
}