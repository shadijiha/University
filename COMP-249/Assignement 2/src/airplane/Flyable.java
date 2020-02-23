/**
 * Every flying object implements Flyable
 */

package airplane;

public abstract class Flyable {

	/**
	 * This function copies the calling object
	 *
	 * @return Returns a copy of the calling object
	 */
	public abstract Flyable copy();

	/**
	 * This function returns the price of the object
	 *
	 * @return Returns the price of the calling object
	 */
	public abstract double getPrice();

}
