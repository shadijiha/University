/**
 * Name(s) and ID(s): Shadi Jiha #40131284
 * COMP249
 * Assignment # 1
 * Due Date: January 31th 2020
 */

package com.main;

public class Appliance {
	private String type;
	private String brand;
	private long serialNumber;
	private double price;

	// Static variables
	private static int applianceCreated = 0;
	private static long currentSerial = 1000000;
	public static final String appliances[] = {"Fridge", "Air Conditioner", "Washer", "Dryer", "Freezer", "Stove", "Dishwasher", "Water Heaters", "Microwave"};

	/***
	 * Specific constructor
	 * @param _type The type of the machine. MUST BE 1 of the allowed types
	 * @param _brand The brand of the machine.
	 * @param _price The price of the machine. Minimum 1.00$
	 */
	Appliance(String _type, String _brand, double _price) {
		this.brand = _brand;
		this.price = Math.max(_price, 1.0);
		this.serialNumber = currentSerial;
		this.type = _type;
		currentSerial++;
		applianceCreated++;
	}

	/***
	 * Default constructor
	 * Inits: TYPE to appliances[0], BRAND to "LG" and PRICE TO "1.0"
	 */
	Appliance() {
		this(appliances[0], "LG", 1.0);
	}

	/***
	 * Copy constructor
	 * @param other The constructor you want to copy
	 */
	Appliance(final Appliance other) {
		this(other.type, other.brand, other.price);
		serialNumber = other.serialNumber;
		/*type = other.type;
		brand = other.brand;
		price = other.price;*/
	}

	// Static functions

	/***
	 * This function checks if the passed machine is 1 of the allowed onces
	 * from the appliances[] array
	 * @param s The type you want to check
	 * @return Returns true if the machine passed is inside the array, false otherwise
	 */
	public static boolean isValide(String s) {
		for (var appliance : appliances) {
			if (appliance.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	public static int findNumberOfCreatedAppliances() {
		return applianceCreated;
	}

	// Essential methods

	/***
	 * This function checks if calling objects is equal to the passed object
	 * @param other The object you want to compare with the calling Object
	 * @return Returns if the calling object has the same brand, type and price as the passed object
	 */
	@Override
	public boolean equals(final Object other) {
		if (other == null) {
			return false;
		} else if (this.getClass() != other.getClass()) {
			return false;
		} else {
			Appliance otherApp = (Appliance) other;
			return (brand.equals(otherApp.brand) && type.equals(otherApp.type) && price == otherApp.price);
		}
	}

	@Override
	public String toString() {
		return String.format("\tAppliance Serial # %d,\tBrand: %s,\tType: %s,\tPrice: $%1.2f", serialNumber, brand, type, price);
	}

	// Setters
	public void setType(String type) {
		this.type = type;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Getters
	public String getType() {
		return type;
	}

	public String getBrand() {
		return brand;
	}

	public long getSerialNumber() {
		return serialNumber;
	}

	public double getPrice() {
		return price;
	}
}
