/**
 * 
 * @author shadi
 *
 */

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Lab l = new Lab("WW", 60, true);

		ClassHandler c = new ClassHandler(l);

		c.writeToFile("xd.txt");

		Lab r = c.readFromFile();

		System.out.println(r.toString());

	}

}
