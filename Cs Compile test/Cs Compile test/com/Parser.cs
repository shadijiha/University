using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Cs_Compile_test.com {
	public static class Parser {

		public static IList<Block> ParseBlocks(string block) {

			List<Block> blocks = new List<Block>();
			blocks.Add(new Block(""));

			int current = 0;
			foreach (char c in block) {

				if (c == '{')
					current++;
				else if (c == '}')
					current--;

				if (current < 0)
					break;

				if (blocks.Count <= current) { 
					blocks.Add(new Block(""));
				}

				blocks[current].AppendCode(c);
			}

			return blocks;
		}
	}

	public class Block
	{
		public string code { get; private set; }
		public IList<Block> subblocks { get; }

		public Block(string str) {
			code = str;
			subblocks = new List<Block>();
		}

		public void Add(Block block) {
			subblocks.Add(block);
		}

		public Block SetCode(string str) {
			code = str;
			return this;
		}

		public Block AppendCode(string toAppend) {
			code += toAppend;
			return this;
		}

		public Block AppendCode(char c) {
			code += c;
			return this;
		}

		public override string ToString() {
			return code;
		}
	}
}
