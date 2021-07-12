using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using Cs_Compile_test.com.exceptions;
using Cs_Compile_test.com.nativeTypes;

namespace Cs_Compile_test.com {
	public static class Util {

		public static string[] RemoveBlanks(this string[] array) {
			return array.Where(e => !string.IsNullOrWhiteSpace(e)).ToArray();
		}

		public static bool EvaluateBoolExpression(string expression) {
			try {
				DataTable dt = new DataTable();
				var output = dt.Compute(expression, "");
				return bool.Parse(output.ToString());
			} catch (Exception) { }
			return false;
		}

		public static string ReplaceLastOccurrence(this string Source, string Find, string Replace) {
			int place = Source.LastIndexOf(Find);

			if (place == -1)
				return Source;

			string result = Source.Remove(place, Find.Length).Insert(place, Replace);
			return result;
		}

		public static string ReplaceFirstOccurrence(this string Source, string Find, string Replace) {
			var regex = new Regex(Regex.Escape(Find));
			return regex.Replace(Source, Replace, 1);
		}

		public static StreamReader GetReadStream(this ShadoFileReader reader, ShadoObject ctx) {
			StreamReader stream = ctx.GetVariable("stream").value as StreamReader;
			if (stream == null)
				throw new RuntimeError("Could not open read stream to " + ctx.GetVariable("filepath"));
			return stream;
		}

		public static StreamWriter GetWriteStream(this ShadoFileWriter reader, ShadoObject ctx) {
			StreamWriter stream = ctx.GetVariable("stream").value as StreamWriter;
			if (stream == null)
				throw new RuntimeError("Could not open write stream to " + ctx.GetVariable("filepath"));
			return stream;
		}

		public static void Close(this ICloseBeforeExit stream, ShadoObject context) {
			context.type.GetMethod("close").Call(context, null);
		}
	}

	public struct ExecutionStatus
	{
		public Type status { get; set; }
		public object value { get; set; }

		public ExecutionStatus(Type type = Type.OK, object val = null) {
			this.status = type;
			this.value = val;
		}

		public enum Type {
			OK, RETURN, ABORT, BREAK, CONTINUE
		}
	}
}
