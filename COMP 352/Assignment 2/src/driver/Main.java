package driver;

import calculator.Calculator;

public class Main {

	public static void main(String[] args) {
		// write your code here


		// Calculator
		var result = new Calculator().calculate("3 * ( 2 + 1 ) + 2 ^ 3");
		System.out.println(result);
	}
}
