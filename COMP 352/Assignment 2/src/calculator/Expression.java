package calculator;

public class Expression {

	public static final int UNKNOWN = -1;
	public static final int NUMBER = 0;
	public static final int LOGIC_OPERATOR = 1;
	public static final int OPERATOR = 2;
	public static final int LEFT_PARENTHESIS = 3;
	public static final int RIGHT_PARENTHESIS = 4;

	private int type;
	private double value;
	private char operator;
	private int precedence;

	public Expression() {
		type = UNKNOWN;
	}

	public Expression(final String contents) {

		// TODO: add factorial and logic operators
		switch (contents) {
			case "+":
			case "-":
				type = OPERATOR;
				operator = contents.charAt(0);
				precedence = 1;
				break;
			case "*":
			case "/":
				type = OPERATOR;
				operator = contents.charAt(0);
				precedence = 2;
				break;
			case "^":
				type = OPERATOR;
				operator = contents.charAt(0);
				precedence = 3;
			case "(":
				type = LEFT_PARENTHESIS;
				break;
			case ")":
				type = RIGHT_PARENTHESIS;
				break;
			default:
				type = NUMBER;
				try {
					value = Double.parseDouble(contents);
				} catch (Exception ex) {
					type = UNKNOWN;
				}
		}
	}

	public Expression(double x) {
		type = NUMBER;
		value = x;
	}

	public int getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

	public int getPrecedence() {
		return precedence;
	}

	public Expression evaluate(double a, double b) {
		double result = 0;
		switch (operator) {
			case '+':
				result = a + b;
				break;
			case '-':
				result = a - b;
				break;
			case '*':
				result = a * b;
				break;
			case '/':
				result = a / b;
				break;
		}
		return new Expression(result);
	}
}
