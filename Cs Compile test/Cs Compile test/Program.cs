using Cs_Compile_test.com;
using System;

namespace Cs_Compile_test {
	public class Program {
		public static void Main(string[] args) {
			try {

#if true
				string path = @"C:\Users\shadi\Desktop\code\Projects\University\Cs Compile test\Cs Compile test\test.sscript";
#else
				string path = @"D:\Wamp64\www\GitHub\University\Cs Compile test\Cs Compile test\test.sscript";
#endif

				Compiler compiler =
					new Compiler(path);
				compiler.compile();
				VM.instance.InvokeMain(args.Length, args);
			} catch (Exception e) {
				Console.WriteLine();
				Console.WriteLine(e.Message);
			} finally {
				VM.instance.Shutdown();
			}
		}
	}
}
