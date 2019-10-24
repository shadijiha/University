

import java.util.Scanner;

public class Lab4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("Enter 2 words of the same length:");
		
		String word = scan.next();
		String numbers = scan.next();
		
		String newWord = "";
		
		
		for (int i = word.length() - 1; i >= 0; i--)	{
			
			newWord = newWord + word.charAt(i) + numbers.charAt(i);
			
		}
		
		System.out.printf("The new word is %s\n", newWord);
		System.out.print("What an odd word!");
		

	}

}
