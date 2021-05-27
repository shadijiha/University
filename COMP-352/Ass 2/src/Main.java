import java.util.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) throws InterruptedException {

		int[] testSubject = TestManager.generateArray(10);

		MyArrayList<Integer> list = new MyArrayList<>(testSubject.length);
		ArrayList<Integer> javaList = new ArrayList<>(10);

		MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
		LinkedList<Integer> linkedList = new LinkedList<>();

		for (Integer i : testSubject) {
			TestManager.start("MyArrayList add");
			list.add(i);
			TestManager.end("MyArrayList add");

			TestManager.start("ArrayList add");
			javaList.add(i);
			TestManager.end("ArrayList add");

			TestManager.start("MyLinkedList add");
			myLinkedList.add(i);
			TestManager.end("MyLinkedList add");

			TestManager.start("LinkedList add");
			linkedList.add(i);
			TestManager.end("LinkedList add");
		}

		System.out.println(list.toString());
		System.out.println(myLinkedList.toString());
	}
}
