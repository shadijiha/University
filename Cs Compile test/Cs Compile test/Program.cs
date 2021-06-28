using System;
using Cs_Compile_test.com;

namespace Cs_Compile_test {
	public class Program {
		public static void Main(string[] args) {

			try {

				Compiler compiler = new Compiler(@"D:\Wamp64\www\GitHub\University\Cs Compile test\Cs Compile test\test.shado");
				compiler.compile();
				VM.instance.InvokeMain();

			}
			catch (Exception e) {
				Console.WriteLine();
				Console.WriteLine(e.Message);
			}
		}
	}
}
