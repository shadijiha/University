//  -----------------------------------------------------
// Assignment #4
// Question: 1
// Written by: Shadi Jiha #40131284
//  -----------------------------------------------------

package part1;

import java.io.*;
import java.util.*;

public class TextFile {

	private String filename;
	private String data;
	private ArrayList<String> words;
	private ArrayList<String> dictionnary;
	private String formated = "";

	public TextFile(String filename) {
		this.filename = filename;
		this.data = "";
		this.words = new ArrayList<String>();
		this.dictionnary = new ArrayList<String>();

		readFile();
	}

	private void readFile() {

		FileInputStream input = null;
		try {
			input = new FileInputStream(this.filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner scan = new Scanner(input);

		while (scan.hasNext()) {
			String temp = scan.next();
			words.add(temp);
			data += temp + " ";
		}

		scan.close();
	}

	public void removeBadChars() {

		for (int i = 0; i < words.size(); i++) {
			words.set(i, words.get(i).replaceAll("[?:',=;!.’0123456789]", ""));
			// TODO: Repalce single chars that are not I or A
		}
	}

	public void parse() {

		OuterLoop:
		for (String word : words) {
			for (String dic_word : dictionnary) {
				if (word.equalsIgnoreCase(dic_word)) {
					continue OuterLoop;
				}
			}
			if (!(word.equalsIgnoreCase("") || word.equalsIgnoreCase("\n"))) {
				dictionnary.add(word.toUpperCase());
			}

		}

	}

	public void exportToFile(String output_name) {


		dictionnary.sort(null);

		ArrayList<String> blocks = new ArrayList<String>();
		String previous = "Aefaefaefafef";
		String block = "A\n==\n";

		for (String word : dictionnary) {
			if (word.length() <= 0) {
				continue;
			}

			if (word.charAt(0) != previous.charAt(0)) {
				blocks.add(block);
				block = word.charAt(0) + "\n==\n";
			}
			block += word + "\n";
			previous = word;
		}


		try {
			FileOutputStream outputStream = new FileOutputStream(output_name);
			PrintWriter writer = new PrintWriter(output_name);

			writer.println("The document produced this sub-dictionary, which includes " + dictionnary.size() + " entries.\n\n");

			for (String b : blocks) {
				writer.println(b);
				writer.flush();
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void print() {
		words.stream().forEach(System.out::println);
	}

}
