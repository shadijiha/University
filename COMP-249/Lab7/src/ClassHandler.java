import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author shadi
 *
 */
public class ClassHandler {

	private Lab lab;
	private String path;

	/**
	 * 
	 */
	public ClassHandler(Lab lab) {
		// TODO Auto-generated constructor stub
		this.lab = lab;
	}

	public ClassHandler(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}

	public void writeToFile(String name) {

		path = name;

		try {
			PrintWriter writer = new PrintWriter(new File(name));

			writer.println(lab.getSection());
			writer.println(lab.getCapacity());
			writer.println(lab.isFull());

			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Lab readFromFile() {

		try {
			Scanner scan = new Scanner(new File(path));

			Lab temp = new Lab(null, 0, false);

			while (scan.hasNextLine()) {
				temp.setSection(scan.nextLine());
				temp.setCapacity(Integer.parseInt(scan.nextLine()));
				temp.setFull(Boolean.parseBoolean(scan.nextLine()));
			}

			scan.close();

			return temp;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
