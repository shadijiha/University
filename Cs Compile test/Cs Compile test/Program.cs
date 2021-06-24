using System;
using Cs_Compile_test.com;

namespace Cs_Compile_test {
	public class Program {
		public static void Main(string[] args) {

			try {

				//Compiler compiler = new Compiler(@"C:\Users\shadi\Desktop\Cs Compile test\Cs Compile test\main.shado");
				//compiler.compile();
				
				Compiler.compile(@"
					int main()	{
						double x = 10.564;
						int* a = &x;
						int* b = a;
						int* c = &b;
					}
				");


				foreach (var allVariable in VM.instance.AllVariables()) {
					Console.WriteLine("{0}: {1}", allVariable.name, allVariable.value);
				}

			}
			catch (Exception e) {
				Console.WriteLine();
				Console.WriteLine(e.Message);
			}
		}
	}
}
