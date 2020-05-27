/**
 *
 */

package calculator;

public class Calculator {

	private ExpressionStack operatorStack;
	private ExpressionStack valueStack;
	private boolean error;

	public Calculator() {
		operatorStack = new ExpressionStack();
		valueStack = new ExpressionStack();
		error = false;
	}

	private void evaluateExpression(Expression t) {
		Expression A = null, B = null;

		if (valueStack.isEmpty()) {
			System.out.println("Error! Missing first argument");
			error = true;
			return;
		} else {
			B = valueStack.top();
			valueStack.pop();
		}

		// This will produce an error if we have something like this:
		// 3 *
		if (valueStack.isEmpty()) {
			System.out.println("Error! Missing second argument");
			error = true;
			return;
		} else {
			A = valueStack.top();
			valueStack.pop();
		}

		Expression R = t.evaluate(A.getValue(), B.getValue());
		valueStack.push(R);
	}

	public double calculate(final String input) {

		String[] parts = input.split(" ");
		Expression[] expressions = new Expression[parts.length];

		for (int i = 0; i < parts.length; i++)
			expressions[i] = new Expression(parts[i]);

		// All magic happens here
		for (int i = 0; i < expressions.length; i++) {

			Expression next = expressions[i];

			// If the expression is a number add it to the value stack
			if (next.getType() == Expression.NUMBER)
				valueStack.push(next);
			else if (next.getType() == Expression.OPERATOR) {

				// If the current expression has a higher Precedence than the one before than add it to the top (evaluate it first)
				if (operatorStack.isEmpty() || next.getPrecedence() > operatorStack.top().getPrecedence())
					operatorStack.push(next);
				else {

					// While the stack is not empty and the other ones are more important than the current one
					while (!operatorStack.isEmpty() && next.getPrecedence() <= operatorStack.top().getPrecedence()) {
						Expression toProcess = operatorStack.top();
						operatorStack.pop();
						evaluateExpression(toProcess);
					}
					// After evaluating the more important ones, add the current expression
					operatorStack.push(next);
				}
			} else if (next.getType() == Expression.LEFT_PARENTHESIS)
				operatorStack.push(next);
			else if (next.getType() == Expression.RIGHT_PARENTHESIS) {

				// While stack no empty, evaluate everything inside the parenthesis
				while (!operatorStack.isEmpty() && operatorStack.top().getType() == Expression.OPERATOR) {
					Expression toProcess = operatorStack.top();
					operatorStack.pop();
					evaluateExpression(toProcess);
				}

				if (!operatorStack.isEmpty() && operatorStack.top().getType() == Expression.LEFT_PARENTHESIS)
					operatorStack.pop();
				else {
					System.out.println("Parenthesis don't match!");
					error = true;
				}
			}
		}

		// Evaluate everything at the end
		while (!operatorStack.isEmpty() && operatorStack.top().getType() == Expression.OPERATOR) {
			Expression toProcess = operatorStack.top();
			operatorStack.pop();
			evaluateExpression(toProcess);
		}

		// Return the result if no error
		if (!error) {
			Expression result = valueStack.top();
			valueStack.pop();

			// No result found
			if (!operatorStack.isEmpty() || !valueStack.isEmpty())
				System.out.println("Error!");
			else
				return result.getValue();
		} else {
			throw new RuntimeException("Expression error! Cannot compute result");
		}

		return Double.MIN_VALUE;
	}
}
