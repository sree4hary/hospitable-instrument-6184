package com.srs.useCases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.srs.utility.DBUtil;

abstract class User {
	String name;
	String userTyp;
	
	abstract void showMethods();
	
	void getUser() {
		System.out.println("Current User: " + this.name);
	}
	
	void viewCourses() {
		//code here
	}
	
}

class Guest extends User {
	
	Guest() {
		this.name = "GUEST";
		this.userTyp = "GUEST";
	}
	
	@Override
	void showMethods() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println(" 1.View Courses \n 2.Sign In(for Registered users) \n 3.Sign Up(for new users)");
		System.out.println("-------------------------------------------------");
		System.out.print("Enter your input: ");
		int choice = input.nextInt();
		switch(choice) {
			case 1: viewCourses();
				    break;
			case 2: signIn();
				    break;
			case 3: signUp();
				    break;
		}
	}
	
	User signIn() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter email/username: ");
		String userName = input.next();
		System.out.println("Enter passowrd: ");
		String pswd = input.next();
		//////////////////
		
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from USERS where EMAIL = ? and PSWD = ?");
			ps.setString(1, userName);
			ps.setString(2, pswd);
			ResultSet rs = ps.executeQuery();
			String userId, email, userTyp, name;
			if(rs.next()) {
				userId = rs.getString("USERID");
				email = rs.getString("EMAIL");
				pswd = rs.getString("PSWD");
				userTyp = rs.getString("USR_TYP");
				name = rs.getString("NAME");
				if(userTyp == "ADMIN") {
					return new Admin(userId, email, pswd, userTyp, name);
				} else return new Student(userId, email, pswd, userTyp, name);
			} else System.out.println("User not found.");
		} catch(SQLException e) {
			e.printStackTrace();
		} return this;	
	}
	
	void signUp() {
		
	}
	
}

class Admin extends User {
	
	String userId, email, pswd;
	
	Admin(String userId, String email, String pswd, String userTyp, String name) {
		this.userId = userId;
		this.email = email;
		this.pswd = pswd;
		this.userTyp = userTyp;
		this.name = name;
	}
	
	@Override
	void showMethods() {
		// TODO Auto-generated method stub
	}
	
	Guest signOut() {
		return new Guest();
	}
	
}

class Student extends User {
	
	Student(String userId, String email, String pswd, String userTyp, String name) {
		//code here
	}
	
	@Override
	void showMethods() {
		// TODO Auto-generated method stub
		
	}
	
}



//THE MAIN CLASS
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
