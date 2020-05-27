package calculator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Calculator_old {

	private Stack<String> op_stack;
	private Stack<Variant<Double, Boolean>> value_stack;
	private String expression;

	public Calculator_old(String expression) {
		op_stack = new Stack<>();
		value_stack = new Stack<>();
		this.expression = expression;
	}

	public Object eval() {
		return evalExp();
	}

	private void doOp() {

		Variant y = value_stack.pop();
		Variant x = value_stack.pop();

		Double result_double = null;
		Boolean result_bool = null;

		String op = op_stack.pop();

		switch (op) {
			case "+":
				result_double = (double) x.get() + (double) y.get();
				break;
			case "-":
				result_double = (double) x.get() - (double) y.get();
				break;
			case "*":
				result_double = (double) x.get() * (double) y.get();
				break;
			case "/":
				result_double = (double) x.get() / (double) y.get();
				break;
			case ">":
				result_bool = (double) x.get() > (double) y.get();
				break;
			case "<":
				result_bool = (double) x.get() < (double) y.get();
				break;
			case ">=":
				result_bool = (double) x.get() >= (double) y.get();
				break;
			case "<=":
				result_bool = (double) x.get() <= (double) y.get();
				break;
			case "==":
				result_bool = (double) x.get() == (double) y.get();
				break;
			case "!=":
				result_bool = (double) x.get() != (double) y.get();
				break;
			case "^":
				result_double = power((double) x.get(), (int) (double) y.get());
				break;
		}

		Variant<Double, Boolean> temp = new Variant<>(result_double, result_bool);

		value_stack.push(temp);
	}

	private void repeatOps(String refOp) {
		while (value_stack.size() > 1 && prec(refOp) <= prec(op_stack.firstElement())) {
			doOp();
		}
	}

	private Object evalExp() {

		String[] input = this.expression.split(" ");

		for (String s : input) {

			if (isNumeric(s)) {
				double x = Double.parseDouble(s);
				value_stack.push(new Variant<>(x, null));
			} else {
				repeatOps(s);
				op_stack.push(s);
			}

		}

		repeatOps("$");

		return value_stack.firstElement().get();
	}

	private int prec(String op) {

		switch (op) {
			case "$":
				return 0;
			case "<":
			case "<=":
			case ">":
			case ">=":
				return 1;
			case "+":
			case "-":
				return 2;
			case "*":
			case "/":
				return 3;
			case "^":
			case "!":
				return 4;
			case "(":
			case ")":
				return 5;
			default:
				return -1;
		}
	}

	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static int factorial(int n) {
		return n <= 1 ? 1 : n * factorial(n - 1);
	}

	public static double power(double x, int y) {
		double sum = 1.0D;
		for (int i = 0; i < y; i++) {
			sum *= x;
		}

		return sum;
	}


	public static String loadFromFile(String path) {

		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream(path));

			StringBuilder data = new StringBuilder();
			while (scan.hasNextLine()) {
				data.append(scan.nextLine());
			}

			return data.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Failed to open file " + path);
			return null;
		} finally {
			scan.close();
		}
	}
}
