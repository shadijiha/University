using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Cs_Compile_test.com {
	public class PreprocessorCommand
	{
		public readonly static List<PreprocessorCommand> commands = new List<PreprocessorCommand>();
		public readonly static IDictionary<string, Func<object>> constants = new Dictionary<string, Func<object>>();

		public string name { get; }
		public uint argCount { get; }
		private Run code;

		public delegate object Run(string[] args);

		public PreprocessorCommand(string name, uint argCount, Run code) {
			this.name = name;
			this.argCount = argCount;
			this.code = code;
		}

		static PreprocessorCommand() {
			var include = new PreprocessorCommand("include", 1,
				filename =>  File.ReadAllText(filename[0].Trim().Replace("\"", "").Replace("<", "").Replace(">", "")
				));

			commands.Add(include);

			// Constants
			constants.Add("__LINE__", () => Compiler.GetCurrent().lineNumber);
			constants.Add("__FILE__", () => Compiler.GetCurrent().filename);
			constants.Add("__PATH__", () => Directory.GetParent(Compiler.GetCurrent().filename));
		}

		public T Execute<T>(string[] args) {
			return (T)code(args);
		}

		public static PreprocessorCommand Get(string name) {
			return (from command in commands
				   where command.name == name
				select command).First();
		}
	}
}
