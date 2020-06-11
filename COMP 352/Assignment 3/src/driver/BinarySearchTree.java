/**
 *
 */

package driver;

import java.util.function.*;

public class BinarySearchTree<T extends Comparable<T>> {

	private Node<T> root;

	public BinarySearchTree() {
	}

	public void insert(T element) {

		if (root == null) {
			root = new Node<T>(element, null, null);
			return;
		}

		var current = root;
		while (true) {

			// If number is less than parent insert to the left
			if (element.compareTo(current.element()) < 0) {

				// Current is the new parent of the node we want to insert
				if (!current.hasLeft()) {
					current.setLeft(new Node<T>(element, null, null));
					break;
				}

				current = current.left();
			} else {
				if (!current.hasRight()) {
					current.setRight(new Node<T>(element, null, null));
					break;
				}
				current = current.right();
			}

		}

	}

	public boolean find(T element) {

		var current = root;
		while (current != null) {

			// if value is smaller go the left subtree
			if (element.compareTo(current.element()) < 0) {
				current = current.left();
			} else if (element.compareTo(current.element()) > 0) {
				current = current.right();
			} else {
				return true;
			}
		}

		return false;
	}

	public int depth(Node<T> root) {
		if (root == null)
			return 0;
		else {

			int left_depth = depth(root.left());
			int right_depth = depth(root.right());

			if (left_depth > right_depth)
				return left_depth + 1;
			else
				return right_depth + 1;
		}
	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> root) {
		if (root == null)
			return -1;

		if (isLeaf(root))
			return 0;

		return 1 + Math.max(height(root.left), height(root.right));
	}

	/**
	 * This is for a binary search tree
	 * @return
	 */
	public T min() {

		if (root == null)
			throw new IllegalStateException();

		var current = root;
		var last = current;
		while (current != null) {
			last = current;
			current = current.left();
		}

		return last.element();
	}

	/**
	 * This is for a Binary tree
	 * @param root
	 * @return
	 */
	private T min(Node<T> root) {

		if (isLeaf(root)) {
			return root.element();
		}

		var left = min(root.left());
		var right = min(root.right());

		return minimum(minimum(left, right), root.element());
	}

	private boolean isLeaf(Node<T> node) {
		return !node.hasLeft() && !node.hasRight();
	}

	private static <E extends Comparable<E>> E minimum(E obj1, E obj2) {
		return obj1.compareTo(obj2) < 0 ? obj1 : obj2;
	}

	public void traversePreOrder(Consumer<Node<T>> operation) {
		traversePreOrder(root, operation);
	}

	private void traversePreOrder(Node<T> root, Consumer<Node<T>> operation) {

		if (root == null)
			return;

		operation.accept(root);
		traversePreOrder(root.left(), operation);
		traversePreOrder(root.right(), operation);
	}

	public void traverseInOrder(Consumer<Node<T>> operation) {
		traverseInOrder(root, operation);
	}

	private void traverseInOrder(Node<T> root, Consumer<Node<T>> operation) {

		if (root == null)
			return;

		traverseInOrder(root.left(), operation);
		operation.accept(root);
		traverseInOrder(root.right(), operation);
	}

	public void traversePostOrder(Consumer<Node<T>> operation) {
		traversePostOrder(root, operation);
	}

	private void traversePostOrder(Node<T> root, Consumer<Node<T>> operation) {

		if (root == null)
			return;

		traversePostOrder(root.left(), operation);
		traversePostOrder(root.right(), operation);
		operation.accept(root);
	}

	public String toString() {
		return null;
	}

	public class Node<T> {
		private T element;
		private Node<T> right;
		private Node<T> left;

		private Node(T element, Node<T> left, Node<T> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}

		public boolean hasRight() {
			return right != null;
		}

		public boolean hasLeft() {
			return left != null;
		}

		public T element() {
			return element;
		}

		public Node<T> right() {
			return right;
		}

		public Node<T> left() {
			return left;
		}

		public void setRight(Node<T> right) {
			this.right = right;
		}

		public void setLeft(Node<T> left) {
			this.left = left;
		}

		public String toString() {
			return element.toString();
		}
	}
}
