/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

public abstract class Structure {
	protected int yearOfCreation;
	protected double cost;	
	
	public Structure(int yearOfCreation, double cost) {
		this.yearOfCreation = yearOfCreation;
		this.cost = cost;
	}	
	
	@Override
	public abstract Structure clone();

	@Override
	public String toString() {
		return "Structure [yearOfCreation: " + yearOfCreation + ", cost: " + cost + "$ ]";
	}

	/**
	 * @return Returns the cost of the calling object
	 */
	public int getYearOfCreation() {
		return yearOfCreation;
	}
	
	/**
	 * Changes the year Of Creation of the calling Object
	 * @param cost The new Cost
	 */
	public void setYearOfCreation(int yearOfCreation) {
		this.yearOfCreation = yearOfCreation;
	}
	
	/**
	 * @return Returns the cost of the calling object
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Changes the cost of the calling Object
	 * @param cost The new Cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}	
}
