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
 */

package com.main;

import java.util.Scanner;

public final class Main {

	private static final String PASSWORD = "c249";                 // The program password
	private static final short MAX_ATTEMPTS = 3;                   // The max attempts to get the right password because program restart
	private static final short ABSOLUTE_MAX_ATTEMPTS = MAX_ATTEMPTS * 4;    // The max attempts before program exits
	private static int attempts;

	public static void main(String args[]) {

		// TODO: do some buffer clearing for the Scanner scan

		// Const variables
		final int MAX_APPLIANCES = 10;        // The max capacity of the inventory

		// Variable variables 😊
		int option = 0;
		attempts = 0;

		Appliance[] inventory = new Appliance[MAX_APPLIANCES];    // The inventory
		Scanner scan = new Scanner(System.in);                    // Scanner to get input

		// Welcome banner
		println("Welcome to inventory Software!\n");

		//============ Main program Logic ========================
		while (option != 5) {

			// Display the options list
			println("*************** MAIN MENU ***************\n");
			println("\nWhat do you want to do?");
			println("\t1. Enter new appliances (password required)");
			println("\t2. Change information of an appliance (password required)");
			println("\t3. Display all appliances by a specific brand");
			println("\t4. Display all appliances under a certain price");
			println("\t5. Quit");

			// Get user input
			// Validate if the option entered is a number from 1 to 5
			do {
				print("\nPlease enter your choice > ");
				option = (int) getNumberInput(scan);
				if (option < 1 || option > 5) {
					// if the input is < 1 or > 5
					println("Choice cannot be less than 1 or greater than 5");
				}

			} while (option < 1 || option > 5);

			// I don't know what to write here xD
			// Label switch statement for future reference
			SwitchLabel:
			switch (option) {
				case 1:
					// Verify Password
					if (isValidPassword(scan) == 1) {
						break;
					} else {
						attempts = 0;    // Reset attempts
					}

					// Ask how many Appliances he wants to enter
					print("\nEnter the number of appliances you wish to add: ");
					int desired = (int) getNumberInput(scan);

					// See if there is enough space in the inventory
					if (Appliance.findNumberOfCreatedAppliances() + desired > MAX_APPLIANCES) {
						print("There is not enough space to add ", desired, ". The maximum you can add is ");
						desired = MAX_APPLIANCES - Appliance.findNumberOfCreatedAppliances();
						println(desired, ".");
					}

					// Add the number of appliance desired
					for (int i = 0; i < desired; i++) {
						println("\nPlease complete the data of the appliance #", i + 1);

						// Get type and validate
						String tempType = "";
						do {
							// Stay here while input is not a valid type
							print("\t Enter Type (!types for allowed types): ");
							tempType = scan.next();
							clearBuffer(scan);

							if (tempType.equals("!types")) {
								// Print all the valid types
								print("\t\t");
								printArray(Appliance.appliances, ", ");
							} else if (!Appliance.isValide(tempType)) {
								println("\t\t", tempType, " is not a valid type!");
							}
						} while (!Appliance.isValide(tempType));

						// Get brand
						print("\t Enter Brand: ");
						String tempBrand = scan.next();
						clearBuffer(scan);

						// Get price and validate
						double tempPrice;
						do {
							print("\t Enter Price: $");
							tempPrice = getNumberInput(scan);
						} while (tempPrice < 0.0);
						clearBuffer(scan);

						// Push that appliance to the inventory
						Appliance tempObject = new Appliance(tempType, tempBrand, tempPrice);
						inventory[Appliance.findNumberOfCreatedAppliances() + i] = tempObject;

						// Display the end result
						println("You added ", tempObject.toString(), " to the inventory.\n");
					}

					// Display success message
					println("\nYou have successfully added ", desired, " appliances.");
					break;
				case 2:
					// Verify Password
					if (isValidPassword(scan) == 1) {
						break;
					} else {
						attempts = 0;    // Reset attempts
					}

					// Password is OK proceed
					// Ask the user for the serial number
					// Keep asking until a valid SN or user wants to exit
					int tempChoice = 1;
					do {
						print("Enter the serial number of the Appliance you wish to edit: ");
						long desiredSN = (long) getNumberInput(scan);
						if (isValidSN(inventory, desiredSN) == null) {
							println("\nNo item possess the ", desiredSN, " serial number. Do you wish to enter another SN?");
							print("\t1. Yes\n\t2. No\n > ");
							tempChoice = (int) getNumberInput(scan);
							if (tempChoice > 1) {
								break SwitchLabel;
							}
						}
					} while (tempChoice == 1);


					break;
			}
		}

		// Display closing message
		println("\nThank you for using our inventory application!");

		//============ RELEASE RESOURCES ============
		scan.close();

	}

	/***
	 * This function validates the password entered by the user
	 * @param s The Scanner stream Object
	 * @return IMPORTANT: Returns 0 if the password is ok
	 * 						Returns 1 if the password fail attempts has exceeded MAX_ATTEMPTS
	 */
	public static int isValidPassword(final Scanner s) {
		// Get the password
		String tempPass = "";
		// Keep asking for password while it is incorrect
		do {
			print("Enter the password please: ");
			tempPass = s.next();
			clearBuffer(s);
			attempts++;

			// Display the attempts count
			if (!tempPass.equals(PASSWORD)) {
				print("Wrong password! You have attempted ", attempts, " times. ");
			}

			// If exceeded max attempts
			if (attempts >= ABSOLUTE_MAX_ATTEMPTS) {
				println("Program detected suspicious activities and will terminate immediately!");
				System.exit(-1);
			} else if (attempts % MAX_ATTEMPTS == 0) {
				return 1;    // The password fail attempts has exceeded MAX_ATTEMPTS
			}

		} while (!tempPass.equals(PASSWORD));
		return 0;    // Password OK!
	}

	/***
	 * This function checks if a Appliance exists based on a given serial number
	 * @param array The array of appliance you wish to loop through
	 * @param serialNumber The serial number to match
	 * @return Returns the matched object if any OR returns null if none found
	 */
	public static Appliance isValidSN(Appliance[] array, long serialNumber) {
		for (Appliance temp : array) {
			if (temp != null && temp.getSerialNumber() == serialNumber) {
				return temp;
			}
		}
		return null;
	}

	/***
	 * This function prints a group of stuff followed by a new line
	 * @param args The stuff you want to print
	 * @param <T> Any
	 */
	public static <T> void println(T... args) {
		for (var temp : args) {
			System.out.print(temp);
		}
		System.out.println();
	}

	/***
	 * This function prints a group of stuff WITHOUT A NEW LINE
	 * @param args The stuff you want to print
	 * @param <T> Any
	 */
	public static <T> void print(T... args) {
		for (var temp : args) {
			System.out.print(temp);
		}
	}

	/***
	 * This function prints an array and separates the elements printed by a separator
	 * @param array The array you want to print
	 * @param separator The separator you want between the array elements
	 * @param <T> Any
	 */
	public static <T> void printArray(T[] array, final String separator) {
		for (T temp : array) {
			System.out.print(temp + separator);
		}
	}

	/***
	 * This function gets a Number input from the user and keeps asking until the provided input
	 * is a number
	 * @param scanner The input stream
	 * @return Returns the number input
	 */
	public static double getNumberInput(final Scanner scanner) {
		String temp = "";
		temp = scanner.next();
		clearBuffer(scanner);

		if (!isNumeric(temp)) {
			// If the input if not a number
			println(temp + " is not a number!");
			temp = "-1";
			return -1.0;
		} else {
			return Double.parseDouble(temp);
		}
	}

	/***
	 *  This function clears the Scanner object buffer
	 * @param s The input Scanner stream
	 */
	public static void clearBuffer(final Scanner s) {
		s.nextLine();
	}

	/***
	 * This function determines if a string is a number when parsed or not
	 * @param strNum The string to evaluate
	 * @return Returns TRUE if the string can be converted to a number and false otherwise
	 */
	public static boolean isNumeric(final String strNum) {
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
