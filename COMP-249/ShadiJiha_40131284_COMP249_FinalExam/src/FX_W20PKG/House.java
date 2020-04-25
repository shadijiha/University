/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

public class House extends ResidentialBuilding {

	private double numberOfRooms;
	private int numOfFloors;
	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param landSpace
	 * @param material
	 * @param maxNumberOfHabitants
	 * @param numberOfRooms
	 * @param numOfFloors
	 */
	public House(int yearOfCreation, double cost, double landSpace, String material, int maxNumberOfHabitants,
			double numberOfRooms, int numOfFloors) {
		super(yearOfCreation, cost, landSpace, material, maxNumberOfHabitants);
		this.numberOfRooms = numberOfRooms;
		this.numOfFloors = numOfFloors;
	}
	
	@Override
	public House clone() {
		return new House(yearOfCreation, cost, landSpace, material, maxNumberOfHabitants,
				numberOfRooms, numOfFloors);
	}
	
	@Override
	public String toString() {
		return "House [numberOfRooms: " + numberOfRooms + ", numOfFloors: " + numOfFloors + ", maxNumberOfHabitants: "
				+ maxNumberOfHabitants + ", landSpace: " + landSpace + "m, material: " + material + ", yearOfCreation: "
				+ yearOfCreation + ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the numberOfRooms
	 */
	public double getNumberOfRooms() {
		return numberOfRooms;
	}
	/**
	 * @param numberOfRooms the numberOfRooms to set
	 */
	public void setNumberOfRooms(double numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	/**
	 * @return the numOfFloors
	 */
	public int getNumOfFloors() {
		return numOfFloors;
	}
	/**
	 * @param numOfFloors the numOfFloors to set
	 */
	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}
}
