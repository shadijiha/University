using System;
using System.Collections.Generic;

namespace Learning_c_sharp {

	public class JSONElement {
		private readonly string attribute;
		private readonly string value;

		public JSONElement(string attribute, string value) {
			this.attribute = attribute;
			this.value = value;
		}

		public string getAttribute() {
			return attribute;
		}

		public string getValue() {
			return value;
		}

	}
	public class JSONFile {
		private List<JSONElement> data;

		/***
		 * Default constructor initialized the field "data";
		 */
		public JSONFile() {
			data = new List<JSONElement>();
		}

		///<summary>This function adds an element to the JSON file data</summary>	
		///<param name="element">element The element you want to add</param>
		///<returns>Returns true if the operation is seccussful</returns>
		///<exception cref="System.Exception">thrown when the operation if not seccussful</exception>
		public bool addElement(JSONElement element) {
			try {
				data.Add(element);
				return true;
			} catch (Exception e) {
				throw;
			}
		}

		///<summary>This function removes the first matched element from the JSON file data</summary>	
		///<param name="attribute">the attribute of the element you want to remove</param>
		///<returns>Returns the removed element</returns>
		public JSONElement removeFirstMatch(string attribute) {

			JSONElement match = data.Find(e => e.getAttribute() == attribute);
			data.Remove(match);

			return match;
		}

		///<summary>This function removes all the matched element from the JSON file data</summary>	
		///<param name="attribute">the attribute of the elements you want to remove</param>
		///<returns>Returns an array of removed elements</returns>
		public JSONElement[] removeAllMatch(string attribute) {
			var match = data.FindAll(e => e.getAttribute() == attribute);
			data.RemoveAll(e => e.getAttribute() == attribute);

			return match.ToArray();
		}

		public static JSONElement parseObject(string jsObject) {

			if (jsObject[0] != '{' || jsObject[jsObject.Length - 1] != '}') {
				return null;
			} else if (!jsObject.Contains(":")) {
				return null;
			}

			// Replace " with empty
			jsObject = jsObject.Replace("\"", "");

			// Create result object if everything is ok
			string[] elements = jsObject.Split(':');
			JSONElement result = new JSONElement(elements[0], elements[1]);

			return result;
		}
	}
}
