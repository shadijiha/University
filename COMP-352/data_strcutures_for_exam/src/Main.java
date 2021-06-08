import my.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {
		Tree<Integer> tree = new BinatyTree<>();

		for (int i = 0; i < 10; i++) {
			tree.add(i);
		}

		System.out.println(tree.remove(9));
		tree.inOrder((e) -> {
			System.out.print(e + " ");
		});
	}
}
