import my.BinarySearchTree;
import my.Tree;

/**
 *
 */

public class Main {

	public static void main(String[] args) {

		Tree<Integer> tree = new BinarySearchTree<Integer>();
		for (int i = 0; i < 5; i++) {
			tree.add(i);
		}

		var children = tree.children(tree.root().right);
		for (var child : children)
			System.out.println(child);
	}
}
