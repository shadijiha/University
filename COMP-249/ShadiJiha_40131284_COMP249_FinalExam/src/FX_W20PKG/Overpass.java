/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

public class Overpass extends Bridge {
	
	private double maxLoad;

	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param landSpace
	 * @param material
	 * @param length
	 * @param height
	 * @param maxLoad
	 */
	public Overpass(int yearOfCreation, double cost, double landSpace, String material, double length, double height,
			double maxLoad) {
		super(yearOfCreation, cost, landSpace, material, length, height);
		this.maxLoad = maxLoad;
	}

	@Override
	public Overpass clone() {
		return new Overpass(yearOfCreation, cost, landSpace, material, length, height, maxLoad);
	}	
	
	@Override
	public String toString() {
		return "Overpass [maxLoad: " + maxLoad + "pounds, length: " + length + "m, height: " + height + "m, landSpace: "
				+ landSpace + "m, material: " + material + ", yearOfCreation: " + yearOfCreation + ", cost: " + cost
				+ "$ ]";
	}

	/**
	 * @return the maxLoad
	 */
	public double getMaxLoad() {
		return maxLoad;
	}

	/**
	 * @param maxLoad the maxLoad to set
	 */
	public void setMaxLoad(double maxLoad) {
		this.maxLoad = maxLoad;
	}
}
