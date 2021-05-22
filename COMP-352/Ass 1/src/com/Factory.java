/**
 * This class generates random Data to test the algorithms
 */
package com;

import java.io.*;

public class Factory {

	private static final String[] static_names = {"Linda", "Sam", "Roger", "Alfred", "Roberto", "Melissa", "Brian", "Thomas", "Leslie", "Maria"};

	private String[] names;
	private String[] dates;

	public Factory() {
	}

	private int random(int min, int max) {
		return (int) Math.floor(Math.random() * (max - min) + min);
	}

	public void generateToFile(int n, boolean writeToFile) {

		names = new String[n];
		dates = new String[n];

		PrintWriter writer = null;
		try {

			if (writeToFile) {
				writer = new PrintWriter(new FileOutputStream("n" + n + ".txt"));
			}

			for (int i = 0; i < n; i++) {

				String name = static_names[random(0, static_names.length)];
				String date = random(1, 25) + "-" + random(1, 12) + "-" + random(1900, 2021);

				names[i] = name;
				dates[i] = date;

				if (writeToFile)
					writer.printf("%s %s\n", name, date);
			}

			if (writeToFile)
				writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

			if (writeToFile)
				writer.close();
		}
	}

	public void generateToFile(int n) {
		generateToFile(n, true);
	}

	public String[] getLastGeneratedNames() {
		return names;
	}

	public String[] getLastGeneratedDates() {
		return dates;
	}
}
