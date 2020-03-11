// -----------------------------------------------------
// Assignment #3
// Question:
// Written by: Shadi Jiha #40131284
// -----------------------------------------------------

package com.main;

import com.exceptions.FileInvalidException;

import java.io.*;

/**
 * @author shadi
 */

public class BibliographyFactory {

	public static final int NUMBER_OF_FILES = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO: XD
		System.out.println("\nWelcome to BibliographyFactory!\n");

		// Clear the output directory
		clearDirectory();

		// Create/open all IEEE1.json to IEEE10.json, ACM1.json to ACM10.json, and NJ1.json to NJ10.js
		// Generate the bibliography files
		processFilesForValidation();

		// Close all input files

	}

	/**
	 * This methods creates the parser and builds the files
	 */
	public static void processFilesForValidation() {

		// IMPORTANT:
		// All the parsing is happening in "BibFileParser" class.
		// It is better that way than putting everything in 1 method (not good software design)

		// Initialize the parser
		for (int i = 0; i < NUMBER_OF_FILES; i++) {

			// ALL THE PRSING ENGIN IS INSIDE THE BibFileParser CLASS
			BibFileParser parser = new BibFileParser("Source Bib files/Latex" + (i + 1) + ".bib");

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

					// Build the IEEE format
					String temp = String.format("%s \"%s\", %s, vol. %s, no. %d, p. %s, %s %d\n\n", article.getAuthor(),
							article.getTitle(), article.getJournal(), article.getVolume(), article.getNumber(),
							article.getPages(), article.getMonth(), article.getYear());
					whole_IEEE_file.append(temp);

					// Build the ACM format
					String ACM = String.format("%s. %d. %s. %s. %s, %d (%d), %s. DOI:https://doi.org/%s\n\n", article.getAuthor(), article.getYear(), article.getTitle(), article.getJournal(), article.getVolume(),
							article.getNumber(), article.getYear(), article.getPages(), article.getDoi());
					whole_ACM_file.append(ACM);

					// Build the NJ format
					String NJ = String.format("%s. %s. %s. %s, %s(%d)\n\n", article.getAuthor(), article.getTitle(), article.getJournal(), article.getVolume(), article.getPages(), article.getYear());
					whole_NJ_file.append(NJ);
				}

				// Export the file
				// Only export file if no issues were found
				writeToFile("output/IEEE" + (i + 1) + ".json", whole_IEEE_file.toString());
				writeToFile("output/ACM" + (i + 1) + ".json", whole_ACM_file.toString());
				writeToFile("output/NJ" + (i + 1) + ".json", whole_NJ_file.toString());


			} catch (FileInvalidException e) {
				System.out.println(e.getMessage());
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
			writer.close();
			System.exit(0);
		} finally {
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

	/**
	 * This function closes the open BufferedReader stream
	 *
	 * @param buffer the BufferedReader to close
	 */
	public static void closeFile(BufferedReader buffer) {
		try {
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

		String dir = "output/";

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
