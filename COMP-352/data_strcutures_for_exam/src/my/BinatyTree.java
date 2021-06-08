/**
 *
 */

package my;

import java.util.*;
import java.util.function.*;

public class BinatyTree<T extends Comparable<T>> implements Tree<T> {

	private TreeNode<T> root;

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
	public TreeNode<T>[] children(TreeNode<T> node) {
		return new TreeNode[0];
	}

	@Override
	public void preOrder(Consumer<T> func) {

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
}
