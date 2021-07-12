using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Cs_Compile_test.com.exceptions;

namespace Cs_Compile_test.com.nativeTypes {

	public interface ICloseBeforeExit { }

	public class ShadoFileReader : ShadoClass, ICloseBeforeExit {

		public ShadoFileReader() : base("FileReader", new TypeValidator("FileReader", o => true)) { }

		protected override void initializeMethods() {
			base.initializeMethods();
			
			ShadoMethod constructor = new ShadoMethod("FileReader", 1, "FileReader");
			constructor.SetCode((ctx, args) => new ShadoObject(this, null).AddOrUpdateVariable("string", "filepath", args[0]));
			AddMethod(constructor);

			ShadoMethod open = new ShadoMethod("open", 0, "void");
			open.SetCode((ctx, args) => {
				string filepath = ctx.GetVariable("filepath").value.ToString().Trim();
				ctx.AddOrUpdateVariable("object", "stream", new StreamReader(filepath));
				return null;
			});
			AddMethod(open);

			ShadoMethod close = new ShadoMethod("close", 0, "void");
			close.SetCode((ctx, args) => {
				this.GetReadStream(ctx).Close();
				return null;
			});
			AddMethod(close);

			ShadoMethod readline = new ShadoMethod("readLine", 0, "string");
			readline.SetCode((ctx, args) => this.GetReadStream(ctx).ReadLine());
			AddMethod(readline);

			ShadoMethod hasnext = new ShadoMethod("hasNext", 0, "bool");
			hasnext.SetCode((ctx, args) => !this.GetReadStream(ctx).EndOfStream);
			AddMethod(hasnext);

			ShadoMethod readToEnd = new ShadoMethod("readToEnd", 0, "string");
			readToEnd.SetCode((ctx, args) => this.GetReadStream(ctx).ReadToEnd());
			AddMethod(readToEnd);
		}
	}

	public class ShadoFileWriter : ShadoClass, ICloseBeforeExit {
		public ShadoFileWriter() : base("FileWriter", new TypeValidator("FileWriter", o => true)) { }

		protected override void initializeMethods() {
			base.initializeMethods();

			ShadoMethod constructor = new ShadoMethod("FileWriter", 1, "FileWriter");
			constructor.SetCode((ctx, args) => 
				new ShadoObject(this, null).AddOrUpdateVariable("string", "filepath", args[0])	// Todo: This much be an evaluated expression in the future
				);
			AddMethod(constructor);

			ShadoMethod open = new ShadoMethod("open", 0, "void");
			open.SetCode((ctx, args) => {
				string filepath = ctx.GetVariable("filepath").value.ToString().Trim();
				ctx.AddVariable("object", "stream", new StreamWriter(filepath));
				return null;
			});
			AddMethod(open);

			ShadoMethod close = new ShadoMethod("close", 0, "void");
			close.SetCode((ctx, args) => {
				StreamWriter stream = ctx.GetVariable("stream").value as StreamWriter;
				if (stream == null)
					throw new RuntimeError("Could not open stream to " + ctx.GetVariable("filepath"));
				stream.Close();
				return null;
			});
			AddMethod(close);

			ShadoMethod write = new ShadoMethod("write", 1, "void");
			write.SetCode((ctx, args) => {
				this.GetWriteStream(ctx).Write(args[0].ToString());
				return null;
			});
			AddMethod(write);

			ShadoMethod writeln = new ShadoMethod("writeln", 1, "void");
			writeln.SetCode((ctx, args) => {
				args[0] = args[0] + "\n";
				ctx.type.GetMethod("write").Call(ctx, args);
				return null;
			});
			AddMethod(writeln);

			ShadoMethod flush = new ShadoMethod("flush", 0, "void");
			flush.SetCode((ctx, args) => {
				this.GetWriteStream(ctx).Flush();
				return null;
			});
			AddMethod(flush);

			ShadoMethod append = new ShadoMethod("append", 1, "void");
			append.SetCode((ctx, args) => {
				var stream = this.GetWriteStream(ctx);
				stream.BaseStream.Seek(0, SeekOrigin.End);
				stream.Write(args[0].ToString());
				return null;
			});
			AddMethod(append);
		}
	}
}
