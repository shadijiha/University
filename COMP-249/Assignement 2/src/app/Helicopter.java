// -----------------------------------------------------
// Part: I
// Written by: Shadi Jiha #40131284
// ----------------------------------------------------

package app;

public class Helicopter extends Airplane {
	protected int nbCylinders;
	protected int creationYear; // Maximum moving speed
	protected int passengerCapacity;

	/***
	 * Full constructor
	 * 
	 * @param brand             The brand of the function
	 * @param price             The price of the airplane
	 * @param horsePower        The power of the airplane
	 * @param nbCylinders       The number of cylinders the plane has
	 * @param maxSpeed          The max moving speed
	 * @param passengerCapacity The max passenger that airplane can hold
	 */
	public Helicopter(String brand, double price, int horsePower, int nbCylinders, int creationYear,
			int passengerCapacity) {
		super(brand, price, horsePower);
		this.nbCylinders = nbCylinders;
		this.creationYear = creationYear;
		this.passengerCapacity = passengerCapacity;
	}

	/**
	 * @param nbCylinders       The number of cylinders the plane has
	 * @param maxSpeed          The max moving speed
	 * @param passengerCapacity The max passenger that airplane can hold
	 */
	public Helicopter(int nbCylinders, int creationYear, int passengerCapacity) {
		super();
		this.nbCylinders = nbCylinders;
		this.creationYear = creationYear;
		this.passengerCapacity = passengerCapacity;
	}

	/**
	 * Default constructor
	 */
	public Helicopter() {
		this(0, 0, 0);
	}

	/**
	 * Copies all the fields of the passed object
	 * 
	 * @param other The object to copy
	 */
	public Helicopter(Helicopter other) {
		super(other);
		this.nbCylinders = other.nbCylinders;
		this.creationYear = other.creationYear;
		this.passengerCapacity = other.passengerCapacity;
	}

	/**
	 * Compares 2 Helicopters if they have equal attributes
	 * 
	 * @param o The other object to compare
	 * @return Return true if the object are identical, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		else {
			Helicopter temp = (Helicopter) o;
			return brand.equals(temp.brand) && price == temp.price && horsePower == temp.horsePower
					&& nbCylinders == temp.nbCylinders && creationYear == temp.creationYear
					&& passengerCapacity == temp.passengerCapacity;
		}
	}

	/**
	 * @return Return a string
	 */
	@Override
	public String toString() {
		return String.format(
				"Helicopter: (Brand: %s, price: %.2f, Horse power: %d, Nb of cylinder: %d, Year: %d, Capacity: )",
				brand, price, horsePower, nbCylinders, creationYear, passengerCapacity);
	}

	// Getters and Setters
	public int getNbCylinders() {
		return nbCylinders;
	}

	public void setNbCylinders(int nbCylinders) {
		this.nbCylinders = nbCylinders;
	}

	public int getCreationYear() {
		return creationYear;
	}

	public void setCreationYear(int creationYear) {
		this.creationYear = creationYear;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
}
