// -----------------------------------------------------
// Assignment #3
// Question:
// Written by: Shadi Jiha #40131284
// -----------------------------------------------------

package com.main;

import com.exceptions.FileInvalidException;
import com.main.ArticleData.ArticleDataBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author shadi
 */

public class BibFileParser {

	private String path;
	private String data = "";
	private File file;

	/**
	 *
	 */
	public BibFileParser(String path) {
		// TODO Auto-generated constructor stub
		//filename = path;
		file = new File(path);
		this.path = path;

		// If file doesn't exist exit the program
		if (!file.exists()) {
			System.out.println("Could not open input file Latex" + path + ".bib for reading.\n\nPlease check if file exists! Program will terminate after closing any opened files.");
			System.exit(0);
		}

		readBibFile();
	}

	/**
	 * Reads a Bib file and populates the "data" List attribute
	 */
	private void readBibFile() {

		StringBuilder builder = new StringBuilder();

		// Read the file
		Scanner input = null;
		try {
			input = new Scanner(file);
			while (input.hasNextLine())
				builder.append(input.nextLine()).append('\n');
		} catch (FileNotFoundException e) {
			// If cannot read file, display message then exist
			System.out.println("Could not open input file Latex" + path + ".bib for reading.\n\nPlease check if file exists! Program will terminate after closing any opened files.");
			input.close();
			System.exit(0);
		} finally {
			input.close();
		}

		data = builder.toString();
	}

	public ArticleData[] parse() throws FileInvalidException {

		// Find how many articles are in the file
		String[] all_Articles = data.split("@ARTICLE");
		all_Articles = removeWhiteSpace(all_Articles);

		int number_of_articles = all_Articles.length;

		// Store all ArticleData
		ArticleData[] parsed_result = new ArticleData[number_of_articles];

		// Go though each article
		int line_number = 0; // TODO: Chech if this is working
		int article_number = 0;
		for (var article : all_Articles) {

			ArticleDataBuilder builder = new ArticleDataBuilder();

			String[] lines = article.split(",");

			String bad_characters = "\\{|\\}|\\s";

			// Go over all attribles and data

			for (var line : lines) {

				// Update line number
				line_number++;
				line = line.replaceAll("\\n", ""); // Remove all empty lines
				String[] sperate_attributes = line.split("=");

				// Detect if the line is only a "{" OR "}"
				if (sperate_attributes[0].length() <= 1) {
					continue;
				}

				// If it is an ID
				if (sperate_attributes.length == 1) {
					String id = sperate_attributes[0].replaceAll(bad_characters, "").trim();
					builder.id(id); // Long.parseLong(id)
				} else {

					// Detect invalid stuff
					if (sperate_attributes[1].replaceAll(bad_characters, "").equalsIgnoreCase("")) {
						throw new FileInvalidException(String.format(
								"\"%s\" File is invalid: Field \"%s\" is Empty. Processing stopped at this point. Other empty fields may be present as well!",
								path, sperate_attributes[0].replaceAll(bad_characters, "")));
					}

					// Get the data depending on the attribute
					String attribute = sperate_attributes[0].replaceAll(bad_characters, "");
					String value = sperate_attributes[1].replaceAll("\\{|\\}", "");

					if (attribute.equalsIgnoreCase("author")) {
						builder.author(value);
					} else if (attribute.equalsIgnoreCase("journal")) {
						builder.journal(value);
					} else if (attribute.equalsIgnoreCase("title")) {
						builder.title(value);
					} else if (attribute.equalsIgnoreCase("year")) {
						builder.year(Integer.parseInt(value));
					} else if (attribute.equalsIgnoreCase("volume")) {
						builder.volume(value);
					} else if (attribute.equalsIgnoreCase("number")) {
						builder.number(Integer.parseInt(value));
					} else if (attribute.equalsIgnoreCase("pages")) {
						builder.pages(value);
					} else if (attribute.equalsIgnoreCase("keywords")) {
						builder.keywords(value.split(";"));
					} else if (attribute.equalsIgnoreCase("doi")) {
						builder.doi(value);
					} else if (attribute.equalsIgnoreCase("ISSN")) {
						builder.ISSN(value);
					} else if (attribute.equalsIgnoreCase("month")) {
						builder.month(value);
					} else {
						throw new FileInvalidException(
								String.format("ERROR! %s is not a valid attribute @ line %d (%s=%s)", attribute,
										line_number, attribute, value));
					}
				}
			}

			parsed_result[article_number] = builder.build();
			article_number++;
		}
		return parsed_result;// (ArticleData[]) allArticles.toArray();
	}

	/**
	 * This function removes all empty space --> "" from a String array
	 *
	 * @param array The array to go through
	 * @return Returns a String[] without white spaces
	 */
	private static String[] removeWhiteSpace(String[] array) {

		List<String> result = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (!array[i].equalsIgnoreCase("") && !array[i].equalsIgnoreCase("\n"))
				result.add(array[i]);
		}

		return listToArray(result);
	}

	/**
	 * Converts a java.util.List to a regular String[]
	 *
	 * @param list The list to convert
	 * @return Returns the array
	 */
	public static String[] listToArray(List<String> list) {

		String[] array = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}

		return array;
	}
}
