/**
 * Every flying object implements Flyable
 */

package airplane;

public interface Flyable {

	/**
	 * This function copies the calling object
	 *
	 * @return Returns a copy of the calling object
	 */
	public Flyable copy();
}
