/**
 *
 */
package multirotors;

import airplane.Flyable;
import helicopters.Helicopter;

/**
 * @author shadi
 *
 */
public class Multirotor extends Helicopter implements Flyable {

	private int nbRotors;

	/**
	 * Full constructor
	 * @param brand The brand of multirotor
	 * @param price The price
	 * @param horsePower The power
	 * @param nbCylinders The number of cylinder
	 * @param creationYear The creation year
	 * @param passengerCapacity The maximum passenger capacity
	 * @param nbRotors The number of rotors
	 */
	public Multirotor(String brand, double price, int horsePower, int nbCylinders, int creationYear,
					  int passengerCapacity, int nbRotors) {
		super(brand, price, horsePower, nbCylinders, creationYear, passengerCapacity);
		this.nbRotors = nbRotors;
	}

	public Multirotor() {
		this("", 0.0, 0, 0, 0, 0, 0);
	}

	public Multirotor(final Multirotor other) {
		super(other);
		nbRotors = other.nbRotors;
	}

	/**
	 * Compares 2 Helicopters if they have equal attributes
	 *
	 * @param o The other object to compare
	 * @return Return true if the object are identical, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass())
			return false;
		else {
			Multirotor temp = (Multirotor) o;
			return brand.equals(temp.brand) && price == temp.price && horsePower == temp.horsePower
					&& nbCylinders == temp.nbCylinders && creationYear == temp.creationYear
					&& passengerCapacity == temp.passengerCapacity && nbRotors == temp.nbRotors;
		}
	}

	/**
	 * @return Return a string
	 */
	@Override
	public String toString() {
		return String.format(
				"This Multirotor was manufactured in %d. %s. It has %d cylinders, %d rotors and %d maximum passenger capacity",
				creationYear, super.toString(), nbCylinders, passengerCapacity, nbRotors);
	}

	/**
	 * @return Returns a copy of the calling object
	 */
	public Flyable copy() {
		return new Multirotor(this);
	}

	/**
	 * @return the nbRotors
	 */
	public int getNbRotors() {
		return nbRotors;
	}

	/**
	 * @param nbRotors the nbRotors to set
	 */
	public void setNbRotors(int nbRotors) {
		this.nbRotors = nbRotors;
	}

}
