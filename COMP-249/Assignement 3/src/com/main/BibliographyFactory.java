// -----------------------------------------------------
// Assignment #3
// Question:
// Written by: Shadi Jiha #40131284
// -----------------------------------------------------

package com.main;

import com.exceptions.FileInvalidException;

import java.io.*;
import java.util.Scanner;

/**
 * @author shadi
 */

public class BibliographyFactory {

	public static final int NUMBER_OF_FILES = 10;
	public static final int MAX_CHANCES = 2;
	public static int chance = 0;

	public static int CORRUPTED_FILES = 0;
	public static final int TOTAL_FILES = 10;

	public static final String OUT_PATH = "";
	public static final String IN_PATH = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Variables
		Scanner scan = new Scanner(System.in);

		// TODO: XD
		System.out.println("\nWelcome to BibliographyFactory!\n");

		// Clear the output directory
		clearDirectory();

		// Create/open all IEEE1.json to IEEE10.json, ACM1.json to ACM10.json, and NJ1.json to NJ10.js
		// Generate the bibliography files
		processFilesForValidation();

		// Display how many files created
		System.out.printf("\nA total of %d files were invalid, and could not be processed. All other %d \"Valid\" files have been created.\n", CORRUPTED_FILES, TOTAL_FILES - CORRUPTED_FILES);
		System.out.println("================================");

		// Detect if file exists
		BufferedReader reader = null;
		while (chance < MAX_CHANCES) {
			try {
				System.out.print("Please enter the name of one of the files that you need to review: > ");
				String file_to_review = scan.next();
				reader = openFile(file_to_review);

				// File has been open
				System.out.println("\nHere are the contents of the successfully created File: \n" + file_to_review);
				System.out.println(readFile(reader));

			} catch (Exception e) {
				chance++;
				System.out.println("Could not open input file. File does not exist. possible it could not be created!\n");

				if (chance < MAX_CHANCES) {
					System.out.println("However, you will be allowed another change to enter another file name.");
				} else {
					System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
					closeFile(reader);
					System.exit(0);
				}

			} finally {
				closeFile(reader);
			}
		}
	}

	/**
	 * This methods creates the parser and builds the files
	 */
	public static void processFilesForValidation() {

		// IMPORTANT:
		// All the parsing is happening in "BibFileParser" class.
		// It is better that way than putting everything in 1 method (better software design)

		// Initialize the parser
		for (int i = 0; i < NUMBER_OF_FILES; i++) {

			// ALL THE PRSING ENGIN IS INSIDE THE BibFileParser CLASS
			BibFileParser parser = new BibFileParser(IN_PATH + "Latex" + (i + 1) + ".bib");

			// All the data will be stored here
			ArticleData[] data;

			// Build bibliography
			StringBuilder whole_IEEE_file = new StringBuilder();
			StringBuilder whole_ACM_file = new StringBuilder();
			StringBuilder whole_NJ_file = new StringBuilder();

			try {
				data = parser.parse();

				for (var article : data) {

					if (article.isNull())
						continue;

					// Format the authors
					String authorNJ = "";
					String authorIEEE = "";
					int index = 0;
					for (String temp : article.getAuthors()) {
						authorNJ += temp;
						authorIEEE += temp;

						if (index != article.getAuthors().length - 1) {
							authorNJ += " & ";
							authorIEEE += ", ";
						}

						index++;
					}

					// Build the IEEE format
					String temp = String.format("%s. \"%s\", %s, vol. %s, no. %d, p. %s, %s %d\n\n", authorIEEE,
							article.getTitle(), article.getJournal(), article.getVolume(), article.getNumber(),
							article.getPages(), article.getMonth(), article.getYear());
					whole_IEEE_file.append(temp);

					// Build the ACM format
					String ACM = String.format("%s et al. %d. %s. %s. %s, %d (%d), %s. DOI:https://doi.org/%s\n\n", article.getAuthors()[0], article.getYear(), article.getTitle(), article.getJournal(), article.getVolume(),
							article.getNumber(), article.getYear(), article.getPages(), article.getDoi());
					whole_ACM_file.append(ACM);

					// Build the NJ format
					String NJ = String.format("%s. %s. %s. %s, %s(%d)\n\n", authorNJ, article.getTitle(), article.getJournal(), article.getVolume(), article.getPages(), article.getYear());
					whole_NJ_file.append(NJ);
				}

				// Export the file
				// Only export file if no issues were found
				writeToFile(OUT_PATH + "IEEE" + (i + 1) + ".json", whole_IEEE_file.toString());
				writeToFile(OUT_PATH + "ACM" + (i + 1) + ".json", whole_ACM_file.toString());
				writeToFile(OUT_PATH + "NJ" + (i + 1) + ".json", whole_NJ_file.toString());


			} catch (FileInvalidException e) {
				System.out.println(e.getMessage());
				CORRUPTED_FILES++;
			}
		}
	}

	/**
	 * This function writes a string to a file using <code>java.util.Scanner</code>
	 *
	 * @param filename The filename
	 * @param str      The string to write
	 */
	public static void writeToFile(String filename, String str) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(filename));
			writer.print(str);
		} catch (IOException e) {
			System.out.println("Could not open/creat file " + filename + "!");

			if (writer != null)
				writer.close();

			System.exit(0);
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * This function opens a Buffered reader stream
	 *
	 * @param path The path to the file
	 * @return Returns the open stream
	 * @throws FileNotFoundException Throws if the file doesn't exist
	 */
	public static BufferedReader openFile(String path) throws FileNotFoundException {

		File file = new File(path);

		return new BufferedReader(new FileReader(file));
	}

	public static String readFile(BufferedReader reader) throws IOException {
		StringBuilder builder = new StringBuilder();

		String str = "";
		// TODO: Put the number counter: E.g.
		//  [1] blah blah blah
		//  [2] blah blah blah
		int counter = 1;
		while (str != null) {
			str = reader.readLine();

			if (str != null && !str.replaceAll("\\s", "").equalsIgnoreCase("")) {
				builder.append("[").append(counter).append("]\t");
				counter++;
				builder.append(str).append("\n\n");
			}
		}

		return builder.toString();
	}

	/**
	 * This function closes the open BufferedReader stream
	 *
	 * @param buffer the BufferedReader to close
	 */
	public static void closeFile(BufferedReader buffer) {
		try {
			if (buffer != null)
				buffer.close();
		} catch (IOException e) {
			System.out.println("Couldnot close buffer");
			e.printStackTrace();
		}
	}

	/**
	 * This function clears all "IEEE1.json to IEEE10.json, ACM1.json to ACM10.json, and NJ1.json to
	 * NJ10.json" in the "output/" folder
	 */
	public static void clearDirectory() {

		String dir = OUT_PATH;

		for (int i = 1; i <= NUMBER_OF_FILES; i++) {

			File IEEE = new File(dir + "IEEE" + i + ".json");
			File ACM = new File(dir + "ACM" + i + ".json");
			File NJ = new File(dir + "NJ" + i + ".json");

			if (IEEE.exists())
				IEEE.delete();
			if (ACM.exists())
				ACM.delete();
			if (NJ.exists())
				NJ.delete();
		}

		System.out.println("Successfully cleared \"" + dir + "\" folder.");
	}
}
