/**
 * 
 */
package multirotors;

import helicopters.Helicopter;

/**
 * @author shadi
 *
 */
public class Multirotor extends Helicopter {

	private int nbRotors;

	/**
	 * 
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
					&& passengerCapacity == temp.passengerCapacity && nbCylinders == temp.nbCylinders
					&& nbCylinders == temp.nbRotors;
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
