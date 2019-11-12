
import java.util.Scanner;

public class Lab6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		String rawDate;
		
		System.out.print("Enter array size (minimum size 5)? ");		
		int n = scan.nextInt();
		int[] resultArray = new int[n];
		
		
		System.out.printf("Enter %d distinct integer values to store in the array: ", n);
		
		scan.nextLine();
		rawDate = scan.nextLine();		
		System.out.println();
		
		String[] data = rawDate.split(" ");
		
		// fill array
		System.out.println("Original array:");
		for (int i = 0; i < resultArray.length; i++)	{
			resultArray[i] = Integer.parseInt(data[i]);
			System.out.print(resultArray[i] + "\t");
		}
		
		// Detect min and max
		int min = resultArray[0];
		int max = resultArray[1];
		int minIndex = 0;
		int maxIndex = 0;
		
		for (int i = 0; i < resultArray.length; i++)	{
			if (resultArray[i] < min)	{
				min = resultArray[i];
				minIndex = i;
			}
			if (resultArray[i] > max)	{
				max = resultArray[i];
				maxIndex = i;
			}
		}
		
		System.out.println();
		System.out.println();
		
		// Swap
		int temp = min;
		resultArray[minIndex] = max;
		resultArray[maxIndex] = temp;
		
		System.out.println("Array after swapping:");
		for (int element : resultArray)	{
			System.out.print(element + "\t");
		}

	}

}
