using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Cs_Compile_test.com {
	static class Util {

		public static string[] RemoveBlanks(this string[] array) {
			return array.Where(e => !string.IsNullOrWhiteSpace(e)).ToArray();
		}

	}
}
