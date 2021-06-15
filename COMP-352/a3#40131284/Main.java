/**
 *
 */
package com;

public class Main {

	public static void main(String args[]) {

		BinarySearchTree tree = new BinarySearchTree();

		int[] test = {18, 23, 12, 28, 15, 9, 33, 25, 13, 11, 21};
		for (int e : test) {
			tree.add(e);
		}
		tree.inOrder(e -> System.out.print(e + " "));
		System.out.println();
		System.out.println(tree.countSubTreesWithinRange(8, 23));
	}
}
