/**
 *
 */
package uavs;

import airplane.Flyable;

/**
 * @author shadi
 *
 */
public class UAV implements Flyable {

	protected double weight;
	protected double price;

	/**
	 *
	 */
	public UAV(double weight, double price) {
		// TODO Auto-generated constructor stub
		this.weight = weight;
		this.price = price;
	}

	public UAV() {
		this.weight = 0.0;
		this.price = 0.0;
	}

	public UAV(final UAV other) {
		this.weight = other.weight;
		this.price = other.price;
	}

	@Override
	public String toString() {
		return String.format("This UAV weights %.2f pounds and costs %.2f$", weight, price);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		} else {
			UAV u = (UAV) o;
			return u.weight == weight && u.price == price;
		}
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * This function copies the calling object
	 *
	 * @return Returns a copy of the calling object
	 */
	@Override
	public Flyable copy() {
		return new UAV(this);
	}
}
