using Cs_Compile_test.com;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Cs_Compiler_unit_tests {
	[TestClass]
	public class MathModuleTest : BaseTest {

		[TestMethod]
		public void Min() {
			string code = TestUtil.MainWithCore("int abc = min(6, 2);", "int b = min(1, 6)");
			TestUtil.CompileAndRun(code);

			ShadoObject a = VM.instance.Get("main").GetVariable("abc");
			ShadoObject b = VM.instance.Get("main").GetVariable("b");

			Assert.AreEqual("2", a.ToString());
			Assert.AreEqual("1", b.ToString());
		}

		[TestMethod]
		public void Max() {
			string code = TestUtil.MainWithCore("int a = max(6, 2);", "int b = max(1, 6)");
			TestUtil.CompileAndRun(code);

			ShadoObject a = VM.instance.Get("main").GetVariable("a");
			ShadoObject b = VM.instance.Get("main").GetVariable("b");

			Assert.AreEqual("6", a.ToString());
			Assert.AreEqual("6", b.ToString());
		}

	}
}
