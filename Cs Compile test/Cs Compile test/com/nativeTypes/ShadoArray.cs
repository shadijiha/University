using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Cs_Compile_test.com.exceptions;

namespace Cs_Compile_test.com.nativeTypes {
	public class ShadoArray : ShadoClass {

		public ShadoArray(string name) : base(name, new TypeValidator(name, o => {
			var type = VM.instance.GetClass(name).GetUnitType();
			var list = o as List<object>;
			if (list == null)
				throw new RuntimeError("Cannot assign {0} to a variable of type {1}", o.ToString(), name);

			foreach (var obj in list) {
				if (!type.IsValid(obj))
					return false;
			}
			return true;
		})) { }

		protected override void initializeMethods() {
			base.initializeMethods();

			ShadoMethod toString = new ShadoMethod("toString", 0, "string");
			toString.SetCode((ctx, args) => {
				StringBuilder builder = new StringBuilder();

				builder.Append("[");

				var list = ctx.value as List<object>;
				int i = 0;
				foreach (var o in list) {
					builder.Append(o);
					if (i < list.Count - 1)
						builder.Append(", ");

					i++;
				}
				builder.Append("]");

				return builder.ToString();
			});
			AddMethod(toString);

			// Add length method
			AddMethod(new ShadoMethod("length", 0, "int").SetCode((ctx, args) => (ctx.value as List<object>).Count));
			AddMethod(new ShadoMethod("at", 1, GetUnitType().name)
				.SetCode((ctx, args) => (ctx.value as List<object>)[int.Parse(args[0].ToString())]));

			AddMethod(new ShadoMethod("add", 1, "void")
				.SetCode((ctx, args) => {
					(ctx.value as List<object>).Add(args[0]);
					return null;
				}));

			AddMethod(new ShadoMethod("addAt", 2, "void")
				.SetCode((ctx, args) => {
					(ctx.value as List<object>).Insert(int.Parse(args[0].ToString()), args[1]);
					return true;
				}));

			AddMethod(new ShadoMethod("pop", 0, GetUnitType().name)
				.SetCode((ctx, args) => {
					var list = (ctx.value as List<object>);
					var obj = list.Last();
					list.RemoveAt(list.Count - 1);
					return obj;
				}));

			AddMethod(new ShadoMethod("contains", 1, "bool")
				.SetCode((ctx, args) => (ctx.value as List<object>).Contains(args[0])));
		}

	}
}
