/**
 *
 */
package my;

public class TreePrinter<T extends Comparable<T>> {

	private Tree<T> tree;
	int COUNT;

	public TreePrinter(Tree<T> tree) {
		this.tree = tree;
		COUNT = tree.size();
	}

	// Function to print binary tree in 2D
	// It does reverse inorder traversal
	private StringBuilder toString2DUtil(Tree.TreeNode<T> root, int space, StringBuilder builder) {
		// Base case
		if (root == null)
			return builder;

		// Increase distance between levels
		space += COUNT;

		// Process right child first
		toString2DUtil(root.right, space, builder);

		// Print current node after space
		// count
		builder.append("\n");
		for (int i = COUNT; i < space; i++)
			builder.append(" ");
		builder.append(root.data + "\n");

		// Process left child
		toString2DUtil(root.left, space, builder);

		return builder;
	}

	// Wrapper over print2DUtil()
	public void print() {
		// Pass initial space count as 0
		System.out.println(toString());
	}

	public String toString() {
		return toString2DUtil(tree.root(), 0, new StringBuilder()).toString();
	}
}
