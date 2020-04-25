/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

public abstract class Airport extends Structure {
	
	protected int numOfRunways;
	protected int code; // International airport code
	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param numOfRunways
	 * @param code
	 */
	public Airport(int yearOfCreation, double cost, int numOfRunways, int code) {
		super(yearOfCreation, cost);
		this.numOfRunways = numOfRunways;
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Airport [numOfRunways: " + numOfRunways + ", code: " + code + ", yearOfCreation: " + yearOfCreation
				+ ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the numOfRunways
	 */
	public int getNumOfRunways() {
		return numOfRunways;
	}
	/**
	 * @param numOfRunways the numOfRunways to set
	 */
	public void setNumOfRunways(int numOfRunways) {
		this.numOfRunways = numOfRunways;
	}
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
