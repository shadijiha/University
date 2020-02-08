/**
 * 
 */
package Duo;

import Uno.Person;

/**
 * @author shadi
 *
 */
public class Employee extends Person {

	/**
	 * 
	 */
	public Employee(int nbOfSeblings) {
		// TODO Auto-generated constructor stub
		super(nbOfSeblings);
		this.calorieIntake += 1000;
	}

	@Override
	public String toString() {
		return super.toString() + " Nb of study hours: I don't have to study. HA!";
	}

	@Override
	public void favSongs() {
		// TODO Auto-generated method stub
		return;
	}
}
