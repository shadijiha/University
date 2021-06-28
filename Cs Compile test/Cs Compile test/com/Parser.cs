using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using Cs_Compile_test.com.exceptions;

namespace Cs_Compile_test.com {
	public static class Parser {

		public static void ExtractMethods(string code) {

			Compiler.ResetLineCount();
			var blocks = ExtractBlocks(code);

			foreach (string block in blocks) {
				string[] lines = block.Split("\n");

				// If this is not a method definition
				if (!IsMethodDefinition(lines[0]))
					continue;

				// Extract method data
				string[] tokens = Regex.Split(lines[0], "\\s+");
				string funcName = Clean(tokens[1].Split("(")[0]);
				string[] args = Clean(Regex.Match(lines[0], "\\(.*\\)").Value)
					.Split(",").Where(x => !string.IsNullOrEmpty(x))
					.ToArray();

				// Add it to the global scope
				ShadoMethod method = new ShadoMethod(funcName, args.Length, tokens[0]);
				List<Expression> methodCodeLines = new List<Expression>();

				// Add all args to method scope
				for (int i = 0; i < args.Length; i++) {
					var temp = Regex.Split(args[i], "\\s+").Where(e => !string.IsNullOrEmpty(e)).ToArray();
					method.AddVariable(temp[0], temp[1]);
				}

				// Parse method lines
				for (int i = 1; i < lines.Length - 1; i++) {
					methodCodeLines.Add(new Expression(lines[i], method));
				}

				method.SetCode((ctx, args) => {
					for (int i = 0; i < methodCodeLines.Count - 1; i++)
						methodCodeLines[i].Execute();

					return methodCodeLines[methodCodeLines.Count - 1].Execute();
				});

				VM.instance.PushVariable(method);
				Compiler.IncrementLineCount(lines.Length);
			}
		}

		public static void ExtractClasses(string code) {
			Compiler.ResetLineCount();
			var blocks = ExtractBlocks(code);

			foreach (string block in blocks) {
				string[] lines = block.Split("\n");

				// If this is a class definition
				if (!IsClassDefinition(lines[0]))
					continue;

				// Extract class data
				string line = lines[0];
				string[] tokens = Regex.Split(line, "\\s+").Where(e => !string.IsNullOrEmpty(e)).ToArray();

				string className = "";
				for (uint i = 0; i < tokens.Length; i++) {
					if (tokens[i] == "class")
						try { className = tokens[i + 1]; } catch (Exception) { throw new SyntaxError("Class does not have a name");}
				}

				// Register this class
				ShadoClass clazz = new ShadoClass(className);
				clazz.AddParentClass(VM.GetSuperType());
				clazz.AddMethod(new ShadoMethod(className, 0, className).SetCode((ctx, args) => {

					// Call constructor of all parent
					foreach (var parent in clazz.GetParentClasses())
						parent.GetConstructor().Call(ctx, null);

					Console.WriteLine("Inside {0} constructor", className);
					return new ShadoObject(clazz, null);
				}));
				VM.instance.AddType(clazz);

				Compiler.IncrementLineCount(lines.Length);
			}
		}

		private static List<string> ExtractBlocks(string code) {
			List<string> blocks = new List<string>();

			Stack<string> stack = new Stack<string>();
			StringBuilder buffer = new StringBuilder();

			string[] lines = code.Split("\n");
			foreach (string line in lines) {
				if (line.Contains("{")) {
					stack.Push("{");
				}

				if (line.Contains("}")) {
					stack.Pop();
					if (stack.Count == 0) {
						blocks.Add(buffer.ToString() + "}");
						buffer.Clear();
					}
				}

				if (stack.Count != 0)
					buffer.Append(line).Append("\n");
			}

			return blocks;
		}

		private static string Clean(string input, string regex = @"\(|\)") {
			return Regex.Replace(input, regex, "");
		}

		private static bool IsMethodDefinition(string line) {
			return new ExpressionSyntax("TYPE IDENTIFIER(ANY)ANY{").Matches(line);
		}

		private static bool IsClassDefinition(string line) {
			return new ExpressionSyntax("ANYclassANY{").Matches(line);
		}
	}
}
