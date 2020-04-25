/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */
package FX_W20PKG;

public abstract class ResidentialBuilding extends Building {

	protected int maxNumberOfHabitants;

	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param landSpace
	 * @param material
	 * @param maxNumberOfHabitants
	 */
	public ResidentialBuilding(int yearOfCreation, double cost, double landSpace, String material,
			int maxNumberOfHabitants) {
		super(yearOfCreation, cost, landSpace, material);
		this.maxNumberOfHabitants = maxNumberOfHabitants;
	}
	
	@Override
	public String toString() {
		return "ResidentialBuilding [maxNumberOfHabitants: " + maxNumberOfHabitants + ", landSpace: " + landSpace
				+ "m, material: " + material + ", yearOfCreation: " + yearOfCreation + ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the maxNumberOfHabitants
	 */
	public int getMaxNumberOfHabitants() {
		return maxNumberOfHabitants;
	}

	/**
	 * @param maxNumberOfHabitants the maxNumberOfHabitants to set
	 */
	public void setMaxNumberOfHabitants(int maxNumberOfHabitants) {
		this.maxNumberOfHabitants = maxNumberOfHabitants;
	}

}
