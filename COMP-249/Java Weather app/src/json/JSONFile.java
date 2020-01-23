/***
 * 
 * JSON class to parse and store json files
 */

package json;

import java.util.ArrayList;
import java.util.List;

public class JSONFile {

	private List<JSONElement> data;

	public JSONFile() {
		data = new ArrayList<JSONElement>();
	}

	/***
	 * Adds an element to the data of JSON file
	 * 
	 * @param element
	 * @return Returns true if the element has been added successfully
	 * @throws Exception thrown if the operation wasn't successful
	 * @see JSONElement
	 */
	public boolean addElement(JSONElement element) throws Exception {
		try {
			data.add(element);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Search for the JSON element whose attribute matches the passed one
	 * 
	 * @param att The attribute you want to match
	 * @return Returns the found JSONElement
	 */
	public JSONElement firstMatch(String att) {

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getAttribute().equals(att))
				return data.get(i);
		}
		return null;
	}

	/***
	 * Removes the first JSON element whose attribute matches the passed one
	 * 
	 * @param att The attribute you want to match
	 * @return Returns the removed JSONElement
	 */
	public JSONElement removeFirstMatch(String att) {

		int index = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getAttribute().equals(att))
				index = i;
		}

		JSONElement removedElement = data.remove(index);

		return removedElement;
	}

	/***
	 * Removes all JSON elements whose attribute matches the passed one
	 * 
	 * @param att The attribute you want to match
	 * @return Returns all the removed JSONElements
	 */
	public List<JSONElement> removeAllMatch(String att) {

		List<JSONElement> temp = new ArrayList<JSONElement>();

		var iteration = firstMatch(att);
		while (iteration != null) {
			temp.add(iteration);
			removeFirstMatch(att);
			iteration = firstMatch(att);
		}
		return temp;
	}

	/***
	 * Converts a normal String to a JSON Element
	 * 
	 * @param jsObject The string to convert
	 * @return Returns a JSON Element with the string content
	 */
	public static JSONElement parseObject(String jsObject) {

		if (jsObject.charAt(0) != '{' || jsObject.charAt(jsObject.length() - 1) != '}') {
			return null;
		} else if (!jsObject.contains(":")) {
			return null;
		}

		// Remove all the garbage
		jsObject = jsObject.replace("{", "");
		jsObject = jsObject.replace("}", "");
		jsObject = jsObject.replace("\"", "");

		/*
		 * Split into attribut and value/array TODO: this is a trash way to do it and
		 * will not work if you have:
		 * 
		 * {"attribute" : { "something": "value", "something": "value" } }
		 */
		String[] elements = jsObject.split(":");

		// Detect arrays
		// If the object has an array it will return a JSON element that has an array
		// but not a value.\
		// Otherwise, will return a normal JSON element
		if (elements[1].contains("[")) {
			String arrayElements[] = elements[1].split(",");
			return new JSONElement(elements[0], arrayElements);

		} else {
			return new JSONElement(elements[0], elements[1]);
		}

	}

	/***
	 * @return Returns all the data of the JSON file
	 */
	public List<JSONElement> getAllElements() {
		return new ArrayList<JSONElement>(this.data);
	}
}
