/**
 * Testing compressing files with a lot of similar lines
 */
package com;

import java.io.*;
import java.util.*;

public class Zipper {

	private String name;
	private File file;
	private String extension;

	public Zipper(String path) {
		var nameTokens = path.split("\\.");
		name = nameTokens[0];
		extension = nameTokens[1];

		file = new File(path);
	}

	/**
	 * Compress the file
	 *
	 * @return Returns a zipper with the ziped file data
	 */
	public Zipper zip() {

		if (extension.equalsIgnoreCase("szip"))
			throw new IllegalStateException("Cannot zip an already zipped file");

		// Read the file
		Scanner scanner = null;
		Map<String, Integer> map = new HashMap<>();
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!map.containsKey(line)) {
					map.put(line, 1);
				} else {
					int old = map.get(line);
					map.replace(line, old, old + 1);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		// Write the data to the compressed file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(name + ".szip"));

			for (var entry : map.entrySet())
				writer.printf("(%d)\t%s\n", entry.getValue(), entry.getKey());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}


		return new Zipper(name + ".szip");
	}

	/**
	 * @return Returns a zipper with the unzipped file data
	 */
	public Zipper unzip() {

		if (!extension.equalsIgnoreCase("szip"))
			throw new IllegalArgumentException("Cannot unzip a non Shado Zip file (*.szip)");

		// Read the zipped file
		List<struct> map = new ArrayList<>();

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split("\t");

				// Identafiy the number
				String numberStr = tokens[0].replaceAll("[()]", "");
				int number = Integer.parseInt(numberStr);

				tokens[0] = "";
				String data = String.join("", tokens);

				map.add(new struct(data, number));
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


		// Write data to unzipped file
		try {

			PrintWriter writer = new PrintWriter(new FileOutputStream(name + ".txt"));

			for (struct temp : map) {
				for (int i = 0; i < temp.count; i++)
					writer.println(temp.data);
			}

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Zipper(name + ".txt");
	}

	private final class struct {
		public String data;
		public int count;

		public struct(String data, int count) {
			this.data = data;
			this.count = count;
		}
	}
}
