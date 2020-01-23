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

	private static final int MAX_APPLIANCES = 10;        // The max capacity of the inventory

	private static final String PASSWORD = "c249";       // The program password
	private static final short MAX_ATTEMPTS = 3;         // The max attempts to get the right password because program restart
	private static final short ABSOLUTE_MAX_ATTEMPTS = MAX_ATTEMPTS * 4;    // The max attempts before program exits
	private static int attempts;

	public static void main(String[] args) {

		// TODO: do some buffer clearing for the Scanner scan
		// TODO: There is a bug @ line 191 in the while loop

		// Constant variables


		// Variable variables :)
		int option = 0;
		attempts = 0;

		Appliance[] inventory = new Appliance[MAX_APPLIANCES];    // The inventory
		Scanner scan = new Scanner(System.in);                    // Scanner to get input

		// Welcome banner
		println("Welcome to inventory Software!");

		//============ Main program Logic ========================
		while (option != 5) {

			// Display the options list
			println("\n\n*************** MAIN MENU ***************\n");
			println("What do you want to do?");
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
							tempType = scan.nextLine();

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
						String tempBrand = scan.nextLine();

						// Get price and validate
						double tempPrice;
						do {
							print("\t Enter Price: $");
							tempPrice = getNumberInput(scan);
						} while (tempPrice < 0.0);

						// Push that appliance to the inventory
						Appliance tempObject = new Appliance(tempType, tempBrand, tempPrice);
						inventory[Appliance.findNumberOfCreatedAppliances() + i - 1] = tempObject;

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
					int validApplianceIndex = -1;
					do {
						print("Enter the serial number of the Appliance you wish to edit: ");
						long desiredSN = (long) getNumberInput(scan);
						if (isValidSN(inventory, desiredSN) == -1) {
							println("\nNo item possess the ", desiredSN, " serial number. Do you wish to enter another SN?");
							print("\t1. Yes\n\t2. No\n > ");
							tempChoice = (int) getNumberInput(scan);
							if (tempChoice > 1) {
								break SwitchLabel;
							}
						} else {
							validApplianceIndex = isValidSN(inventory, desiredSN);
						}
					} while (tempChoice == 1 && validApplianceIndex == -1);

					// If the serial number is ok show options to the user
					println("You have selected: ", inventory[validApplianceIndex].toString());
					println("What information would like to change?");
					println("\t1. brand\n\t2. type\n\t3. price\n\t4. Quit");
					print("Enter your choice > ");

					// Get the choice of the user
					int changeChoise = (int) getNumberInput(scan);
					switch (changeChoise) {
						case 1:
							print("\nEnter the new Brand > ");
							String _newBrand = scan.nextLine();
							inventory[validApplianceIndex].setBrand(_newBrand);
							println("New brand has been set!");
							break;
						case 2:

							String _newType = "";

							// Verify that is is a valid type
							while (!Appliance.isValide(_newType)) {
								print("\nEnter the new type > ");
								_newType = scan.nextLine();

								if (!Appliance.isValide(_newType)) {
									println(_newType, " is not a valid appliance type!");
								}
							}

							inventory[validApplianceIndex].setType(_newType);
							println("New type has been set!");

							break;
						case 3:
							print("\nEnter the new price > $");
							double _newPrice = getNumberInput(scan);
							inventory[validApplianceIndex].setPrice(_newPrice);
							println("New price has been set!");

							break;
						default:
							break;
					}

					break;
				case 3:
					print("Enter that brand you want to search for > ");

					// Get user input
					String targetBrand = scan.nextLine();

					// Get the matching result
					Appliance[] searchResults = findByBrand(targetBrand, inventory);
					println("Found the following results:");
					// Print all Appliances that aren't null
					for (Appliance element : searchResults) {
						if (element != null) {
							println(element.toString());
						}
					}

					break;
				case 4:
					print("Enter the maximum price to search for > ");

					// Get user input
					double targetPrice = getNumberInput(scan);

					// Get the matching results
					Appliance[] priceSearchResults = findByPrice(targetPrice, inventory);
					println("Found the following results:");
					// Print all Appliances that aren't null
					for (Appliance element : priceSearchResults) {
						if (element != null) {
							println(element.toString());
						}
					}

					break;
			}
		}

		// Display closing message
		println("\nThank you for using our inventory application!");

		//============ RELEASE RESOURCES ============
		scan.close();

	}

	/***
	 * THis function searches for all the Appliance that match a specific brand in an Appliance array
	 * @param _brand The brand you want to search for
	 * @param array the array in which you want to seach
	 * @return Returns a partially filled array with all the matched result
	 */
	public static Appliance[] findByBrand(String _brand, Appliance[] array) {
		Appliance[] temp = new Appliance[MAX_APPLIANCES];

		int i = 0;
		for (Appliance element : array) {
			if (element != null && _brand.equalsIgnoreCase(element.getBrand())) {
				temp[i] = new Appliance(element);
				i++;
			}
		}

		return temp;
	}

	/***
	 * THis function searches for all the Appliance that have a price less than the specified price
	 * @param _price The max price you want to search for
	 * @param array the array in which you want to search
	 * @return Returns a partially filled array with all the matched result
	 */
	public static Appliance[] findByPrice(double _price, Appliance[] array) {
		Appliance[] temp = new Appliance[MAX_APPLIANCES];

		int i = 0;
		for (Appliance element : array) {
			if (element != null && element.getPrice() < _price) {
				temp[i] = new Appliance(element);
				i++;
			}
		}

		return temp;
	}

	/***
	 * This function validates the password entered by the user
	 * @param s The Scanner stream Object
	 * @return IMPORTANT: Returns 0 if the password is ok
	 * 						Returns 1 if the password fail attempts has exceeded MAX_ATTEMPTS
	 */
	public static int isValidPassword(Scanner s) {
		// Get the password
		String tempPass = "";
		// Keep asking for password while it is incorrect
		do {
			print("Enter the password please: ");
			tempPass = s.nextLine();
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
	 * @return Returns index of the matched object if any OR returns -1 if none found
	 */
	public static int isValidSN(Appliance[] array, long serialNumber) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].getSerialNumber() == serialNumber) {
				return i;
			}
		}
		return -1;
	}

	/***
	 * This function prints a group of stuff followed by a new line
	 * @param args The stuff you want to print
	 * @param <T> Any
	 */
	public static <T> void println(T... args) {
		for (T temp : args) {
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
		for (T temp : args) {
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
		temp = scanner.nextLine();

		// TODO: Tranform this to a while loop
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
