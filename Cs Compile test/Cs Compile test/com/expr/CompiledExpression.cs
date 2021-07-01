using System;
using System.Collections.Generic;
using System.Data;
using System.Text;
using Cs_Compile_test.com.interfaces;

namespace Cs_Compile_test.com.expr {

	public class ConditionalExpression : AbstractExpression
	{
		private string block;
		private ShadoObject scope;
		private string condition;

		public ConditionalExpression(string block, ShadoObject scope) {
			this.block = block;
			this.scope = scope;
		}

		public object Execute() {

			if (Evaluate()) {
				var lines = block.Split("\n");
				foreach (var line in lines) {
					new Expression(line, scope).Execute();
				}
			}

			return null;
		}

		private bool Evaluate(Delegate) {
			string line = condition.Replace("if", "").Trim().Substring(1);
			line = line.Substring(0, line.Length - 1);

			foreach (var variable in scope.GetAllVariables()) {
				line = line.Replace(variable.name, variable.value.ToString());
			}

			return EvaluateBoolExpression(line);
		}

		private static bool EvaluateBoolExpression(string expression) {
			try {
				DataTable dt = new DataTable();
				var output = dt.Compute(expression, "");
				return bool.Parse(output.ToString());
			} catch (Exception) { }
			return false;
		}
	}
}
