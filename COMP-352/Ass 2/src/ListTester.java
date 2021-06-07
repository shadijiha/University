import java.io.*;
import java.util.*;

/**
 *
 */

public class ListTester {

	static PrintWriter writer;
	static volatile boolean finished = false;

	static {
		try {
			writer = new PrintWriter(new FileOutputStream("testrun.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Time elapsed for DEBUG puposes
		new Thread(() -> {

			int seconds = 0;
			while (!finished) {
				System.out.printf("Time elapsed: %d:%d\n", (int) (seconds / 60), seconds % 60);
				seconds++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		int[] N = {10, 100, 1000, 10000, /*100_000, 1000_000*/};

		for (int j = 0; j < N.length; j++) {
			int n = N[j];

			MyArrayList<Integer> list = new MyArrayList<>();
			ArrayList<Integer> javaList = new ArrayList<>();
			MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
			LinkedList<Integer> linkedList = new LinkedList<>();

			// ************** Insert **************
			writer.println("A) ************** Insertions **************\n");
			writer.printf("\n  N = %d\tInsert@start (ns)\tInsert@end (ns)\tInsert@random (ns)\n", n);
			insertAtStartList(list, n);
			insertAtEndList(list, n);
			insertAtRandomList(list, n);

			insertAtStartList(javaList, n);
			insertAtEndList(javaList, n);
			insertAtRandomList(javaList, n);

			insertAtStartList(myLinkedList, n);
			insertAtEndList(myLinkedList, n);
			insertAtRandomList(myLinkedList, n);

			insertAtStartList(linkedList, n);
			insertAtEndList(linkedList, n);
			insertAtRandomList(linkedList, n);

			writer.println("\n\nB) ************** Deletions **************\n");
			writer.printf("\n  N = %d\tRemove@start (ns)\tRemove@end (ns)\tRemove@random (ns)\tRemove byvalue (ns)\n", n);
			removeAtStart(list, n);
			removeAtEnd(list, n);
			removeAtRandom(list, n);
			removeByValue(list, n);

			removeAtStart(javaList, n);
			removeAtEnd(javaList, n);
			removeAtRandom(javaList, n);
			removeByValue(javaList, n);

			removeAtStart(myLinkedList, n);
			removeAtEnd(myLinkedList, n);
			removeAtRandom(myLinkedList, n);
			removeByValue(myLinkedList, n);

			removeAtStart(linkedList, n);
			removeAtEnd(linkedList, n);
			removeAtRandom(linkedList, n);
			removeByValue(linkedList, n);
		}

		writer.close();
		finished = true;
	}

	public static void insertAtStartList(List<Integer> list, int n) {
		list.clear();

		String session = list.getClass().getSimpleName() + "@start (n = " + n + ")";
		TestManager.start(session);
		for (int i = 0; i < n; i++) {
			int randomNumber = (int) TestManager.random(0, 2 * n);
			list.add(0, randomNumber);
		}
		TestManager.end(session);

		writer.printf("%s\t\t%d\t\t", list.getClass().getSimpleName(), TestManager.get(session));
		writer.flush();
	}

	public static void insertAtEndList(List<Integer> list, int n) {
		populateListUntil(list, n);

		String session = list.getClass().getSimpleName() + "@end (n = " + n + ")";
		TestManager.start(session);
		for (int i = n - 1; i >= 0; i--) {
			int randomNumber = (int) TestManager.random(0, 2 * n);
			list.add(i, randomNumber);
		}
		TestManager.end(session);

		writer.printf("%d\t\t", TestManager.get(session));
		writer.flush();
	}

	public static void insertAtRandomList(List<Integer> list, int n) {
		populateListUntil(list, n);

		String session = list.getClass().getSimpleName() + "@random (n = " + n + ")";
		TestManager.start(session);
		for (int i = n - 1; i >= 0; i--) {
			int randomNumber = (int) TestManager.random(0, 2 * n);
			int randomIndex = (int) TestManager.random(0, n - 1);
			list.add(randomIndex, randomNumber);
		}
		TestManager.end(session);

		writer.printf("%d\n", TestManager.get(session));
		writer.flush();
	}

	public static void removeAtStart(List<Integer> list, int n) {

		populateListUntil(list, n);

		String session = list.getClass().getSimpleName() + "@remove_start_n_" + n;
		TestManager.start(session);
		for (int i = 0; i < n; i++) {
			list.remove(0);
		}
		TestManager.end(session);

		writer.printf("%s\t\t%d\t\t", list.getClass().getSimpleName(), TestManager.get(session));
		writer.flush();
	}

	public static void removeAtEnd(List<Integer> list, int n) {

		// If list is empty then populate it with values
		populateListUntil(list, n);

		String session = list.getClass().getSimpleName() + "@remove_end_n_" + n;
		TestManager.start(session);
		for (int i = n - 1; i >= 0; i--) {
			list.remove(i);
		}
		TestManager.end(session);

		writer.printf("%d\t\t", TestManager.get(session));
		writer.flush();
	}

	public static void removeAtRandom(List<Integer> list, int n) {

		// If list is empty then populate it with values
		populateListUntil(list, n);

		String session = list.getClass().getSimpleName() + "@remove_random_n_" + n;
		TestManager.start(session);
		for (int i = 0; i < n; i++) {
			int randomIndex = (int) TestManager.random(0, n - 1);
			try {
				list.remove((int) i);
			} catch (Exception e) {
			}

		}
		TestManager.end(session);

		writer.printf("%d\t\t", TestManager.get(session));
		writer.flush();
	}

	public static void removeByValue(List<Integer> list, int n) {

		// If list is empty then populate it with values
		populateListUntil(list, n);

		String session = list.getClass().getSimpleName() + "@remove_byvalue_n_" + n;
		TestManager.start(session);
		for (int i = 0; i < n; i++) {
			int randomValue = (int) TestManager.random(0, n * 2);
			list.remove((Object) randomValue);
		}
		TestManager.end(session);

		writer.printf("%d\n", TestManager.get(session));
		writer.flush();
	}

	public static void populateListUntil(List<Integer> list, int n) {
		int tries = 0;
		while (list.size() < n) {
			int randomNumber = (int) TestManager.random(0, 2 * n);
			list.add(randomNumber);
			tries++;
			if (tries > 2000_000)
				throw new RuntimeException("Infinite loop alert!");
		}
	}
}
