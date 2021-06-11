import my.*;

import java.util.stream.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {
		int[] A = {-19, 84, 46, -13, 39, 65};
		int[] B = new int[A.length];

		Tree<Integer> tree = new BinaryTree<>(new Integer[]{
				1, 2, 3, 4, 5, 6
		});

		Stream.of(1, 2, 3, 4).reduce(0, () ->);

	}
}
