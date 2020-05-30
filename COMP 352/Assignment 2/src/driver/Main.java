package driver;

import calculator.Calculator;

public class Main {

	public static void main(String[] args) {

		// Calculator
		var result = new Calculator().calculate("3 * ( 2 + 1 )");
		System.out.println(result);
	}
}
