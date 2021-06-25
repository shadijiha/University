/**
 *
 */

using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Cs_Compile_test.com;

namespace Cs_Compile_test
{
	public class Compiler
	{
		private static Compiler current = null;

		private string filecontent;
		private string filename;
		private uint lineNumber;

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

			lineNumber = 1;
			string[] lines = filecontent.Split("\n");
			foreach (var l in lines) {
				string line = l.Trim().Split("//")[0]; // Without comments

				new Expression(line).Execute();
				lineNumber++;
			}
		}

		public static string GetCurrentInfo() {
			string line = current.filecontent.Split("\n")[current.lineNumber - 1];
			return $"{current.filename} @ line: {current.lineNumber}\n" +
			       $"--->\t{line}";
		}

		public static void compile(string code) {
			Compiler compiler = new Compiler(null);
			compiler.filecontent = code;
			compiler.compile();
		}
	}
}