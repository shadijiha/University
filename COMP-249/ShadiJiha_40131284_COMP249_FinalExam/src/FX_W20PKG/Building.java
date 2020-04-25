/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

public abstract class Building extends Structure {
	
	protected double landSpace;
	protected String material;

	public Building(int yearOfCreation, double cost, double landSpace, String material) {
		super(yearOfCreation, cost);
		this.landSpace = landSpace;
		this.material = material;
	}

	@Override
	public String toString() {
		return "Building [landSpace: " + landSpace + "m, material: " + material + ", yearOfCreation: " + yearOfCreation
				+ ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the landSpace
	 */
	public double getLandSpace() {
		return landSpace;
	}

	/**
	 * @param landSpace the landSpace to set
	 */
	public void setLandSpace(double landSpace) {
		this.landSpace = landSpace;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

}
