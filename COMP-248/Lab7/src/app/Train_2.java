/*	COPYRIGHT OF CONCORDIA UNIVERSITY
DEPARTMENT OF ENGINEERING AND COMPUTER SCIENCE

	COMP248
	Prof: Nancy Acemian
	Topic: Simple Class	 
 */

package app;
import java.util.Scanner;
public class Train_2
{
	// ------------------------
	// CODE TO COMPLETE
	// -------------------------
	// Declare instance variables
	private String line;
	private int wagons;
	private boolean isElectric;
	
	// Implement default constructor
	public Train_2()	{
		this.line = null;
		this.wagons = 0;
		this.isElectric = false;
	}
	
	// Implement 2nd constructor
	public Train_2(String _line, int _wagons, boolean _isElectric)	{
		this.line = _line;
		this.wagons = _wagons;
		this.isElectric = _isElectric;
	}	
	
	// Implement Get/Set methods
	public String getLine()			{	return this.line;}
	public int getWagons()			{	return this.wagons;}
	public boolean getIsElectric()	{	return this.isElectric;}
	
	public void setLine(String _line)				{	this.line = _line;}
	public void setWagons(int _wagons)				{	this.wagons = _wagons;}
	public void setIsElectric(boolean _isElectric)	{	this.isElectric = _isElectric;}
	
	// Implement toString
	public String toString()	{
		return String.format("The train on line %s has %d wagons and is electric is %b", this.line, this.wagons, this.isElectric);		
	}
	
	// Implement equals methods
	public boolean equals(Train_2 other)	{
		return (this.line.equals(other.getLine()) && this.wagons == other.getWagons() && this.isElectric == other.getIsElectric());
	}
	
	//Implement bothElectric methods
	public boolean bothElectric(Train_2 other)	{
		return this.isElectric && other.getIsElectric();
	}

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

