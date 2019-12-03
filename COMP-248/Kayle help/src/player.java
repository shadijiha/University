public class player {
	private String name;
	private int level;
	private int x;
	private int y;
	private int energy;

	public player() {
		this.name = "";
		this.level = 0;
		this.x = 0;
		this.y = 0;
		this.energy = 10;
	}

	public player(String name) {
		level = 0;
		x = 0;
		y = 0;
		this.name = name;
		energy = 10;
	}

	public player(int level, int x, int y) {
		this.level = level;
		this.x = x;
		this.y = y;
		name = "";
		energy = 10;
	}

	public player(player a) {
		x = a.getX();
		y = a.getY();
		level = a.getLevel();
		energy = a.getEnergy();
		name = a.getName();
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return this.level;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getEnergy() {
		return this.energy;
	}

	public String getName() {
		return this.name;
	}

	public boolean isEqual(player p) {
		boolean equal;
		if (p.level == level && p.x == x && p.y == y) {
			equal = true;
		} else
			equal = false;
		return equal;

	}

	public String toString() {
		return name + " is on level " + level + " at location (" + x + "," + y + ") and has " + energy
				+ " units of energy.";
	}

	public void moveTo(player p) {
		level = p.getLevel();
		x = p.getX();
		y = p.getY();
	}

	public boolean won(board b) {
		boolean winner;
		int levelMax = b.getLevel();
		int sizeMax = b.getSize();
		if (level == levelMax - 1 && x == sizeMax - 1 && y == sizeMax - 1) {
			winner = true;
		} else
			winner = false;
		return winner;
	}

}