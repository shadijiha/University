/**
 *
 */

using System;
using System.Collections.Generic;
using System.Text;

namespace Cs_Compile_test.com.nativeTypes {
	public class ShadoString : ShadoClass {
		public ShadoString()
			: base("string", new TypeValidator("string", o => true))
		{}

		protected override void initializeMethods() {
			base.initializeMethods();

			var length_func = new ShadoMethod("length", 0, "int");
			length_func.SetCode((ctx, objects)=> {
				Console.WriteLine(ctx.value.ToString().Length);
				return ctx.value.ToString().Length;
			});
			methods.Add(length_func);
		}
	}
}
