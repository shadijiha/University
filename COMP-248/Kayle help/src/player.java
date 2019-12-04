
public class player {
	private String name; // The name of the player
	private int level; // The player's current level
	private int x; // The player's current row
	private int y; // The player's current column
	private int energy; // The player's current amount of energy

	public player() // Default constructor
	{
		this.name = "";
		this.level = 0;
		this.x = 0;
		this.y = 0;
		this.energy = 10;
	}

	public player(String name) // A constructor that stores the player with the player's name
	{
		level = 0;
		x = 0;
		y = 0;
		this.name = name;
		energy = 10;
	}

	public player(int level, int x, int y) // A constructor that stores the player's current location
	{
		this.level = level;
		this.x = x;
		this.y = y;
		name = "";
	}

	public player(player a) // A constructor to get all the information
	{
		this.x = a.getX();
		this.y = a.getY();
		this.level = a.getLevel();
		this.energy = a.getEnergy();
		this.name = a.getName();
	}

	public void setLevel(int level) // A method that sets the level
	{
		this.level = level;
	}

	public void setX(int x) // A method that sets the row
	{
		this.x = x;
	}

	public void setY(int y) // A method that sets the column
	{
		this.y = y;
	}

	public void setEnergy(int energy) // A method that sets the energy
	{
		this.energy = energy;
	}

	public void setName(String name) // A method that sets the name
	{
		this.name = name;
	}

	public int getLevel() // A method that gets the level
	{
		return this.level;
	}

	public int getX() // A method that gets the row
	{
		return this.x;
	}

	public int getY() // A method that gets the column
	{
		return this.y;
	}

	public int getEnergy() // A method that gets the energy
	{
		return this.energy;
	}

	public String getName() // A method that gets the energy
	{
		return this.name;
	}

	public boolean isEqual(player p) // A method that verifies if the player is at a location
	{
		boolean equal; // A boolean that is used to verify if the player is at the location
		if (p.level == level && p.x == x && p.y == y) {
			equal = true;
		} else
			equal = false;
		return equal;

	}

	public String toString() // A method that tells the player their location and amount of energy
	{
		return name + " is on level " + level + " at location (" + x + "," + y + ") and has " + energy
				+ " units of energy.";
	}

	public void moveTo(player p) // A method that moves the player
	{
		this.level = p.getLevel();
		this.x = p.getX();
		this.y = p.getY();
	}

	public boolean won(board b) // A method that verifies whether a player has won the game
	{
		boolean winner; // A boolean used to verify if the player won the game
		int levelMax = b.getLevel(); // An integer to represent the max level
		int sizeMax = b.getSize(); // An integer to represent the max of one size
		if (level >= levelMax - 1 && x >= sizeMax - 1 && y >= sizeMax - 1) {
			winner = true;
		} else
			winner = false;
		return winner;
	}

}