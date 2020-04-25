//  -----------------------------------------------------
// Assignment #4
// Question: 2
// Written by: Shadi Jiha #40131284
//  -----------------------------------------------------

package part2;

public class uint32_t {

	private int value = 0;

	public uint32_t(int value) {
		assigne(value);
	}

	public uint32_t(uint32_t other) {
		this(other.value);
	}

	private void assigne(int val) {
		if (val < 0) {
			NegativeUnsignedInt e = new NegativeUnsignedInt("Assigning a negative number to an unsigned 32 bit integer!");
			e.printStackTrace();
		}
		value = val;
	}

	public int value() {
		return Math.max(value, 0);
	}

	public void set(int val) {
		assigne(val);
	}

	public void increment(int increment_value) {
		assigne(this.value + increment_value);
	}

	public void increment() {
		increment(1);
	}

	public boolean divides(int number) {
		return value() % number == 0;
	}

	public boolean divides(uint32_t number) {
		return value() % number.value() == 0;
	}

	private class NegativeUnsignedInt extends Exception {
		protected NegativeUnsignedInt(String msg) {
			super(msg);
		}

		protected NegativeUnsignedInt() {
			super();
		}
	}
}
