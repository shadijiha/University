public class dice {
	public int die1;
	public int die2;

	public dice() {
		this.die1 = die1;
		this.die2 = die2;
	}

	public int die1() {
		return this.die1;
	}

	public int die2() {
		return this.die2;
	}

	public int rollDice() {
		int sum;
		this.die1 = (int) (Math.random() * 6) + 1;
		this.die2 = (int) (Math.random() * 6) + 1;
		sum = this.die1 + this.die2;
		return sum;
	}

	public boolean isDouble() {
		boolean isDouble;
		if (this.die1 == this.die2) {
			isDouble = true;
		} else
			isDouble = false;
		return isDouble;

	}

	public String toString() {
		String toString = "Die 1: " + this.die1 + " Die 2: " + this.die2 + ".";
		return toString;
	}
}