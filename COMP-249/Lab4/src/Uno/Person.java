/**
 * 
 */
package Uno;

/**
 * @author shadi
 *
 */
public abstract class Person {

	protected int numberOfSeblings;
	protected long medicareID;
	protected int calorieIntake;

	/**
	 * 
	 */
	public Person(int nbSeblings) {
		// TODO Auto-generated constructor stub
		numberOfSeblings = nbSeblings;
		medicareID = (long) (Math.random() * Long.MAX_VALUE);
		calorieIntake = 2000;
	}

	@Override
	public String toString() {
		return String.format("Medicare#%d, Nb of seblings: %d, Calorie intake/day: %d", medicareID, numberOfSeblings,
				calorieIntake);
	}

	public abstract void favSongs();

}
