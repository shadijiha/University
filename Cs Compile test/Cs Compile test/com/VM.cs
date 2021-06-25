using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using Cs_Compile_test.com.exceptions;
using Cs_Compile_test.com.nativeTypes;

namespace Cs_Compile_test.com {
	public sealed class VM
	{
		public static readonly VM instance = new VM();

		private IList<ShadoClass> classes;
		private IList<ShadoObject> variables;

		private VM() {
			classes = new List<ShadoClass>();
			variables = new List<ShadoObject>();
		}

		public void Initialize() {

			AddType("object", o=> true);
			AddType("function", o=> true);
			AddType(new ShadoInt());
			AddType(new ShadoFloat());
			AddType(new ShadoDouble());
			AddType(new ShadoBool());
			AddType(new ShadoString());
			AddType(new ShadoChar());
			AddType(new ShadoVoid());


			ShadoMethod method_print = new ShadoMethod("print", 1, "void", true, new string[] { "object" });
			method_print.SetCode((context, obj) => {
				foreach (object e in obj) {
						string o = e.ToString().Trim();
						if (o.StartsWith("\"") && o.EndsWith("\"")) {
							o = o.Substring(1, o.Length - 2);            // Keep the string literal without the quotes ("")
						} else if (!o.Contains("\"")) {
							o = VM.instance.GetOrThrow(o).ToString(); // Get the value of the variable if it is not a raw string
						} else {
							throw new SyntaxError("invalid syntax");
						}
						Console.Write(o + " ");
					}

					Console.WriteLine();
					return null;
			});
			PushVariable(method_print);

			ShadoMethod method_typeof = new ShadoMethod("typeof", 1, "string", new string[] { "object" });
			method_typeof.SetCode((ctx, obj) => this.Get(obj[0].ToString()).type.name);
			PushVariable(method_typeof);
		}

		public bool IsValidType(ShadoClass clazz, Object value) {
			if (clazz == null)
				throw new RuntimeError("A type does not exist");

			return clazz.IsValid(value) || clazz.name == "object" || clazz.name == "object*";
		}

		public bool HasType(ShadoClass clazz) {
			return classes.Any(e=>e.Equals(clazz));
		}

		public bool HasType(string type) {
			return classes.Any(e => e.name == type);
		}

		public void AddType(ShadoClass clazz) {
			classes.Add(clazz);

			// Add pointer and reference type
			classes.Add(new ShadoClass(clazz.name + "*", new TypeValidator(clazz.name + "*", o => {

				// if o is a variable
				ShadoObject var = VM.instance.Get(o.ToString());
				if (var != null)
					return var.type.name == clazz.name + "*";

				// If o is an integer
				if (int.TryParse(o.ToString(), out _))
					return true;

				// Otherwise
				string rvalue = o.ToString().Trim();
				if (!rvalue.StartsWith("&"))
					return false;

				// Get type of variable without &
				rvalue = rvalue.Substring(1);
				var = VM.instance.Get(rvalue);
				if (var == null)
					throw new RuntimeError("Cannot get the pointer of a null variable");

				// Otherwise it is ok
				return clazz.name == var.type.name;
			})));


			// Add array type
			classes.Add(new ShadoArray(clazz.name + "[]"));;
		}

		public void AddType(string type, Predicate<Object> validator) {
			AddType(new ShadoClass(type, new TypeValidator(type, validator)));
		}

		public void PushVariable(ShadoObject obj) {
			if (Get(obj.name) != null)
				throw new CompilationError("variable {0} already exists", obj.name);

			if (!HasType(obj.type))
				throw new CompilationError("type {0} does not exist", obj.type);

			// Verify type compatibility
			bool valid = IsValidType(obj.type, obj.value);
			if (!valid)
				throw new CompilationError("Cannot assign {0} to a variable of type {1}", obj.ToString(), obj.type);

			// Verify if it is a pointer
			/*if (obj.IsPointer()) {
				obj.value = obj.GetHashCode();
			}*/

			variables.Add(obj);
		}

		public ShadoObject Get(string varName) {
			return variables.Where(e => e.name == varName).FirstOrDefault();
		}

		public ShadoObject GetOrThrow(string varName) {

			ShadoObject obj = Get(varName);
			if (obj != null)
				return obj;
			throw new CompilationError("an error has occurred");
		}

		public ShadoClass GetClass(string name) {
			foreach (ShadoClass klass in classes)
				if (klass.name == name)
					return klass;
			return null;
		}

		public ShadoObject GetByAddress(int hashCode) {
			var it = from obj in variables
				where obj.GetHashCode() == hashCode
				select obj;

			return it.First();
		}

		public List<ShadoObject> AllVariables() {
			return new List<ShadoObject>(variables);
		}

		public IList<ShadoClass> GetAllTypes() {
			return new List<ShadoClass>(classes);
		}

		public static VM GetInstance() {
			return instance;
		}
	}
}
