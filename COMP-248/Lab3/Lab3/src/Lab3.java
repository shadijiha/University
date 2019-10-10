
import java.util.Scanner;

public class Lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		int n1, n2, n3, sum = 0;
		String magicNumber = "";
		String input;
		String[] numbers;
		
		System.out.println("Enter 3 2-digit numbers:");
		input = scan.nextLine();
		numbers = input.split(" ");
		
		n1 = Integer.parseInt(numbers[0]);
		n2 = Integer.parseInt(numbers[1]);
		n3 = Integer.parseInt(numbers[2]);
		
		sum = n1 + n2 + n3;
		
		if (sum % 3 == 0 && sum % 5 != 0)	{			
			
			magicNumber = numbers[1].split("")[0];
			magicNumber += Integer.toString(n1 + n3);
			
		} else if (sum % 3 != 0 && sum % 5 == 0)	{
			
			magicNumber = Integer.toString(n1 + n3);
			magicNumber += numbers[1].split("")[numbers[1].split("").length - 1];
			
		} else if (sum % 3 == 0 && sum % 5 == 0)	{
			
			magicNumber = numbers[1] + numbers[0] + "1";
			
		} else	{
			
			magicNumber = numbers[0] + numbers[1] + numbers[2];
			
		}
		
		System.out.printf("\nYour magic number is %s", magicNumber);	

	}

}
