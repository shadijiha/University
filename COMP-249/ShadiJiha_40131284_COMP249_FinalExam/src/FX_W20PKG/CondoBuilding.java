/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */
package FX_W20PKG;

public class CondoBuilding extends ResidentialBuilding {

	private int numOfUnits;

	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param landSpace
	 * @param material
	 * @param maxNumberOfHabitants
	 * @param numOfUnits
	 */
	public CondoBuilding(int yearOfCreation, double cost, double landSpace, String material, int maxNumberOfHabitants,
			int numOfUnits) {
		super(yearOfCreation, cost, landSpace, material, maxNumberOfHabitants);
		this.numOfUnits = numOfUnits;
	}
	
	@Override
	public CondoBuilding clone() {
		return new CondoBuilding(yearOfCreation, cost, landSpace, material, maxNumberOfHabitants,
				numOfUnits);
	}	
	
	@Override
	public String toString() {
		return "CondoBuilding [numOfUnits: " + numOfUnits + ", maxNumberOfHabitants: " + maxNumberOfHabitants
				+ ", landSpace: " + landSpace + "m, material: " + material + ", yearOfCreation: " + yearOfCreation
				+ ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the numOfUnits
	 */
	public int getNumOfUnits() {
		return numOfUnits;
	}

	/**
	 * @param numOfUnits the numOfUnits to set
	 */
	public void setNumOfUnits(int numOfUnits) {
		this.numOfUnits = numOfUnits;
	}	
}
