/**
 *
 */

package driver;

import java.util.*;

public class SharedArrayStack {

	private static final int N = 10;
	private static final SharedArrayStackNode[] array = new SharedArrayStackNode[N];

	private SharedArrayStackNode last;

	public SharedArrayStack() {
		last = null;
	}

	public void push(int number) {

		// First element in the stack
		if (last == null) {

			int index_to_add = 0;
			while (array[index_to_add] != null)
				index_to_add++;

			array[index_to_add] = new SharedArrayStackNode(number, index_to_add, null, null);
			last = array[index_to_add];

		} else {

			int index_to_add = last.index();
			while (array[index_to_add] != null)
				index_to_add++;

			array[last.index()].setNext(index_to_add);
			array[index_to_add] = new SharedArrayStackNode(number, index_to_add, last.index(), null);

			last = array[index_to_add];
		}
	}

	public int pop() throws EmptyStackException {

		// if stack is empty
		if (last == null)
			throw new EmptyStackException();

		var temp_last = new SharedArrayStackNode(last);
		array[last.index()] = null;

		if (temp_last.previous() == null)
			last = null;
		else
			last = array[temp_last.previous()];

		return temp_last.value();
	}

	public int size() {

		int count = 0;

		Integer index = last.index();

		while (index != null) {
			count++;
			index = array[index].previous();
		}

		return count;
	}

	public int top() throws EmptyStackException {

		if (last == null)
			throw new EmptyStackException();

		return array[last.index()].value();
	}

	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("[");

		Integer index = last.index();

		while (index != null) {

			var element = array[index];

			builder.append(element.value());

			// Don't add ", " if it is the last element
			if (element.previous() != null)
				builder.append(", ");

			index = element.previous();
		}

		builder.append("]");

		return builder.toString();
	}

	public static void printArray() {
		System.out.println(Arrays.toString(SharedArrayStack.array));
	}

	private static class SharedArrayStackNode {

		private int value;
		private int index;
		private Integer next;
		private Integer previous;

		private SharedArrayStackNode(int value, int index, Integer previous_index, Integer next_index) {
			this.value = value;
			this.index = index;
			this.previous = previous_index;
			this.next = next_index;
		}

		private SharedArrayStackNode(final SharedArrayStackNode other) {
			value = other.value;
			index = other.index;
			previous = other.previous;
			next = other.next;
		}

		private boolean hasNext() {
			return next != null;
		}

		private boolean hasPrevious() {
			return previous != null;
		}

		private Integer next() {
			return next;
		}

		private Integer previous() {
			return previous;
		}

		public int index() {
			return index;
		}

		private int value() {
			return value;
		}

		private void setNext(Integer o) {
			next = o;
		}

		private void setPrevious(Integer o) {
			previous = o;
		}

		private void setIndex(int i) {
			index = i;
		}

		private void setValue(int value) {
			this.value = value;
		}

		public String toString() {
			return value + "";
		}
	}
}
