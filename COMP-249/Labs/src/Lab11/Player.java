package Lab11;

/**
 *
 */

public class Player implements Comparable<Player> {

	private String name;
	private int health;

	public Player(String name, int health) {
		this.name = name;
		this.health = health;
	}

	public Player(final Player other) {
		this(other.name, other.health);
	}

	@Override
	public int compareTo(Player o) {
		return Integer.compare(this.health, o.health);
	}

	@Override
	public String toString() {
		return "Lab11.Player\t{" +
				"name='" + name + '\'' +
				", health=" + health +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		} else {
			Player p = (Player) o;
			return p.health == health && p.name.equals(name);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
