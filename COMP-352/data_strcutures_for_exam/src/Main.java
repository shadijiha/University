import my.BinaryTree;
import my.Tree;
import my.TreePrinter;

/**
 *
 */

public class Main {

	public static void main(String[] args) {

		Tree<Integer> tree = new BinaryTree<>(new Integer[]{
				1, 2, 3, 4, 5, 6
		});

		TreePrinter<Integer> printer = new TreePrinter<>(tree);
		printer.print();
	}
}
