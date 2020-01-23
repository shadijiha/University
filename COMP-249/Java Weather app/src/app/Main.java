/***
 * Main file
 * 
 */
package app;

import json.JSONFile;

public final class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "{\"temp\": \"25\"}";
		String test2 = "{\"country\": \"CA\"}";
		JSONFile file = new JSONFile();

		try {
			file.addElement(JSONFile.parseObject(test));
			file.addElement(JSONFile.parseObject(test2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		file.getAllElements().stream().forEach(e -> System.out.println(e.getAttribute() + ", " + e.getValue()));

	}
}
