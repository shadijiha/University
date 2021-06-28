using System;
using System.Collections.Generic;
using System.Text;
using Cs_Compile_test.com.exceptions;

namespace Cs_Compile_test.com {
	public class ShadoMethod : ShadoObject {

		public delegate object MethodCall(ShadoObject context, object[] args);

		protected int argCount;
		protected string returnType;
		protected string[] argTypes;
		protected MethodCall code;
		protected bool optionalArgs;
		protected string fullType;

		public ShadoMethod(string name, int argCount, string returnType, bool optionalArgs, string[] argTypes)
			: base("function", name, null)
		{
			this.argCount = argCount;
			this.returnType = returnType;
			this.argTypes = argTypes;
			this.optionalArgs = optionalArgs;

			// Compute type
			string method_full_type = returnType + "(";
			int i = 1;
			foreach (string rawArg in argTypes) {
				method_full_type += rawArg + (i++ < argCount ? "," : "");
			}
			method_full_type += ")";
			this.fullType = method_full_type;

			//VM.getInstance().addType(method_full_type, o -> true);
			//this.type = VM.getInstance().getClass(method_full_type);
		}

		public ShadoMethod(string name, int argCount, string returnType, string[] argTypes)
			: this(name, argCount, returnType, false, argTypes) {
			
		}

		public ShadoMethod(String name, int argCount, String returnType)
			: this(name, argCount, returnType, new string[argCount]) {
			for (int i = 0; i < argCount; i++)
				argTypes[i] = "object";
		}

		public ShadoMethod(ShadoMethod o)
			: this(o.name, o.argCount, o.returnType, o.optionalArgs, o.argTypes) {
			code = o.code;
		}

		public object Call(ShadoObject context, Object[] args) {
			if (context == null)
				throw new RuntimeError("Null pointer exception: Cannot call a method on null");

			int c = args?.Length ?? 0;
			if (!optionalArgs && c != argCount)
				throw new RuntimeError("method arguments mismatch (call for {0})", this.name);

			// Bind args value to names
			for (int i = 0; i < c; i++) {
				string argType = i >= argTypes.Length ? "object" : argTypes[i];
				string name = i >= instanceVariables.Count ? "temp_" + GetHashCode() : instanceVariables[i].name;
				AddOrUpdateVariable(argType, name, args[i]);
			}


			return code(context, args);
		}

		public ShadoMethod SetCode(MethodCall code) {
			this.code = code;
			return this;
		}

		public String GetFullType() {
			return fullType;
		}

		public override bool IsMethod() {
			return true;
		}

		/**
		 * Method is always a pointer
		 * @return
		 */
		public override bool IsPointer() {
			return true;
		}

		public string[] GetArgTypes() {
			return argTypes;
		}

		public override bool Equals(object obj) {
			if (obj == null || obj.GetType() != GetType())
				return false;
			else if (obj == this)
				return true;
			else {
				var o = (ShadoMethod) obj;
				return name == o.name && argCount == o.argCount;
			}
		}
	}
}
