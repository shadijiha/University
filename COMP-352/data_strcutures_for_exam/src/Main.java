import my.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {

		// The following class represents the nodes in a binary tree ...
		// 1. This method will return the value of the left most node of the tree (smallest value)
		// 2.
		BinaryTree<Integer> tree = new BinaryTree<>(new Integer[]{1, 2, 3, 4, 5, 6});

		System.out.println(search(tree, tree.root(), 9));
		System.out.println(new TreePrinter<>(tree).toString());


	}

	public static boolean search(BinaryTree<Integer> tree, BinaryTree.TreeNode<Integer> node, int x) {

		if (node == null)
			return false;

		if (node.data.equals(x))
			return true;

		boolean result1 = search(tree, node.left, x);
		boolean result2 = search(tree, node.right, x);
		return result1 || result2;
	}
}
