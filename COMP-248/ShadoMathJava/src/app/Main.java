// #include "ShadoMath.java"

/***
 * 
 * Driver class
 * */

package app;

import ShadoMath.Complex;
import ShadoMath.Fraction;
import ShadoMath.Matrix;
import ShadoMath.Vector;

public class Main {

	public static void main(String[] args) {

		Matrix test = new Matrix(2, 2);
		Matrix test2 = new Matrix(2, 2);

		test.print();
		print("");
		test.inverse().print();
		print("\n" + test.determinant2D());

		System.out.print("\n\n\n\n");

	}

	public static <T> void print(T element) {
		System.out.println(element);
	}

}
