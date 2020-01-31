/***
 * Driver class
 */

public class Main {
	/***
	 *
	 * @param args CMD arguments
	 */
	public static void main(String[] args) {
		// JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLayout(new FlowLayout());
		//
		// JButton b = new JButton("Log In");
		// formatJButton(b);
		//
		// frame.add(b);
		// frame.pack();
		// frame.setVisible(true);

		Person[] array = new Person[2];
		array[0] = new Student("Jean", "Leblanc", "Electrical engineering");
		array[1] = new Employee("Albert", "Belval", "Part-time");

		for (Person temp : array)
			System.out.println(temp.toString());

	}
}