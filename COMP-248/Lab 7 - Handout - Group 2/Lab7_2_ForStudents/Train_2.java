/*	COPYRIGHT OF CONCORDIA UNIVERSITY
DEPARTMENT OF ENGINEERING AND COMPUTER SCIENCE

	COMP248
	Prof: Nancy Acemian
	Topic: Simple Class	 
 */
import java.util.Scanner;
public class Train_2
{
	// ------------------------
	// CODE TO COMPLETE
	// -------------------------
	// Declare instance variables
	
	
	// Implement default constructor
	
	// Implement 2nd constructor
	
	
	// Implement Get/Set methods
	
	
	// Implement toString
	
	
	// Implement equals methods
	
	
	//Implement bothElectric methods
	

	/* ================================================================================================== */
	/* =====*****-----+++++!!!!! DO NOT ALTER, CHANGE, OR MODIFY THE CODE BELOW !!!!!+++++-----*****===== */
	/* ================================================================================================== */

	public static void main(String[] args) {

		Scanner keyIn = new Scanner (System.in);
		String line;
		int wagons;
		boolean isElectric;
		System.out.print("On which line is this train? ");
		line = keyIn.nextLine();
		System.out.print("How many wagons does it have? ");
		wagons = keyIn.nextInt();
		System.out.print("Is it an electric train? (true or false) ");
		isElectric = keyIn.nextBoolean();
		Train_2 t1 = new Train_2();
		Train_2 t2 = new Train_2(line,wagons,isElectric);

		System.out.println("The two trains are:");
		System.out.print(t1 + "\n" + t2);

		//Let's set up the 2nd train

		System.out.print("\n\nLet's set up the 1st train ...\n\tWhat line is it? ");
		line = keyIn.nextLine();
		line = keyIn.nextLine();

		System.out.print("\tHow many wagons? ");
		wagons = keyIn.nextInt();
		System.out.print("\tIs it an electric train (true or false)? ");
		isElectric = keyIn.nextBoolean();
		t1.setLine(line);
		t1.setWagons(wagons);
		t1.setIsElectric(isElectric);
		System.out.println("Train 1: " + t1);

		System.out.println("Are the 2 Train objects equal?" 
				+ (t1.equals(t2)?" Yes":" No"));
		System.out.println("Are they both electric trains? " + t1.bothElectric(t2));
		t1.setIsElectric((!t1.getIsElectric()));
		
		System.out.print("Now are they both electric trains? " + t1.bothElectric(t2));
		keyIn.close();
	}
}

