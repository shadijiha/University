// -----------------------------------------------------
// Assignment #1
// Question: Question 1, Part I
// Written by: Shadi Jiha #40131284
// -----------------------------------------------------

/**
 * Name(s) and ID(s): Shadi Jiha #40131284
 * COMP249
 * Assignment # 1
 * Due Date: January 31th 2020
 * */

package com.main;

import java.util.Scanner;

public final class Main {

	public static void main(String args[])	{
		// TODO: Test the code
		final int MAX_APPLIANCES = 10;
		int option = 0;

		Appliance[] inventory = new Appliance[MAX_APPLIANCES];
		Scanner scan = new Scanner(System.in);

		while (option != 5)	{
			System.out.println("Welcome to inventory Software!\n");

			System.out.println("What do you want to do?");
			System.out.println("\t1. Enter new appliances (password required)");
			System.out.println("\t2. Change information of an appliance (password required)");
			System.out.println("\t3. Display all appliances by a specific brand");
			System.out.println("\t4. Display all appliances under a certain price");
			System.out.println("\t5. Quit");

			// Get user input
			// Validate if the option entered is a number from 1 to 5
			String tempInput = "";
			do {
				System.out.print("\nPlease enter your choice > ");
				tempInput = scan.next();

				if (!isNumeric(tempInput))	{
					// If the input if not a number
					System.out.println(tempInput + " is not a number!");
					tempInput = "-1";
				} else if(Integer.parseInt(tempInput) < 1 || Integer.parseInt(tempInput) > 5)	{
					// if the input is < 1 or > 5
					System.out.println("Choice cannot be less than 1 or greater than 5");
				}

			} while(Integer.parseInt(tempInput) < 1 || Integer.parseInt(tempInput) > 5);

			// Convert input to number if it is valid
			option = Integer.parseInt(tempInput);

			// I don't know what to write here xD
			switch (option)	{

			}
		}

		//============ RELEASE RESOURCES ============
		scan.close();
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
