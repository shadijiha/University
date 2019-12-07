public class dice {
	public int die1; // An integer to use as the first die
	public int die2; // An integer to use as the second die

	public dice() // Default constructor
	{

	}

	public int getDie1() // A return method for die 1
	{
		return this.die1;
	}

	public int getDie2() // A return method for die 2
	{
		return this.die2;
	}

	public int rollDice() // A method to serve as a dice roll
	{
		int sum; // An integer that will calculate the total of the dice
		this.die1 = (int) (Math.random() * 6) + 1;
		this.die2 = (int) (Math.random() * 6) + 1;
		sum = this.die1 + this.die2;
		return sum;
	}

	public boolean isDouble() // A method to verify if the roll is a double
	{
		boolean isDouble; // A boolean used to return if the roll is a double
		if (this.die1 == this.die2) {
			isDouble = true;
		} else
			isDouble = false;
		return isDouble;

	}

	public String toString() // A method to tell the player what the value of each die is
	{
		String toString = "Die 1: " + this.die1 + " Die 2: " + this.die2 + "."; // A string that will be used to return
																				// the values of each die
		return toString;
	}
}