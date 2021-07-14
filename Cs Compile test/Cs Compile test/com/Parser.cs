﻿using Cs_Compile_test.com.exceptions;
using Cs_Compile_test.com.expr;
using Cs_Compile_test.com.interfaces;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace Cs_Compile_test.com {
	public static class Parser {

		/// <summary>
		/// Parses the code and adds all the extacted methods to the class
		/// </summary>
		/// <param name="code">The code to parse</param>
		/// <param name="clazz">The class to add the method to, if it is null, the methods are added to the global scope</param>
		public static void ExtractMethods(string code, ShadoClass clazz = null) {

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
				List<AbstractExpression> methodCodeLines = new List<AbstractExpression>();

				// Add all args to method scope
				for (int i = 0; i < args.Length; i++) {
					var temp = Regex.Split(args[i], "\\s+").Where(e => !string.IsNullOrEmpty(e)).ToArray();
					method.AddVariable(temp[0], temp[1]);
				}

				// Parse method lines
				for (int i = 1; i < lines.Length - 1; i++) {
					// Check if the statement is an if statement
					if (ConditionalExpression.IsIfStatement(lines[i])) {
						Range range = new Range(i, lines.Length);
						var ifBlock = ExtractBlocks(string.Join('\n', lines[range]));
						methodCodeLines.Add(new ConditionalExpression(ifBlock.First(), lines[i], method));

						// Do not add the if statement content for parsing
						i += ifBlock.First().Split("\n").Length;
					}
					// Else check if it is a for loop
					else if (LoopExpression.IsLoopStatement(lines[i])) {
						Range range = new Range(i, lines.Length);
						var loopBlock = ExtractBlocks(string.Join('\n', lines[i..lines.Length]));
						methodCodeLines.Add(new LoopExpression(loopBlock.First(), lines[i], method));

						// Do not add the if statement content for parsing
						i += loopBlock.First().Split("\n").Length;
					}

					// Otherwise just push an Expression
					methodCodeLines.Add(new Expression(lines[i], method));
				}

				method.SetCode((ctx, args) => {

					var status = new ExecutionStatus();

					int i = 0;
					while (i < methodCodeLines.Count && status.status == ExecutionStatus.Type.OK) {
						try {
							methodCodeLines[i++].Execute(ref status);
						} catch (Exception e) {
							throw new Exception(e.Message + $"\n\t @ line: {i + 1}\n" + $"--->\t{lines[i]}");
						}
					}

					return status.value;
				});

				if (clazz == null)
					VM.instance.PushVariable(method);
				else
					clazz.AddMethod(method);
			}
		}

		public static void ExtractClasses(string code) {
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
						try { className = tokens[i + 1]; } catch (Exception) { throw new SyntaxError("Class does not have a name"); }
				}

				// Register this class
				ShadoClass clazz = new ShadoClass(className);
				clazz.AddParentClass(VM.GetSuperType());
				clazz.AddMethod(new ShadoMethod(className, 0, className).SetCode((ctx, args) => {

					// Call constructor of all parent
					foreach (var parent in clazz.GetParentClasses())
						parent.GetConstructor().Call(ctx, args);

					return new ShadoObject(clazz, null);
				}));
				VM.instance.AddType(clazz);

				// Extract methods
				Range r = new Range(1, lines.Length - 1);
				ExtractMethods(string.Join('\n', lines[r]), clazz);
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
					try { stack.Pop(); } catch (Exception) { }

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
			return new ExpressionSyntax("ANYclassANY{").Matches(line) ||
				new ExpressionSyntax("ANYstructANY{").Matches(line);
		}
	}
}
