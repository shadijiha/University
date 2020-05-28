package driver;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// write your code here


		// Calculator
		//var result = new Calculator().calculate("3 * ( 2 + 1 )");
		//System.out.println(result);

		var t = remove_duplicates(22, 61, -10, 61, 10, 9, 9, 21, 35, 22, -10, 19, 5, 77, 5, 92, 85, 21, 35, 12, 9, 61);

		System.out.println(Arrays.toString(t));
	}

	public static int[] remove_duplicates(int... input) {

		ShadoStack<Integer> stack = new ShadoStack<>();

		for (int e : input)
			add_to_stack(stack, e);

		// Turn the stack back to array
		int[] result = new int[stack.size()];

		for (int i = stack.size() - 1; i >= 0; i--)
			result[i] = stack.pop();

		return result;
	}

	public static <T extends Number & Comparable> void add_to_stack(ShadoStack<T> stack, T e) {

		for (T temp : stack)
			if (temp.equals(e))
				return;

		stack.push(e);
	}
}
