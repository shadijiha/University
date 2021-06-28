/**
 *
 */

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using Cs_Compile_test.com;

namespace Cs_Compile_test
{
	public class Compiler
	{
		private static Compiler current = null;

		private string filecontent;
		public string filename { get; private set; }
		public uint lineNumber { get; set; }

		public Compiler(string filename) {
			if (filename == null)
				return;

			this.filename = filename;
			StringBuilder builder = new StringBuilder();

			using StreamReader reader = new StreamReader(filename);
			while (!reader.EndOfStream) {
				builder.Append(reader.ReadLine()).Append("\n");
			}

			filecontent = builder.ToString();
		}

		public void compile() {
			current = this;
			VM.instance.Initialize();

			// Run the preprocessor
			preprocessor();

			// Remove multiline comments
			while (Regex.IsMatch(filecontent, @"\/\*[^*]*\*+([^/][^*]*\*+)*\/"))
				filecontent = Regex.Replace(filecontent, @"\/\*[^*]*\*+([^/][^*]*\*+)*\/", "");
			
			// Run the class definer
			Parser.ExtractClasses(filecontent);

			// Run the method definer
			Parser.ExtractMethods(filecontent);

		}

		public void preprocessor() {
			string[] lines = filecontent.Split("\n");
			for (uint i = 0; i < lines.Length; i++) {
				lineNumber = i + 1;
				string line = lines[i].Trim();

				// Replace prepressor constant
				foreach (var constant in PreprocessorCommand.constants) {
					line = line.Replace(constant.Key, constant.Value().ToString());
					lines[i] = line;
				}

				if (line.StartsWith("#"))	{
					// Extract command name
					string[] tokens = Expression.SplitByExceptQuotes(line.Substring(1), " |	").ToArray();
					PreprocessorCommand command = PreprocessorCommand.Get(tokens[0]);

					lines[i] = command.Execute<string>(tokens.Skip(1).ToArray());
					
					// Go back to preprocess the file that just got included
					lines = string.Join('\n', lines).Split('\n');
					i = 0;
				}

			}

			filecontent = string.Join('\n', lines);
		}

		public static string GetCurrentInfo() {
			try {
				string line = current.filecontent.Split("\n")[current.lineNumber];
				return $"{current.filename} @ line: {current.lineNumber + 1}\n" +
				       $"--->\t{line}";
			}
			catch (Exception) {
				return "(Unable to get line info)";
			}
		}

		public static Compiler GetCurrent() {
			return current;
		}

		public static void ResetLineCount() {
			current.lineNumber = 0;
		}

		public static void IncrementLineCount(int i = 1) {
			current.lineNumber += 1;
		}

		public static void compile(string code) {
			Compiler compiler = new Compiler(null);
			compiler.filecontent = code;
			compiler.compile();
		}
	}
}