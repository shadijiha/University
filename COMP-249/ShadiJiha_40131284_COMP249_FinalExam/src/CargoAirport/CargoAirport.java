/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package CargoAirport;

import FX_W20PKG.Airport;

public class CargoAirport extends Airport {
	
	private double landedWeight;	// The weight of landed goods on the airport

	/**
	 * @param yearOfCreation
	 * @param cost
	 * @param numOfRunways
	 * @param code
	 * @param landedWeight
	 */
	public CargoAirport(int yearOfCreation, double cost, int numOfRunways, int code, double landedWeight) {
		super(yearOfCreation, cost, numOfRunways, code);
		this.landedWeight = landedWeight;
	}
	
	@Override
	public CargoAirport clone() {
		return new CargoAirport(yearOfCreation, cost, numOfRunways, code, landedWeight);
	}
	
	@Override
	public String toString() {
		return "CargoAirport [landedWeight: " + landedWeight + ", numOfRunways: " + numOfRunways + ", code: " + code
				+ ", yearOfCreation: " + yearOfCreation + ", cost: " + cost + "$ ]";
	}

	public boolean isCargo()	{
		return landedWeight >= 100_000_000; 
	}

	/**
	 * @return the landedWeight
	 */
	public double getLandedWeight() {
		return landedWeight;
	}

	/**
	 * @param landedWeight the landedWeight to set
	 */
	public void setLandedWeight(double landedWeight) {
		this.landedWeight = landedWeight;
	}
}
