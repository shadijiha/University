/***
 * Main file
 * 
 */
package app;

import json.JSONElement;
import json.JSONFile;

public final class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "{\"temp\": \"25\"}";
		String test2 = "{\"country\": \"CA\"}";
		JSONFile file = new JSONFile();

		var element = new JSONElement("tempratures", "15");

		for (int i = 0; i < 10; i++)
			element.addValue(Integer.toString(((int) (Math.random() * 100))));

		file.addElement(JSONFile.parseObject(test));
		file.addElement(JSONFile.parseObject(test2));
		file.addElement(element);

		var it = file.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		return;
	}
}
