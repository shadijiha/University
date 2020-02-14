/**
 *
 */
package agricultural;

import airplane.Flyable;
import uavs.UAV;

/**
 * @author shadi
 *
 */
public class MAV extends UAV {

	private double size;
	private String model;

	/**
	 *
	 */
	public MAV(double weight, double price, String model, double size) {
		super(weight, price);
		this.model = model;
		this.size = size;
	}

	public MAV() {
		super();
		this.model = "";
		this.size = 0.0;
	}

	public MAV(final MAV other) {
		super(other);
		model = other.model;
		size = other.size;
	}

	@Override
	public String toString() {
		return String.format("This MAV is of model %s. %s. It has a size of %.2f cm", model, super.toString(), size);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		} else {
			MAV u = (MAV) o;
			return u.weight == weight && u.price == price && model.equals(u.model) && size == u.size;
		}
	}

	/**
	 * @return Returns a copy of the calling object
	 */
	public Flyable copy() {
		return new MAV(this);
	}

	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

}
