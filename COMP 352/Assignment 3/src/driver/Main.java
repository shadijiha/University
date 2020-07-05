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
		tree.traverseInOrder(node -> depth_of_nodes.add(tree.depth(node)));

		//System.out.println(depth_of_nodes);

		//System.out.println("\n" + tree.height());
		//System.out.println(tree.min());

		/* Question 3 */
		IExperimentalMap<Integer, Integer> map = new HashTable<>(15, false);

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

			PrintStream writer = new PrintStream(new FileOutputStream("Timed_test.csv"));

			//for (int i = 100; i < 100_000; i += 1_000) {

			long before_time = System.nanoTime();

			CVR cvr = new CVR(1100, 15);
			cvr.test();

			var keys = cvr.allKeys();

			System.out.println(Arrays.toString(cvr.prevAccids(keys[0])));

			long time_taken = System.nanoTime() - before_time;

			writer.printf("%d, %d\n", 1000, time_taken);
			//}

			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* QUESTION 1 b) */
	public static int Count_Full_Nodes(BinarySearchTree<?> tree) {

		AtomicInteger count = new AtomicInteger();
		tree.traverseInOrder(node -> {
			if (node.hasLeft() && node.hasRight())
				count.getAndIncrement();
		});

		return count.get();
	}
}
