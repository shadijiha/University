package driver;

import calculator.*;

public class Main {

	public static void main(String[] args) {
		// write your code here


		// Calculator
		var result = new Calculator("3 * (2 + 1)").eval();
		System.out.println(result);
	}
}
