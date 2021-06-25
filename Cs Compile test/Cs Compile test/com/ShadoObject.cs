using System;
using System.Collections.Generic;
using System.Text;
using Cs_Compile_test.com.exceptions;

namespace Cs_Compile_test.com {
	public class ShadoObject {
		static readonly Random random = new Random();
		public static readonly ShadoObject Global = new ShadoObject("object", "GLOBAL", null);

		public ShadoClass type { get; set; }
		public string name { get; set; }
		private object _value;
		private int id;

		public ShadoObject(ShadoClass clazz, string name, object value) {
			this.type = clazz;
			this.name = name;
			this.value = value;
			// Keep the string literal without the quotes ("")
			if (type != null && type.name == "string") {
				char[] arr = value.ToString().ToCharArray();
				if (arr[0] == '"')
					arr[0] = ' ';
				if (arr[^1] == '"')
					arr[^1] = ' ';

				this.value = new string(arr).Trim();
			}

			this.id = random.Next(int.MaxValue);
		}

		public ShadoObject(string type, string name, object value)
			: this(VM.GetInstance().GetClass(type), name, value)
		{
		}

		public ShadoObject(string type, object value)
			: this(type, "", value)
		{
			name = "temp_" + GetHashCode();
		}

		public object value {
			get => _value;
			set {
				if (value != null && !(type?.IsValid(value) ?? true))
					throw new RuntimeError("Cannot assign {0} to an object of type {1}", value.ToString(), type.name);
				_value = value;
			}
		}

		public virtual bool IsMethod() {
			return false;
		}

		public virtual bool IsPointer() {
			return type.name.Trim().EndsWith("*");
		}

		public bool IsReference() {
			return type.name.Trim().EndsWith("&");
		}

		public bool IsValue() {
			return !IsPointer() && !IsReference();
		}

		public override int GetHashCode() {
			return id;
		}

		public override string ToString() {
			return type.GetMethod("toString").Call(this, null).ToString();
		}
	}
}
