/**
 *
 */

using System;
using System.Collections.Generic;
using System.Text;

namespace Cs_Compile_test.com.nativeTypes {
	public class ShadoChar : ShadoClass {
		public ShadoChar()
			: base("char", new TypeValidator("char", 
				o=>o.ToString().StartsWith("'") && o.ToString().EndsWith("'") && o.ToString().Replace("'", "").Length <= 1)
				) {}
	}
}
