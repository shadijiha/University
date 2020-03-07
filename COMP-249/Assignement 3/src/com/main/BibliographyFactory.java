/**
 *
 */
package com.main;

import com.exceptions.FileInvalidException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author shadi
 *
 */

public class BibliographyFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO: XD

		try {

			// Initialize the parser
			for (int file_number = 1; file_number <= 10; file_number++) {

				BibFileParser parser = new BibFileParser("Comp249_W20_Assg3_Files/Latex" + file_number + ".bib");

				// Get the passed data
				ArticleData[] data = parser.parse();

				// Build the IEEE format
				StringBuilder whole_file = new StringBuilder();
				for (var article : data) {

					String temp = String.format("%s \"%s\", %s, vol. %s, no. %d, p. %s, %s %d\n\n", article.getAuthor(),
							article.getTitle(), article.getJournal(), article.getVolume(), article.getNumber(),
							article.getPages(), article.getMonth(), article.getYear());
					whole_file.append(temp);
				}

				// Export the file
				writeToFile("IEEE" + file_number + ".json", whole_file.toString());
			}

		} catch (FileNotFoundException | FileInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static <T> void print(T v) {
		System.out.println(v);
	}

	public static void writeToFile(String filename, String str) {

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(new File(filename));

			writer.print(str);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}

	public static Scanner openFile(String path) throws FileNotFoundException {

		File file = new File(path);
		Scanner scan = new Scanner(file);

		return scan;
	}

	public static void closeFile(Scanner scan) {
		scan.close();
	}

}
