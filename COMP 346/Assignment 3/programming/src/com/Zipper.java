/**
 * Testing compressing files with a lot of similar lines
 */
package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

	public void zip() {

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

	}

	public void unzip() {

		if (!extension.equalsIgnoreCase(".szip"))
			throw new IllegalArgumentException("Cannot unzip a non Shado Zip file (*.szip)");

		// Read the zipped fil

	}

}
