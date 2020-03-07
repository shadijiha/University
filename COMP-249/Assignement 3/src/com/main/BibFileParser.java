/**
 * 
 */
package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exceptions.FileInvalidException;
import com.main.ArticleData.ArticleDataBuilder;

/**
 * @author shadi
 *
 */

public class BibFileParser {

	private File file;
	private String filename;
	private String data;
	private int number_of_articles;

	/**
	 * @throws FileNotFoundException
	 * 
	 */
	public BibFileParser(String path) throws FileNotFoundException {
		// TODO Auto-generated constructor stub

		filename = path;
		file = new File(path);

		// If the file doesn't exist
		if (!file.exists())
			throw new FileNotFoundException("The file " + file.getAbsolutePath() + " does not exist!");
		else
			readBibFile();
	}

	/**
	 * Reads a Bib file and populates the "data" List attribute
	 */
	private void readBibFile() {

		try {
			Scanner scan = new Scanner(file);

			StringBuilder builder = new StringBuilder();

			// Read the file
			while (scan.hasNextLine())
				builder.append(scan.nextLine() + '\n');

			data = builder.toString();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR! Cannot open file: " + file.getAbsolutePath());
		}

	}

	public ArticleData[] parse() throws FileInvalidException {

		// Find how many articles are in the file
		String[] all_Articles = data.split("@ARTICLE");
		all_Articles = removeWhiteSpace(all_Articles);

		number_of_articles = all_Articles.length;

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
								"File is invalid: Field \"%s\" is Empty. Processing stopped at this point. Other empty fields may be present as well!",
								sperate_attributes[1].replaceAll(bad_characters, "")));
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
