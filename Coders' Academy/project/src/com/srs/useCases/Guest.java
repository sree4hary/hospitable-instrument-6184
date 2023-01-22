package com.srs.useCases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.srs.utility.DBUtil;

public class Guest extends User {
	
	Guest() {
		this.name = "GUEST";
		this.userTyp = "GUEST";
		this.email = null;
		this.userId = null;
		this.pswd = null;
	}
	
	@Override
	void showMethods() {
		Scanner input = new Scanner(System.in);
		System.out.println(" 1.View Courses \n 2.Sign In(for Registered users) \n 3.Sign Up(for new users) \n 4.Exit");
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
		System.out.println("===================================================");
		System.out.println("-----------------USER SIGN IN---------------------");
		System.out.println("-------------------------------------------------");
		System.out.print("Enter email/username: ");
		String userName = input.next();
		System.out.print("Enter passowrd      : ");
		String pswd = input.next();
		//////////////////
		System.out.println("-------------------------------------------------");
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
				System.out.println("Hi! "+name+". Your Login successful!");
				if(userTyp.equals("ADMIN")) {
					Main.currentUser = new Admin(userId, email, pswd, userTyp, name);
				} else {
					Main.currentUser = new Student(userId, email, pswd, userTyp, name);
				}
			} else System.out.println("User not found.");
		} catch(SQLException e) {
			e.printStackTrace();
		} return this;	
	}
	
	void signUp() {
		sorry();
	}
	
}