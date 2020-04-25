//  -----------------------------------------------------
// Assignment #4
// Question: 2
// Written by: Shadi Jiha #40131284
//  -----------------------------------------------------

package part2;

import java.util.*;

public class CellPhone implements Cloneable {

	private long serialNum;
	private String brand;
	private int year;
	private double price;

	public CellPhone(long serialNum, String brand, double price, int year) {
		this.brand = brand;
		this.year = year;
		this.price = price;
		this.serialNum = serialNum;
	}

	public CellPhone(String brand, double price, int year) {
		this.brand = brand;
		this.year = year;
		this.price = price;
		this.serialNum = (long) (Math.random() * Long.MAX_VALUE);
	}

	public CellPhone(final CellPhone other, long value) {
		this(value, other.brand, other.price, other.year);
	}

	public CellPhone(final CellPhone other) {
		this(other.brand, other.price, other.year);
	}

	/**
	 * Creates and returns a copy of this object.  The precise meaning
	 * of "copy" may depend on the class of the object.
	 *
	 * @return a clone of this instance.
	 * @see Cloneable
	 */
	@Override
	public Object clone() {
		try {
			CellPhone c = (CellPhone) super.clone();
			c.brand = this.brand;
			c.year = this.year;
			c.price = this.price;

			// Get serial number from the user
			Scanner scanner = new Scanner(System.in);

			System.out.print("Please Enter the serial number of the cloned object > ");
			c.serialNum = scanner.nextLong();

			scanner.close();

			return c;
		} catch (CloneNotSupportedException ignored) {
			return null;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		} else {
			CellPhone c = (CellPhone) o;
			return c.brand.equals(brand) && c.year == year && price == c.price;
		}
	}

	@Override
	public String toString() {
		return String.format("[%d: %s %.2f$ %d]", serialNum, brand, price, year);
	}

	public long getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
