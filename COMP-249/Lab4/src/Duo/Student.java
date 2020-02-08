/**
 * 
 */
package Duo;

import Uno.Person;

/**
 * @author shadi
 *
 */
public class Student extends Person {

	private int studyHours; // Number of study hours
	private int caloricIntake;

	/**
	 * 
	 */
	public Student(int nbOfSeblings, int studyHours) {
		// TODO Auto-generated constructor stub
		super(nbOfSeblings);
		this.studyHours = studyHours;
		this.caloricIntake = this.caloricIntake + 500;
	}

	@Override
	public String toString() {
		return super.toString() + ", Nb of study hours: " + studyHours;
	}

	@Override
	public void favSongs() {
		// TODO Auto-generated method stub
		return;
	}

}
