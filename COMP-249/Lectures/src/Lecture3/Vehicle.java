package Lecture3;

public class Vehicle {
	private int numberOfDoors;
	private double price;

	public Vehicle() {
		numberOfDoors = 0;
		price = 0.0;
	}

	public Vehicle(int nd, double pr) {
		numberOfDoors = nd;
		price = pr;
	}

	// Copy constructor
	public Vehicle(Vehicle vec) {
		numberOfDoors = vec.numberOfDoors;
		price = vec.price;
	}

	public int getNumOfDoors() {
		return numberOfDoors;
	}

	public void setNumOfDoors(int nd) {
		this.numberOfDoors = nd;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double pr) {
		price = pr;
	}

	public String toString() {
		return String.format("Vehicle has %d and costs $%.2f", numberOfDoors, price);
	}//Override the equal method from Object class

	public boolean equals(Object x) {
		if (x == null) {
			return false;
		} else if (x.getClass() != this.getClass()) {
			return false;
		} else {
			Vehicle temp = (Vehicle) x;
			return this.numberOfDoors == temp.numberOfDoors && this.price == temp.price;
		}
	}
}
