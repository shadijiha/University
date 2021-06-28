using System;
using System.Collections.Generic;
using System.Text;
using Cs_Compile_test.com.exceptions;

namespace Cs_Compile_test.com {
	public static class MemoryManager {

		private static readonly Dictionary<int, ShadoObject> objects = new Dictionary<int, ShadoObject>();

		public static void AddVariable(ShadoObject obj) {
			objects.Add(obj.GetHashCode(), obj);
		}

		public static ShadoObject GetByAddress(int address) {
			try {
				return objects[address];
			}
			catch (Exception e) {
				throw new RuntimeError("Cannot find the variable with address {0}", address);
			}
		}

	}
}
