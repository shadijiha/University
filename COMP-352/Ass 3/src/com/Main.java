/**
 *
 */
package com;

public class Main {

	public static void main(String args[]) {

		BinarySearchTree tree = new BinarySearchTree();

		int[] test = {27, 42, 60, 38, 87, 18, 29, 2, 56};
		for (int e : test) {
			tree.insertIterative(e);
		}
		printTree("Original:", tree);

		//System.out.println("Subtrees: " + tree.countSubTreesWithinRange(1, 40));
		//printTree("After:", tree);

		tree.removeOutsideRange(20, 75);
		printTree(tree);

	}

	public static void printTree(String label, BinarySearchTree tree) {
		System.out.print(label + "\t");
		printTree(tree);
	}

	public static void printTree(BinarySearchTree tree) {
		tree.inOrder(e -> System.out.print(e + " "));
		System.out.println();
	}
}
