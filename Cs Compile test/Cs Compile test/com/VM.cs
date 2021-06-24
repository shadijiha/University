using System;
using System.Collections.Generic;
using System.Linq;
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


			ShadoMethod method_print = new ShadoMethod("print", 1, "void", true, new String[] { "object" });
			method_print.SetCode((context, obj) => {
				foreach (object e in obj) {
					string o = e.ToString();
					if (o.StartsWith("\"") && o.EndsWith("\"")) {
						o = o.Substring(1, o.Length - 1);            // Keep the string literal without the quotes ("")
					} else if (!o.Contains("\"")) {
						o = VM.instance.Get(o).value.ToString();        // Get the value of the variable if it is not a raw string
					} else {
						throw new SyntaxError("invalid syntax");
					}
					Console.Write(o);
				}

				Console.WriteLine();
				return null;
			});
			PushVariable(method_print);

			ShadoMethod method_typeof = new ShadoMethod("typeof", 1, "string", new String[] { "object" });
			method_typeof.SetCode((ShadoObject ctx, object[] obj)=> {
				var var = this.Get(obj[0].ToString());
				if (var != null)
					Console.WriteLine(var.type);
				return null;
			});
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
			classes.Add(new ShadoClass(clazz.name + "[]", new TypeValidator(clazz.name + "[]", o => true)));
		}

		public void AddType(string type, Predicate<Object> validator) {
			AddType(new ShadoClass(type, new TypeValidator(type, validator)));
		}

		public void PushVariable(ShadoObject obj) {
			if (Get(obj.name) != null)
				throw new CompilationError("variable %s already exists", obj.name);

			if (!HasType(obj.type))
				throw new CompilationError("type %s does not exist", obj.type);

			// Verify type compatibility
			bool valid = IsValidType(obj.type, obj.value);
			if (!valid)
				throw new CompilationError("Cannot assign %s to a variable of type %s", obj.value, obj.type);

			// Verify if it is a pointer
			/*if (obj.IsPointer()) {
				obj.value = obj.GetHashCode();
			}*/

			variables.Add(obj);
		}

		public ShadoObject Get(string varName) {
			return variables.Where(e => e.name == varName).FirstOrDefault();
		}

		public ShadoObject GetOrThrow(String varName, int line) {

			ShadoObject obj = Get(varName);
			if (obj != null)
				return obj;
			throw new CompilationError("an error has occurred at line {0}", line);
		}

		public ShadoClass GetClass(string name) {
			foreach (ShadoClass klass in classes)
				if (klass.name == name)
					return klass;
			return null;
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
