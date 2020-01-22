﻿using System;
using System.Collections.Generic;
using System.Net;

namespace Learning_c_sharp {

	class JSONFile_test {
		// Just a data container for json element
		public struct JsonElement {
			public string name;
			public string value;
			public JsonElement(string _name, string _val) {
				name = _name;
				value = _val;
			}
		}

		private List<JsonElement> data = new List<JsonElement>();

		public void addElement(string name, string value) {
			JsonElement result = new JsonElement {
				name = name,
				value = value
			};

			data.Add(result);
		}

		public JsonElement getElementByName(string name) {
			foreach (var temp in data) {
				if (temp.name.Contains(name)) {
					return temp;
				}
			}
			return new JsonElement(null, null);
		}

		public static JSONFile_test parseJSON(string rawJson) {

			JSONFile_test result = new JSONFile_test();
			string[] splited = rawJson.Split(',');

			foreach (var line in splited) {
				var elements = line.Split(':');
				result.addElement(elements[0], elements[1]);
			}

			return result;
		}
	}
	class Program {
		public static void Main(string[] args) {

			/***
			 * Weather app with C#
			 */

			Console.Title = "Z OMEGALUL E";

			while (true) {
				string apiKey = "15ef7515ef7a18f9396055cfcf49301c";

				Console.Write("Enter the desired city to get weather > ");
				string city = Console.ReadLine();

				string url = string.Format("https://api.openweathermap.org/data/2.5/weather?q={0}&units=metric&appid={1}", city.Trim(), apiKey);

				using (WebClient client = new WebClient()) {

					string data;
					JSONFile_test json;

					try {
						// Get data from server if it exists
						data = client.DownloadString(url);
						json = JSONFile_test.parseJSON(data);
					} catch (Exception e) {
						// If no such data is found (e.g. city doesn't exist) display error then exit the program
						// Display the proper error message
						Console.WriteLine("Error! {0}, {1}", e.GetType(), e.Message);
						Console.ReadLine();
						break;
					}

					// Display data on console
					Console.WriteLine("\nDisplay informations about {0}:", city);
					Console.WriteLine("\tCountry: {0}", json.getElementByName("country").value);
					Console.WriteLine("\tCity: {0}", json.getElementByName("name").value);
					Console.WriteLine("\tTemprature: {0}°C", json.getElementByName("temp").value);
				}

				// ---- PAUSE ----
				Console.Write("Would you like to get another city weather (Y/N) > ");
				string input = Console.ReadLine();
				if (input.Equals("N") || input.Equals("n")) {
					break;
				}
			}

		}

	}
}
