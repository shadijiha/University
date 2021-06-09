import java.util.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {
		int[] A = {-19, 84, 46, -13, 39, 65};
		int[] B = new int[A.length];

		towSidesEquity(A, B);
		System.out.println(Arrays.toString(B));
	}

	static void towSidesEquity(int[] A, int[] B) {
		int n = A.length;

		for (int i = 0; i < n; i++) {
			int numLeft = 0;
			for (int j = 0; j < i; j++) {
				if (A[j] > 0)
					numLeft++;
			}

			int numRight = 0;
			for (int j = i + 1; j < n; j++) {
				if (A[j] > 0)
					numRight++;
			}

			B[i] = numLeft - numRight;
		}
	}
}
