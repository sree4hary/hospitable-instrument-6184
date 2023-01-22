package com.srs.useCases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.srs.utility.DBUtil;

//THE MAIN CLASS
public class Main {
	
	static User currentUser;
	
	public static void main(String[] args) {
		currentUser = new Guest();
		Scanner input = new Scanner(System.in);
		System.out.println("       CODERS' ACADEMY - STUDENT MANAGEMENT SYSTEM     ");
		System.out.println("===============================================");
		while(currentUser != null) {
			System.out.println("---------------------MENU------------------------");
			System.out.println("Current user: " + currentUser.name + " |     | " + "User Type: " + currentUser.userTyp);
			System.out.println("-------------------------------------------------");
			currentUser.showMethods();
			System.out.println("-------------------------------------------------");
			System.out.print("Do you wish to continue? If so, enter 1. Else, enter any other number: ");
			int x = input.nextInt();
			if(x != 1) {
				currentUser = null;
			}
		} System.out.println("Program exited");
		input.close();
	}
	
}

