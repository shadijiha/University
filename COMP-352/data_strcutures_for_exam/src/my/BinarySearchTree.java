/**
 *
 */
package my;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	@Override
	public void add(T element) {
		root = insertRecursive(root, element);
	}

	private TreeNode<T> insertRecursive(TreeNode<T> root, T element) {
		/* If the tree is empty,
           return a new node */
		if (root == null) {
			root = new TreeNode<>(element);
			return root;
		}

		/* Otherwise, recur down the tree */
		if (element.compareTo(root.data) < 0)
			root.left = insertRecursive(root.left, element);
		else if (element.compareTo(root.data) > 0)
			root.right = insertRecursive(root.right, element);

		/* return the (unchanged) node pointer */
		return root;
	}
}
