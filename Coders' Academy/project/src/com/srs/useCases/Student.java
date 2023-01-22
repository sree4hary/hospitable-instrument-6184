package com.srs.useCases;

import java.util.Scanner;

public class Student extends User {
	
	Student(String userId, String email, String pswd, String userTyp, String name) {
		this.userId = userId;
		this.email = email;
		this.pswd = pswd;
		this.userTyp = userTyp;
		this.name = name;
	}
	
	@Override
	void showMethods() {
		Scanner input = new Scanner(System.in);
		System.out.println(" 1.View courses \n 2.update profile \n 3.View courses list and seats available \n 4. Sign Out");
		System.out.println("-------------------------------------------------");
		System.out.print("Enter your input: ");
		int choice = input.nextInt();
		switch(choice) {
		case 1: viewCourses();
		break;
		case 2: updateProfile();
		break;
		case 3: viewAvbleSeats();
		break;
		case 4: signOut();
		break;
		}
	}
	
	private void viewAvbleSeats() {
		sorry();
	}

	private void updateProfile() {
		sorry();
	}

	void signOut() {
		Main.currentUser = new Guest();
		System.out.println("The user logged out successfully.");
	}
	
}