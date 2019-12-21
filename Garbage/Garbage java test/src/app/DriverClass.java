/***
 * 
 * Driver class ^_^
 */


package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String test = "this is a test";
		
		List<Character> arr = toCharList(test); 
		
		arr.stream()
			.forEach(e -> System.out.print(e + " "));
		
	}
	
	private static List<Character> toCharList(String str)	{
		List<Character> result = new ArrayList<Character>();
		
		for (int i = 0; i < str.length(); i++)	{
			result.add(str.charAt(i));
		}
		
		return result;		
	}

}
