using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using Cs_Compile_test.com.exceptions;

namespace Cs_Compile_test.com {

	public class ExpressionSyntax
	{
		public readonly static string type = "TYPE";
		public readonly static string identifier = "IDENTIFIER";
		public readonly static string op = "OPERATOR";
		public readonly static string symbol = "SYMBOL";
		public readonly static string any = "ANY";

		public static readonly ExpressionSyntax ASSIGNEMENT = new ExpressionSyntax($"{identifier} {op} {any};");
		public static readonly ExpressionSyntax DECLARATION = new ExpressionSyntax($"{type} {identifier};");
		public static readonly ExpressionSyntax FULL_DECLARATION = new ExpressionSyntax($"{type} {identifier} = {any};");

		private readonly string pattern;

		public ExpressionSyntax(string pattern) {
			this.pattern = pattern;
		}

		public bool Matches(string str) {
			string regex = Regex.Escape(pattern).Replace(type, compileTypes());
			regex = regex.Replace(identifier, compileIdentifiers());
			regex = regex.Replace(any, ".*");
			regex = regex.Replace(op, compileOperators());
			regex = regex.Replace(symbol, compileOperators());
			return Regex.IsMatch(str, regex);
		}

		public static string compileTypes() {
			/*StringBuilder builder = new StringBuilder();
			builder.Append("(");

			var it = VM.instance.GetAllTypes();
			uint count = 0;
			foreach (ShadoClass shadoClass in it) {
				builder.Append(Regex.Escape(shadoClass.name));
				if (count < it.Count - 1)
					builder.Append("|");

				count++;
			}

			builder.Append(")");*/

			return "\\w+(\\*|\\[])?"; //builder.ToString();
		}

		public static string compileIdentifiers() {
			return "\\w+";
		}

		public static string compileOperators() {
			string[] ops = {"=", "==", "!=", "!", "<", ">", "<=", ">=", "+", "*", "-", "/", "(", ")", "[", "]", "{", "}", "|", "%", "@", "$", "#", "^", "&", ".", "->", ",", ";" };
			StringBuilder builder = new StringBuilder();

			uint count = 0;
			builder.Append("(");
			foreach (string s in ops) {
				builder.Append(Regex.Escape(s));

				if (count < ops.Length - 1)
					builder.Append("|");

				count++;
			}

			builder.Append(")");

			return builder.ToString();
		}
	}

	public class Expression
	{
		public enum Type
		{
			DECLARATION, ASSIGNMENT, FUNC_CALL, OBJECT_FUNC_CALL
		}

		private string raw;
		private ShadoClass type;
		private string name;
		private string rhs;
		private Type expressionType;

		public Expression(string raw) {
			this.raw = raw.Trim();
			var parts = SplitByExceptQuotes(raw, " ");

			parseExpression(parts);
		}

		public object Execute() {
			switch (expressionType) {
				case Type.ASSIGNMENT:
				case Type.DECLARATION:
					return executeVariableAssignemt();
				case Type.FUNC_CALL:
					return executeFuncCall();
				case Type.OBJECT_FUNC_CALL:
					return executeFuncCallOnObject();
				default: return rhs;
			}
		}

		private void parseExpression(List<string> tokens) {
			
			// If it is a full declaration
			if (ExpressionSyntax.FULL_DECLARATION.Matches(raw)) {
				this.type = VM.instance.GetClass(tokens[0]);
				this.name = tokens[1];
				this.rhs = string.Join(' ', tokens.GetRange(3, tokens.Count - 3));
				this.expressionType = Type.ASSIGNMENT;
			} 
			// If it is a declaration
			else if (ExpressionSyntax.DECLARATION.Matches(raw)) {
				this.type = VM.instance.GetClass(tokens[0]);
				this.name = tokens[1].Replace(";", "").Replace("\n", "");
				this.expressionType = Type.DECLARATION;
			}
			// If it is a function call on object
			else if (new ExpressionSyntax("IDENTIFIER.IDENTIFIER(ANY)").Matches(raw) && !raw.Contains("{")) {
				this.type = null;
				this.name = null;
				this.rhs = raw;
				this.expressionType = Type.OBJECT_FUNC_CALL;
			}
			// If it is a function call
			else if (new ExpressionSyntax("IDENTIFIER(ANY)").Matches(raw) && !raw.Contains("{")) {
				this.type = null;
				this.name = null;
				this.rhs = raw;
				this.expressionType = Type.FUNC_CALL;
			}
			// If it is a reassignment
			else if (ExpressionSyntax.ASSIGNEMENT.Matches(raw)) {
				// TODO: The varaibles my be in scope in the future
				this.name = tokens[0];
				this.rhs = string.Join(' ', tokens.GetRange(2, tokens.Count - 2));
				this.type = VM.instance.Get(name).type;
				this.expressionType = Type.ASSIGNMENT;
			} else {
				this.rhs = raw;
			} 

			// Remove the new line or the ; at the end
			this.rhs = rhs.Trim();
			if (!string.IsNullOrEmpty(rhs) && rhs[^1] == ';')
				rhs = rhs.Substring(0, rhs.Length - 1);
		}

		private object executeVariableAssignemt() {
			// TODO: this shit should not be here
			if (name == null)
				return null;

			rhs = rhs.Trim();

			// Check for pointer
			object value = null;
			ShadoObject pointer = null;	// If the current expression is an assignment to clone a method (change its name by variable assignment)

			if (rhs.StartsWith("&")) {
				var ptr = VM.instance.Get(rhs.Substring(1));
				checkVariable(ptr);

				value = ptr.GetHashCode();
			}
			 // Check for derefrence
			 else if (rhs.StartsWith("*")) {

				var ptr = rhs.Substring(1);
				ShadoObject obj;

				// If it is a int
				if (int.TryParse(ptr, out _))
					obj = VM.instance.GetByAddress(int.Parse(ptr));
				else
					obj = VM.instance.GetByAddress(int.Parse(VM.instance.Get(ptr).value.ToString()));

				checkVariable(obj);

				value = obj.value;
				pointer = obj;
			}
			// Math expression
			else if (isMathExpression(rhs, ref value)) { }
			// It is a string assignment
			else if (rhs.StartsWith("\"") && rhs.EndsWith("\"")) {
				value = rhs;
			}
			// It is an array assignment
			else if (rhs.StartsWith("{") && rhs.EndsWith("}")) {
				value = new List<object>();
				string[] args = SplitByExceptQuotes(rhs.Substring(1, rhs.Length - 2), " |,").ToArray();

				// Insert all args to array
				var t = type.GetUnitType();
				foreach (string s in args) {
					if (t.IsValid(s.Trim()))
						(value as List<object>).Add(s);
					else
						throw new RuntimeError("Cannot add {0} to an array of type {1}", s, type.name);
				}
			}
			// Variable to variable assignment
			else if (VM.instance.Get(rhs) != null) {
				var obj = VM.instance.Get(rhs);
				value = obj.value;
				pointer = obj;
			}
			else if (isFunctionCall(rhs)) {
				value = new Expression(rhs).Execute();
			}

			// Push new variable or modify the old one
			try {
				if (pointer?.IsMethod() ?? false) {
					var method = new ShadoMethod(pointer as ShadoMethod);
					method.name = name;
					VM.instance.PushVariable(method);
				} else
					VM.instance.PushVariable(new ShadoObject(type, name, value));
			}
			catch (CompilationError) {
				if (pointer.IsMethod())
					throw new RuntimeError("function pointers are final (cannot be reassigned)");
				VM.instance.Get(name).value = value;
			}

			return null;
		}

		private object executeFuncCall() {

			// Parse function name
			string functionName = rhs.Split("(")[0].Trim();
			string[] args = SplitByExceptQuotes(rhs.Substring(functionName.Length + 1, rhs.Length - functionName.Length - 2), "+| |,").ToArray();

			// Get the function
			ShadoObject method = VM.instance.Get(functionName);
			if (method == null || !method.IsMethod())
				throw new RuntimeError("%s is not a function", functionName);

			return (method as ShadoMethod).Call(ShadoObject.Global, args);
		}

		private object executeFuncCallOnObject() {
			// Parse function name
			string[] tokens = rhs.Split(".", 2);
			string objectName = tokens[0].Trim();
			string functionName = tokens[1].Split("(")[0].Trim();

			char[] argsNoParantheses = tokens[1].Split("(", 2)[1].ToCharArray();
			argsNoParantheses[^1] = ' ';

			string[] args = SplitByExceptQuotes(new string(argsNoParantheses).Trim(), "+| |,").ToArray();

			// Get the function
			ShadoObject ctx = VM.instance.GetOrThrow(objectName);
			return ctx.type.GetMethod(functionName).Call(ctx, args);
		}

		private static bool isMathExpression(string expression, ref object output) {
			try {
				DataTable dt = new DataTable();
				output = dt.Compute(expression, "");
				return true;
			}
			catch (Exception) {}
			return false;
		}

		private static bool isFunctionCall(string expression) {
			return new ExpressionSyntax("IDENTIFIER(ANY)").Matches(expression) || new ExpressionSyntax("IDENTIFIER.IDENTIFIER(ANY)").Matches(expression);
		}

		private static void checkVariable(ShadoObject obj) {
			if (obj == null)
				throw new CompilationError("null pointer exception");
		}

		private static List<string> SplitByExceptQuotes(string raw, string splitter) {
			return Regex.Matches(raw, @"[\""].+?[\""]|[^" + splitter + @"]+")
				.Cast<Match>()
				.Select(m => m.Value)
				.ToList();
		}

		public override string ToString() {
			return $"type: {type}, name: {name}, rhs: {rhs}";
		}
	}
}
