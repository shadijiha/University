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
		public readonly static string type = "*TYPE*";
		public readonly static string identifier = "*IDENTIFIER*";
		public readonly static string op = "*OPERATOR*";
		public readonly static string symbol = "*SYMBOL*";
		public readonly static string any = "*ANY*";

		public static readonly ExpressionSyntax ASSIGNEMENT = new ExpressionSyntax($"{identifier} {op} {any};");
		public static readonly ExpressionSyntax DECLARATION = new ExpressionSyntax($"{type} {identifier};");
		public static readonly ExpressionSyntax FULL_DECLARATION = new ExpressionSyntax($"{type} {identifier} = {any};");

		private readonly string pattern;

		public ExpressionSyntax(string pattern) {
			this.pattern = pattern;
		}

		public bool Matches(string str) {
			string regex = pattern.Replace(type, compileTypes());
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
		private string raw;
		private ShadoClass type;
		private string name;
		private string rhs;

		public Expression(string raw) {
			this.raw = raw;
			var parts = Regex.Matches(raw, @"[\""].+?[\""]|[^ ]+")
				.Cast<Match>()
				.Select(m => m.Value)
				.ToList();

			parseExpression(parts);
		}

		private void parseExpression(List<string> tokens) {
			
			// If it is a declaration
			if (ExpressionSyntax.FULL_DECLARATION.Matches(raw)) {
				this.type = VM.instance.GetClass(tokens[0]);
				this.name = tokens[1];
				this.rhs = string.Join(' ', tokens.GetRange(3, tokens.Count - 3));

			} 
			// If it is a reassignment
			else if (ExpressionSyntax.ASSIGNEMENT.Matches(raw)) {
				// TODO: The varaibles my be in scope in the future
				this.name = tokens[0];
				this.rhs = string.Join(' ', tokens.GetRange(2, tokens.Count - 2));
				this.type = VM.instance.Get(name).type;
			}
			// If it is a declaration
			else if (ExpressionSyntax.DECLARATION.Matches(raw)) {
				this.type = VM.instance.GetClass(tokens[0]);
				this.name = tokens[1].Replace(";", "").Replace("\n", "");
			}
			else {
				return;
			}

			// Remove the new line or the ; at the end
			this.rhs = rhs.Trim();
			if (rhs[rhs.Length - 1] == ';')
				rhs = rhs.Substring(0, rhs.Length - 1);
		}

		public void Execute() {

			if (name == null)
				return;

			object eval = null; 
			if (isLvalue(rhs)) {
				eval = evaluateLvalue(rhs);
			} else {
				eval = evaluateRvalue(rhs);
			}

			try {
				VM.instance.PushVariable(new ShadoObject(type, name, eval));
			}
			catch (CompilationError) {
				VM.instance.Get(name).value = eval;
			}
		}

		private object evaluateLvalue(string lvalue) {
			return VM.instance.Get(lvalue.Trim())?.value ?? lvalue;
		}

		private object evaluateRvalue(string rvalue) {
			// TODO: this is to rework
			// See if the variables already exists in the VM
			object eval = rvalue;
			try {
				DataTable dt = new DataTable();
				eval = dt.Compute(rvalue, "");
				return eval;
			} catch (Exception) { }

			// If object start with & (means a pointer)
			if (rvalue.StartsWith("&"))
				rvalue = rvalue.Trim().Substring(1);
				return VM.instance.GetOrThrow(rvalue, -1).GetHashCode();

			return rvalue;
		}

		private bool isRvalue(string rvalue) {
			return !isLvalue(rvalue);
		}

		private bool isLvalue(string lvalue) {
			return !Regex.IsMatch(lvalue, ExpressionSyntax.compileOperators());
		}

		public override string ToString() {
			return $"type: {type}, name: {name}, rhs: {rhs}";
		}
	}
}
