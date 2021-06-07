/**
 *
 */
package toDelete;

import java.util.*;
import java.util.function.*;

public class BinaryTree<T> {
	private Node root;

	// Tree Node
	public class Node {
		public T data;
		public Node left, right;

		Node(T data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public Object[] append(T left, T right) {
			this.left = new Node(left);
			this.right = new Node(right);

			List<Node> list = new ArrayList<>();
			list.add(this.left);
			list.add(this.right);

			return new Object[]{this.left, this.right};
		}
	}

	// Function to insert nodes in level order
	private Node insertLevelOrder(T[] arr, Node root,
								  int i) {
		// Base case for recursion
		if (i < arr.length) {
			Node temp = new Node(arr[i]);
			root = temp;

			// insert left child
			root.left = insertLevelOrder(arr, root.left,
					2 * i + 1);

			// insert right child
			root.right = insertLevelOrder(arr, root.right,
					2 * i + 2);
		}
		return root;
	}

	public void insertLevelOrder(T[] arr) {
		root = insertLevelOrder(arr, root, 0);
	}

	public int depthOfNode(Node node, T data, int level) {
		if (node == null)
			return 0;

		if (node.data.equals(data))
			return level;

		int downlevel
				= depthOfNode(node.left, data, level + 1);
		if (downlevel != 0)
			return downlevel;

		downlevel
				= depthOfNode(node.right, data, level + 1);
		return downlevel;
	}

	/* Returns level of given data value */
	public int depthOfNode(Node node) {
		return depthOfNode(root, node.data, 0);
	}

	public int countFullNodes(Node node) {

		if (node == null || node.left == null || node.right == null)
			return 0;
		else
			return 1 + countFullNodes(node.left) + countFullNodes(node.right);
	}

	public int countFullNodes() {
		return countFullNodes(root);
	}

	// Function to print tree nodes in InOrder fashion
	private void inOrder(Node node, Consumer<T> function) {
		if (node != null) {
			inOrder(node.left, function);
			function.accept(node.data);
			inOrder(node.right, function);
		}
	}

	public void inOrder(Consumer<T> function) {
		inOrder(root, function);
	}

	private void postOrder(Node node, Consumer<T> function) {
		if (node == null)
			return;

		// first recur on left subtree
		postOrder(node.left, function);

		// then recur on right subtree
		postOrder(node.right, function);

		// now deal with the nod
		function.accept(node.data);
	}

	public void postOrder(Consumer<T> function) {
		postOrder(root, function);
	}

	public void buildString(Node node, StringBuilder builder) {
		if (node != null) {
			builder.append(node.data);
			builder.append(" {");
			buildString(node.right, builder.append("R: "));
			buildString(node.left, builder.append("L: "));
			builder.append("} ");
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		buildString(root, builder);
		return builder.toString();
	}

	public Node getRoot() {
		if (root == null) {
			root = new Node(null);
		}
		return root;
	}
}
