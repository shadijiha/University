/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */
package FX_W20PKG;

public class HighRise extends Building {

	private double height;

	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param landSpace
	 * @param material
	 * @param height
	 */
	public HighRise(int yearOfCreation, double cost, double landSpace, String material, double height) {
		super(yearOfCreation, cost, landSpace, material);
		this.height = height;
	}
	
	@Override
	public HighRise clone() {
		return new HighRise(yearOfCreation, cost, landSpace, material, height);
	}	

	@Override
	public String toString() {
		return "HighRise [height: " + height + "m, landSpace: " + landSpace + "m, material: " + material
				+ ", yearOfCreation: " + yearOfCreation + ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
}
