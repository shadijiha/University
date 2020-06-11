package driver;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;

public class Main {

	public static void main(String[] args) {
		// write your code here

		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		tree.insert(7);
		tree.insert(9);
		tree.insert(4);
		tree.insert(1);
		tree.insert(6);
		tree.insert(8);
		tree.insert(10);

		List<Integer> depth_of_nodes = new ArrayList<>();
		tree.traverseInOrder(e -> depth_of_nodes.add(tree.depth(e)));

		//System.out.println(depth_of_nodes);

		//System.out.println("\n" + tree.height());
		//System.out.println(tree.min());

		/* Question 3 */
		HashTable<Integer, Integer> map = new HashTable<>(15, false);

		map.put(32, 0);
		map.put(147, 0);
		map.put(265, 0);
		map.put(195, 0);
		map.put(207, 0);
		map.put(180, 0);
		map.put(21, 0);
		map.put(16, 0);
		map.put(189, 0);
		map.put(202, 0);
		map.put(91, 0);
		map.put(94, 0);
		map.put(162, 0);
		map.put(75, 0);
		map.put(37, 0);
		map.put(77, 0);
		map.put(81, 0);
		map.put(48, 0);

		//System.out.println(map);
		//System.out.println(map.getCollisions());

		/************* PART 2 ******************/
		try {
			PrintStream writer = new PrintStream(new FileOutputStream("Unit_test.txt"));
			System.setOut(writer);

			CVR cvr = new CVR().setKeyLength(15).setThreshold(100);
			cvr.test();

			var keys = cvr.allKeys();
			for (var key : keys)
				System.out.println(key);

			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* QUESTION 2 */
	public static int Count_Full_Nodes(BinarySearchTree<?> tree) {

		AtomicInteger count = new AtomicInteger();
		tree.traverseInOrder(e -> {
			if (e.hasLeft() && e.hasRight())
				count.getAndIncrement();
		});

		return count.get();
	}
}
