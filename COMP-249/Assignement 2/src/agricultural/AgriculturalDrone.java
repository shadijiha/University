/**
 * @author shadi
 */
package agricultural;

import airplane.Flyable;
import uavs.UAV;


public class AgriculturalDrone extends UAV {

	private String brand;
	private int carryCapacity;

	/**
	 *
	 */
	public AgriculturalDrone(double weight, double price, String brand, int carryCapacity) {
		super(weight, price);
		this.brand = brand;
		this.carryCapacity = carryCapacity;
	}

	public AgriculturalDrone() {
		super();
		this.brand = "";
		this.carryCapacity = 0;
	}

	public AgriculturalDrone(final AgriculturalDrone other) {
		super(other);
		this.brand = other.brand;
		this.carryCapacity = other.carryCapacity;
	}

	@Override
	public String toString() {
		return String.format("This Agricultural Drone is manufactured by %s. %s. It can carry up to %d Kg.", brand,
				super.toString(), carryCapacity);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		} else {
			AgriculturalDrone u = (AgriculturalDrone) o;
			return u.weight == weight && u.price == price && u.brand.equals(brand) && u.carryCapacity == carryCapacity;
		}
	}

	/**
	 * @return Returns a copy of the calling object
	 */
	public Flyable copy() {
		return new AgriculturalDrone(this);
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the carryCapacity
	 */
	public int getCarryCapacity() {
		return carryCapacity;
	}

	/**
	 * @param carryCapacity the carryCapacity to set
	 */
	public void setCarryCapacity(int carryCapacity) {
		this.carryCapacity = carryCapacity;
	}

}
