/**
 *
 */

package my;

import java.util.Queue;
import java.util.*;
import java.util.function.*;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

	protected TreeNode<T> root;

	public BinaryTree(T array[]) {
		for (T e : array)
			add(e);
	}

	public BinaryTree() {
	}

	@Override
	public void add(T element) {
		add(element, root);
	}

	@Override
	public T remove(T element) {
		return null;
	}

	public void add(T element, TreeNode<T> temp) {
		if (temp == null) {
			root = new TreeNode<T>(element);
			return;
		}
		Queue<TreeNode<T>> q = new ArrayDeque<>();
		q.add(temp);

		// Do level order traversal until we find
		// an empty place.
		while (!q.isEmpty()) {
			temp = q.peek();
			q.remove();

			if (temp.left == null) {
				temp.left = new TreeNode<T>(element);
				break;
			} else
				q.add(temp.left);

			if (temp.right == null) {
				temp.right = new TreeNode<T>(element);
				break;
			} else
				q.add(temp.right);
		}
	}

	@Override
	public TreeNode<T> root() {
		return root;
	}

	@Override
	public TreeNode<T> parent(TreeNode<T> node) {
		return parent(root, node.data, null);
	}

	private TreeNode<T> parent(TreeNode<T> current, T val, TreeNode<T> previous) {
		if (current == null)
			return null;

		// If current node is the required node
		if (current.data.equals(val)) {
			return previous;
		} else {

			// Recursive calls for the children
			// of the current node
			// Current node is now the new parent
			parent(current.left, val, current);
			parent(current.right, val, current);
		}

		return null;
	}

	@Override
	public MyList<TreeNode<T>> children(TreeNode<T> node) {
		return children(node, new ArrayList<TreeNode<T>>());
	}

	private ArrayList<TreeNode<T>> children(TreeNode<T> node, ArrayList<TreeNode<T>> previous) {
		if (node == null)
			return previous;
		previous.add(node);
		children(node.left, previous);
		children(node.right, previous);

		return previous;
	}

	@Override
	public void preOrder(Consumer<T> func) {
		preOrder(func, root);
	}

	private void preOrder(Consumer<T> func, TreeNode<T> node) {
		if (node == null)
			return;

		/* first print data of node */
		func.accept(node.data);

		/* then recur on left sutree */
		preOrder(func, node.left);

		/* now recur on right subtree */
		preOrder(func, node.right);
	}

	@Override
	public void postOrder(Consumer<T> func) {
		postOrder(func, root);
	}

	public void postOrder(Consumer<T> func, TreeNode<T> node) {
		if (node == null)
			return;

		// first recur on left subtree
		postOrder(func, node.left);

		// then recur on right subtree
		postOrder(func, node.right);

		// now deal with the node
		func.accept(node.data);
	}

	@Override
	public void inOrder(Consumer<T> func) {
		inOrder(func, root);
	}

	private void inOrder(Consumer<T> func, TreeNode<T> node) {
		if (node == null)
			return;

		inOrder(func, node.left);
		func.accept(node.data);
		inOrder(func, node.right);
	}

	@Override
	public Iterable<T> positions() {
		return null;
	}

	@Override
	public int size() {
		return sizeOfSubtree(root);
	}

	public int height() {
		return height(root);
	}

	private int height(TreeNode<T> node) {
		if (node == null)
			return 0;
		else {
			/* compute the depth of each subtree */
			int lDepth = height(node.left);
			int rDepth = height(node.right);

			/* use the larger one */
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	public int depth(TreeNode<T> node) {
		return depth(root, node.data);
	}

	private int depth(TreeNode<T> root, T x) {

		// Base case
		if (root == null)
			return -1;

		// Initialize distance as -1
		int dist = -1;

		// Check if x is current node=
		if ((root.data.equals(x)) ||

				// Otherwise, check if x is
				// present in the left subtree
				(dist = depth(root.left, x)) >= 0 ||

				// Otherwise, check if x is
				// present in the right subtree
				(dist = depth(root.right, x)) >= 0)

			// Return depth of the node
			return dist + 1;

		return dist;
	}

	/* computes number of nodes in tree */
	public int sizeOfSubtree(TreeNode<T> node) {
		if (node == null)
			return 0;
		else
			return (sizeOfSubtree(node.left) + 1 + sizeOfSubtree(node.right));
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public String toString() {
		return new TreePrinter<T>(this).toString();
	}
}
