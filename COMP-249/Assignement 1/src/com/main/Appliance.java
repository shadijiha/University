/**
 * Name(s) and ID(s): Shadi Jiha #40131284
 * COMP249
 * Assignment # 1
 * Due Date: January 31th 2020
 * */

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
	 * @throws Exception throws an Exception if the type if not valid
	 */
	Appliance(String _type, String _brand, double _price) throws Exception {
		this.brand = _brand;
		this.price = Math.max(_price, 1.0);
		this.serialNumber = currentSerial;
		currentSerial++;
		applianceCreated++;

		// Check if the type is allows
		if (isValide(_type))
			throw new Exception("Error! The passed Appliance type is not valide!");
		else
			this.type = _type;
	}

	/***
	 * Default constructor
	 * Inits: TYPE to appliances[0], BRAND to "LG" and PRICE TO "1.0"
	 * @throws Exception throws an Exception if the type if not valid
	 */
	Appliance() throws Exception {
		this(appliances[0], "LG", 1.0);
	}

	// Static functions
	/***
	 * This function checks if the passed machine is 1 of the allowed onces
	 * from the appliances[] array
	 * @param s The brand you want to check
	 * @return Returns true if the machine passed is inside the array, false otherwise
	 */
	private static boolean isValide(String s)	{
		for (var appliance : appliances)	{
			if (appliance.equals(s))
				return true;
		}
		return false;
	}

	// Essential methods
	/***
	 * This function checks if calling objects is equal to the passed object
	 * @param other The object you want to compare with the calling Object
	 * @return Returns if the calling object has the same brand, type and price as the passed object
	 */
	public boolean equals(final Appliance other)	{
		if (other == null)	{
			return false;
		}
		return brand.equals(other.brand) && type.equals(other.type) && price == other.price;
	}

	@Override
	public String toString()	{
		return String.format("The appliance type is: %s, brand: %s, price: %1.2f. SN: %d", type, brand, price, serialNumber);
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

	public int findNumberOfCreatedAppliances()	{
		return applianceCreated;
	}

}
