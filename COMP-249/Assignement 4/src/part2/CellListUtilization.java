//  -----------------------------------------------------
// Assignment #4
// Question: 2
// Written by: Shadi Jiha #40131284
//  -----------------------------------------------------

package part2;

import java.io.*;
import java.util.*;

public class CellListUtilization {

	public static void main(String[] args) {

		final Scanner scan = new Scanner(System.in);

		CellList first = new CellList();
		CellList second = new CellList();

		// Reading the Cell_Info.txt
		final String filename = "Cell_Info.txt";
		readFile("A4 Text Files/" + filename, first);

		System.out.printf("The current size of the list is %d. Here are the contents of the list\n", first.size());
		System.out.println("====================================================================");

		first.showContents();

		// Test all the methodes
		boolean ok = false;
		while (!ok) {

			System.out.print("\n\nEnter a command (!search, !insert, !delete, !cloneList, !exit, !showList or !help) > ");
			String command = scan.next();

			switch (command) {
				case "!search":
					searchForACellPhone(first, scan);
					break;
				case "!insert":
					insertACellPhone(first, scan);
					break;
				case "!delete":
					deleteFromList(first, scan);
					break;
				case "!cloneList":
					cloneList(first, second, scan);
					break;
				case "!exit":
					ok = true;
					break;
				case "!showList":
					first.showContents();
					break;
				case "!help":
					showHelpOptions();
					break;
				default:
					System.out.println("Please enter a valid command!");
					break;
			}

		}

		//================== CLOSE RESOURCES ===================
		scan.close();
	}

	private static void searchForACellPhone(CellList list, Scanner scan) {
		System.out.println("\nPlease enter the serial number of the cellphone you wish to lookup for:");
		long serial_number = getLongInput(scan);

		CellList.CellNode cell = list.find(serial_number);
		if (cell == null) {
			System.out.println("No cellphone has " + serial_number + " as a serial number.");
		} else {
			System.out.println("You searched for: " + cell.getCellPhone());
		}

		System.out.println("Number of iterations taken to find " + serial_number + ": " + CellList.LAST_ITERATION_COUNT.value());
	}

	private static void insertACellPhone(CellList list, Scanner scan) {

		System.out.println("At which index you want to insert the new CellPhone > ");
		int index = (int) getLongInput(scan);

		System.out.print("Please enter the information of the Cellphone in the following form:  \"serialNumber brand price year\" > ");

		CellPhone phone = null;
		try {
			phone = new CellPhone(scan.nextLong(), scan.next(), scan.nextDouble(), scan.nextInt());
		} catch (Exception e) {
			System.out.println("Please enter valid inputs!");
		}

		if (index == 0) {
			list.addToStart(phone);
		} else {
			list.insertAtIndex(phone, index);
		}
	}

	private static void deleteFromList(CellList list, Scanner scan) {

		System.out.println("At which index you want to delete the CellPhone > ");
		int index = (int) getLongInput(scan);

		System.out.println(list.get(index).getCellPhone().toString() + " will be deleted.");
		list.deleteFromIndex(index);
	}

	private static void cloneList(CellList list, CellList clone, Scanner scan) {

		clone = new CellList(list);

		System.out.print("The list has been cloned. Type '!view' to view the cloned list > ");
		String command = scan.next();

		if (command.equals("!view"))
			clone.showContents();
	}

	private static void showHelpOptions() {
		System.out.println("Here are all the commands supported by the program:");
		System.out.println("\t!search: used to search an element inside the CellList.");
		System.out.println("\t!insert: used to insert a new CellPhone object to the CellList.");
		System.out.println("\t!delete: used to delete an element from the CellList.");
		System.out.println("\t!cloneList: used to clone the whole CellList into another one.");
		System.out.println("\t!exit: used to Exit the current program.");
		System.out.println("\t!showList: used to show the content of the CellList.");
		System.out.println("\t!help: you surely know what this command does :D");
	}

	public static void readFile(String filename, CellList target) {

		try {
			Scanner scan = new Scanner(new FileInputStream(filename));

			while (scan.hasNextLine()) {

				CellPhone phone = new CellPhone(
						scan.nextLong(),
						scan.next(),
						scan.nextDouble(),
						scan.nextInt()
				);

				// Only add if it is unique cellphone
				if (!target.contains(phone.getSerialNum()))
					target.addToStart(phone);

			}

			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static long getLongInput(Scanner scanner) {

		long num = 0L;
		boolean ok = false;
		while (!ok) {
			String input = "";
			try {
				System.out.print("Please enter a valid number > ");
				input = scanner.next();
				num = Long.parseLong(input.trim());
				ok = true;
			} catch (NumberFormatException e) {
				System.out.printf("'%s' is not a valid input!\n", input);
			}
		}
		return num;
	}
}
