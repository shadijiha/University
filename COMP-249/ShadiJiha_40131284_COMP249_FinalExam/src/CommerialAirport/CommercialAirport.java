/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package CommerialAirport;

import FX_W20PKG.Airport;

public class CommercialAirport extends Airport  {
	
	private int numOfGates;	// Number of gates in the airport

	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param numOfRunways
	 * @param code
	 * @param numOfGates
	 */
	public CommercialAirport(int yearOfCreation, double cost, int numOfRunways, int code, int numOfGates) {
		super(yearOfCreation, cost, numOfRunways, code);
		this.numOfGates = numOfGates;
	}
	
	@Override
	public CommercialAirport clone() {
		return new CommercialAirport(yearOfCreation, cost, numOfRunways, code, numOfGates);
	}	
	
	@Override
	public String toString() {
		return "CommercialAirport [numOfGates: " + numOfGates + ", numOfRunways: " + numOfRunways + ", code: " + code
				+ ", yearOfCreation: " + yearOfCreation + ", cost: " + cost + "$ ]";
	}

	/**
	 * @return the numOfGates
	 */
	public int getNumOfGates() {
		return numOfGates;
	}

	/**
	 * @param numOfGates the numOfGates to set
	 */
	public void setNumOfGates(int numOfGates) {
		this.numOfGates = numOfGates;
	}
}
