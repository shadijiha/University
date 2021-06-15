/**
 *
 */
package com;

import java.util.*;
import java.util.function.*;

public class BinarySearchTree {

	private Random random = new Random();
	private Node root;

	public void add(int element) {
		root = insertRecursive(root, element);
	}

	public void insertRandomRecursive() {
		add(random.nextInt() % 100);
	}

	public void insertRandomIterative() {
		int r = random.nextInt();
		insertIterative(r % 100);
	}

	private Node insertRecursive(Node root, int element) {
		/* If the tree is empty,
           return a new node */
		if (root == null) {
			root = new Node(element, null, null);
			return root;
		}

		/* Otherwise, recur down the tree */
		if (element < root.data)
			root.left = insertRecursive(root.left, element);
		else if (element > root.data)
			root.right = insertRecursive(root.right, element);

		/* return the (unchanged) node pointer */
		return root;
	}

	public void insertIterative(int element) {

		if (root == null) {
			root = new Node(element, null, null);
			return;
		}

		Node parent = null;
		Node current = root;
		while (current != null) {

			parent = current;
			if (element < current.data) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		if (element < parent.data)
			parent.left = new Node(element, null, null);
		else
			parent.right = new Node(element, null, null);
	}

	public void removeOutsideRange(int min, int max) {
		Integer[] array = new Integer[size()];
		int[] index = {0};
		inOrder(e -> array[index[0]++] = e);

		for (Integer e : array)
			if (e != null && ((int) e < min || (int) e > max))
				remove(e);
	}

	public void remove(int element) {
		root = remove(root, element);
	}

	private Node remove(Node root, int key) {
		/* Base Case: If the tree is empty */
		if (root == null)
			return root;

		/* Otherwise, recur down the tree */
		if (key < root.data)
			root.left = remove(root.left, key);
		else if (key > root.data)
			root.right = remove(root.right, key);

			// if key is same as root's
			// key, then This is the
			// node to be deleted
		else {
			// node with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			// node with two children: Get the inorder
			// successor (smallest in the right subtree)
			root.data = findMinvalue(root.right);

			// Delete the inorder successor
			root.right = remove(root.right, root.data);
		}

		return root;
	}

	private int findMinvalue(Node root) {
		int minv = root.data;
		while (root.left != null) {
			minv = root.left.data;
			root = root.left;
		}
		return minv;
	}

	public int countSubTreesWithinRange(int min, int max) {
		return countSubTreesWithinRange(min, max, root, 0);
	}

	public int countSubTreesWithinRange(int min, int max, Node current, int total) {
		if (current == null)
			return total;

		if (current.data > min && current.data < max) {
			total++;
			total = countSubTreesWithinRange(min, max, current.left, total);
			total = countSubTreesWithinRange(min, max, current.right, total);
		}
		return total;
	}

	public void inOrder(Consumer<Integer> func) {
		inOrder(func, root);
	}

	private void inOrder(Consumer<Integer> func, Node node) {
		if (node == null)
			return;

		inOrder(func, node.left);
		func.accept(node.data);
		inOrder(func, node.right);
	}

	public Node root() {
		return root;
	}

	public int size() {
		final List<Integer> list = new ArrayList<>();
		inOrder(e -> list.add(e));
		return list.size();
	}

	public class Node {
		public int data;
		public Node left;
		public Node right;

		public Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public Node(int data) {
			this.data = data;
		}
	}
}
