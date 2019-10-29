
import java.util.Scanner;

public class Lab5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter a string: ");
		String userInput = scan.next();		
		System.out.print("\n");
		
		
		for (int i = userInput.length(); i >= 0; i--)	{
			for (int j = i - 1; j >= 0; j--)	{
				System.out.print(userInput.charAt(userInput.length() - j - 1) + "	");
			}
			if (i > 0)	{
				System.out.print("\n");
			}			
		}
		
		for (int i = 2; i < userInput.length() + 1; i++)	{
			for (int j = i - 1; j >= 0; j--)	{
				System.out.print(userInput.charAt(userInput.length() - j - 1) + "	");
			}
			System.out.print("\n");
		}
		
		
		

	}

}
