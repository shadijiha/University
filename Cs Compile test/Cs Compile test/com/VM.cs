using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using Cs_Compile_test.com.exceptions;
using Cs_Compile_test.com.nativeTypes;

namespace Cs_Compile_test.com {
	public sealed class VM
	{
		public static readonly VM instance = new VM();

		private IList<ShadoClass> classes;
		private IList<ShadoObject> variables;
		private Random random;
		private bool hasInitialized = false;

		private VM() {
			classes = new List<ShadoClass>();
			variables = new List<ShadoObject>();
			random = new Random();
		}

		public void Initialize() {

			if (hasInitialized)
				return;
			hasInitialized = true;

			//AddType("object", o=> true);
			AddType("function", o=> o == null || o.GetType() == typeof(ShadoMethod));
			AddType(new ShadoInt());
			AddType(new ShadoFloat());
			AddType(new ShadoDouble());
			AddType(new ShadoBool());
			AddType(new ShadoString());
			AddType(new ShadoChar());
			AddType(new ShadoVoid());
			AddType(new ShadoFileReader());
			AddType(new ShadoFileWriter());


			ShadoMethod method_print = new ShadoMethod("print", 1, "void", true, new string[] { "object" });
			method_print.SetCode((context, obj) => {
				foreach (object e in obj) {
					string o = e?.ToString().Trim() ?? "\"null\"";
					if (o.StartsWith("\"") && o.EndsWith("\"")) {
						o = Regex.Unescape(o.Substring(1,
							o.Length - 2)); // Keep the string literal without the quotes ("")
					}
					else if (context.HasVariable(o) || Get(o) != null) {
						o = context.GetVariable(o)?.ToString() ??
						    VM.instance.Get(o)?.ToString(); // Get the value of the variable if it is not a raw string
					}
					// Otherwise try to find the type of the variable or just print the C# type (Mainly the variable is an R-value)
					else {
						ShadoObject temp = new ShadoObject(GetTypeOf(e), e);
						o = temp.ToString() ?? o;
					}

					Console.Write(o + " ");
				}
				Console.WriteLine();
				return null;
			});
			PushVariable(method_print);

			ShadoMethod method_typeof = new ShadoMethod("typeof", 1, "string", new string[] { "object" });
			method_typeof.SetCode((ctx, obj) => 
				ctx.GetVariable(obj[0].ToString())?.type.name ?? Get(obj[0].ToString())?.type.name ?? GetTypeOf(obj[0])?.name ?? "object");
			PushVariable(method_typeof);

			ShadoMethod method_random = new ShadoMethod("random", 0, "double");
			method_random.SetCode((ctx, obj) => random.NextDouble());
			PushVariable(method_random);

			ShadoMethod method_clear = new ShadoMethod("clear", 0, "void");
			method_clear.SetCode((ctx, obj) => { Console.Clear(); return null; });
			PushVariable(method_clear);

			ShadoMethod method_sleep = new ShadoMethod("sleep", 1, "void");
			method_sleep.SetCode((ctx, obj) => { Thread.Sleep(int.Parse(obj[0].ToString())); return null; });
			PushVariable(method_sleep);

			ShadoMethod method_sqrt = new ShadoMethod("sqrt", 1, "double");
			method_sqrt.SetCode((ctx, obj) => Math.Sqrt(double.Parse(obj[0].ToString())));
			PushVariable(method_sqrt);

			ShadoMethod method_pow = new ShadoMethod("pow", 2, "double");
			method_pow.SetCode((ctx, obj) => Math.Pow(double.Parse(obj[0].ToString()), double.Parse(obj[1].ToString())));
			PushVariable(method_pow);

			ShadoMethod method_print_types = new ShadoMethod("print_all_types", 0, "void");
			method_print_types.SetCode((ctx, obj) => {
				foreach (var shadoClass in VM.instance.GetAllTypes()) {
					Console.WriteLine("Type:\t{0}", shadoClass);
					foreach (var shadoMethod in shadoClass.GetMethods()) {
						Console.WriteLine("\t\t{0}\t{1}", shadoMethod.name, shadoMethod.GetFullType());
					}
				}
				return null;
			});
			PushVariable(method_print_types);

			ShadoMethod print_user_defined_types = new ShadoMethod("print_user_defined_types", 0, "void");
			print_user_defined_types.SetCode((ctx, obj) => {
				foreach (var shadoClass in VM.instance.GetAllTypes()) {
					if (shadoClass.GetType().Namespace.Contains("native"))
						continue;

					Console.WriteLine("Type:\t{0}", shadoClass);
					foreach (var shadoMethod in shadoClass.GetMethods()) {
						Console.WriteLine("\t\t{0}\t{1}", shadoMethod.name, shadoMethod.GetFullType());
					}
				}
				return null;
			});
			PushVariable(print_user_defined_types);
		}

		public void Shutdown() {
			foreach (var variable in variables) {
				// See if it is a method, then get all its inner variable
				if (variable is ShadoMethod method) {
					foreach (var methodVar in method.GetAllVariables()) {
						// If the method is auto closable
						if (methodVar.type is ICloseBeforeExit closable) {
							closable.Close(methodVar);
						}
					}
				}
				else {
					// Otherwise see if the variable is auto closable
					if (variable.type is ICloseBeforeExit closable) {
						closable.Close(variable);
					}
				}
			}
		}

		public void InvokeMain(int argc = 0, string[] argv = null) {
			ShadoMethod main = Get("main") as ShadoMethod;
			main.optionalArgs = true;
			if (main == null)
				throw new RuntimeError("Method main not found!");
			main.Call(ShadoObject.Global, new object[]{argc, argv.Cast<object>().ToList()});
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

			// If type already exists
			if (classes.Contains(clazz)) {
				classes.Remove(clazz);
				classes.Remove(new ShadoClass(clazz.name + "*"));
				classes.Remove(classes.Single(e => e.name == clazz.name + "[]"));
			}

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

		public ShadoClass? GetTypeOf(object o) {
			foreach (ShadoClass clazz in GetAllTypes()) {
				if (clazz.IsValid(o) && clazz.name != "object" && clazz.name != "object*" && clazz.name != "object[]")
					return clazz;
			}

			return GetClass("object");
		}

		public static ShadoClass GetSuperType() {
			return instance.GetClass("object");
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
