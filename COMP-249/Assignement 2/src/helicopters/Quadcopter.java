/**
 *
 */
package helicopters;

/**
 * @author shadi
 *
 */
public class Quadcopter extends Helicopter {

	private int maxFlyingSpeed;

	/**
	 *
	 */
	public Quadcopter(String brand, double price, int horsePower, int nbCylinders, int creationYear,
					  int passengerCapacity, int maxSpeed) {
		super(brand, price, horsePower, nbCylinders, creationYear, passengerCapacity);
		maxFlyingSpeed = maxSpeed;
	}

	public Quadcopter() {
		this("", 0.0, 0, 0, 0, 0, 0);
	}

	public Quadcopter(final Quadcopter other) {
		this(other.brand, other.price, other.horsePower, other.nbCylinders, other.creationYear, other.passengerCapacity,
				other.maxFlyingSpeed);
	}

	/**
	 * Compares 2 objects
	 *
	 * @param o The other object to compare
	 * @return Return true if the object are identical, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		else if (o.getClass() != getClass())
			return false;
		else {
			Quadcopter temp = (Quadcopter) o;
			return brand.equals(temp.brand) && price == temp.price && horsePower == temp.horsePower
					&& nbCylinders == temp.nbCylinders && creationYear == temp.creationYear
					&& passengerCapacity == temp.passengerCapacity && nbCylinders == temp.nbCylinders
					&& maxFlyingSpeed == temp.maxFlyingSpeed;
		}
	}

	/**
	 * @return Return a string that represents the calling object
	 */
	@Override
	public String toString() {
		return String.format(
				"This Multirotor was manufactured in %d. %s. It has %d cylinders, %d rotors and a maximum speed of %d km/h",
				creationYear, super.toString(), nbCylinders, passengerCapacity, maxFlyingSpeed);
	}

	/**
	 * @return Returns a copy of the calling object
	 */
	public Quadcopter copy() {
		return new Quadcopter(this);
	}


	/**
	 * @return the maxFlyingSpeed
	 */
	public int getMaxFlyingSpeed() {
		return maxFlyingSpeed;
	}

	/**
	 * @param maxFlyingSpeed the maxFlyingSpeed to set
	 */
	public void setMaxFlyingSpeed(int maxFlyingSpeed) {
		this.maxFlyingSpeed = maxFlyingSpeed;
	}

}
