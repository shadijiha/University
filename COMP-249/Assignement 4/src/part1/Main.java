//  -----------------------------------------------------
// Assignment #4
// Question: 1
// Written by: Shadi Jiha #40131284
//  -----------------------------------------------------

package part1;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		// write your code here

		TextFile file = new TextFile("A4 Text Files/PersonOfTheCentury.txt");
		file.removeBadChars();
		file.parse();

		// Pronte the user
		System.out.print("\nPlease enter a file name > ");
		String filename = scanner.next();

		if (filename.equals(""))
			filename = "SubDictionary.txt";

		file.exportToFile(filename);

		System.out.println("Exported to " + new File(filename).getAbsolutePath());
	}
}
