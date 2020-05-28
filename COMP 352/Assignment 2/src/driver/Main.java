package driver;

public class Main {

	public static void main(String[] args) {
		// write your code here


		// Calculator
		//var result = new Calculator().calculate("3 * ( 2 + 1 )");
		//System.out.println(result);

		SharedArrayStack stack1 = new SharedArrayStack();
		SharedArrayStack stack2 = new SharedArrayStack();
		SharedArrayStack stack3 = new SharedArrayStack();

		stack1.push(5);
		stack1.push(6);

		stack2.push(9);


		stack3.push(90);


		SharedArrayStack.printArray();
	}
}
