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
		private string filecontent;
		private string filename;

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
			VM.instance.Initialize();

			string[] lines = filecontent.Split("\n");
			foreach (var l in lines) {
				string line = l.Trim();

				new Expression(line).Execute();
			}
		}

		public static void compile(string code) {
			Compiler compiler = new Compiler(null);
			compiler.filecontent = code;
			compiler.compile();
		}
	}
}