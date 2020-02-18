// -----------------------------------------------------
// Part: I
// Written by: Shadi Jiha #40131284
// ----------------------------------------------------
package airplane;

public class Airplane extends Flyable {
	protected String brand;
	protected double price;
	protected int horsePower;

	/***
	 * Full constructor
	 *
	 * @param brand      The brand of the function
	 * @param price      The price of the airplane
	 * @param horsePower The power of the airplane
	 */
	public Airplane(String brand, double price, int horsePower) {
		this.brand = brand;
		this.price = price;
		this.horsePower = horsePower;
	}

	/**
	 * Default constructor inits: brands = "", price = 0.0 and horsePower = 0
	 */
	public Airplane() {
		this("", 0.0, 0);
	}

	/**
	 * Copies all the fields of the passed object
	 *
	 * @param other The object to copy
	 */
	public Airplane(final Airplane other) {
		this(other.brand, other.price, other.horsePower);
	}

	/**
	 * Compares 2 Airplane if they have equal brand, price and horse power
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
			Airplane temp = (Airplane) o;
			return brand.equals(temp.brand) && price == temp.price && horsePower == temp.horsePower;
		}
	}

	/**
	 * @return Return a string that represents the calling airplane
	 */
	@Override
	public String toString() {
		return String.format("This airplane is manufactured by %s, costs %.2f$ and has a power of %d HP.", brand, price,
				horsePower);
	}

	/**
	 * @return Returns a copy of the calling object
	 */
	public Flyable copy() {
		return new Airplane(this);
	}

	// Getters and Setters
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}
}
