import Duo.Employee;
import Duo.Student;
import Uno.Person;

/**
 * 
 */

/**
 * @author shadi
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Person[] array = new Person[4];

		for (int i = 0; i < array.length; i += 2) {
			array[i] = new Student(i, (i + 1) * 10);
			array[i + 1] = new Employee(i + 1);
		}

		for (Person temp : array)
			System.out.println(temp.toString());

	}

}
