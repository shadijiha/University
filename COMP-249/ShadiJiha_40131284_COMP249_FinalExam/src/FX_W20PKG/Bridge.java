/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

public abstract class Bridge extends Building {
	
	protected double length;
	protected double height;
	
	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param landSpace
	 * @param material
	 * @param length
	 * @param height
	 */
	public Bridge(int yearOfCreation, double cost, double landSpace, String material, double length, double height) {
		super(yearOfCreation, cost, landSpace, material);
		this.length = length;
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "Bridge [length: " + length + "m, height: " + height + "m, landSpace: " + landSpace + "m, material: "
				+ material + ", yearOfCreation: " + yearOfCreation + ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		this.length = length;
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
