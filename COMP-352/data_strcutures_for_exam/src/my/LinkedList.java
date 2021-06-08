/**
 *
 */
package my;

public class LinkedList<T> implements MyList<T> {

	private Node head;

	private class Node {
		private T data;
		private Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	@Override
	public void add(T element) {
		if (head == null)
			head = new Node(element, null);
		else {
			Node current = head;
			while (current.next != null)
				current = current.next;

			current.next = new Node(element, null);
		}
	}

	@Override
	public void addAt(int index, T element) {

		verifyIndex(index);

		int count = 0;
		Node current = head;
		while (current != null) {

			if (count == index) {
				Node temp = new Node(element, current.next);
				current.next = temp;

				temp.data = current.data;
				current.data = element;
				break;
			}

			count++;
			current = current.next;
		}
	}

	@Override
	public T get(int index) {
		verifyIndex(index);

		int count = 0;
		Node current = head;
		while (current != null) {
			if (count == index)
				return current.data;
			count++;
			current = current.next;
		}

		return null;
	}

	@Override
	public T removeAt(int index) {
		int count = 0;
		Node current = head;
		Node previous = null;
		while (current != null) {
			if (count == index) {
				T temp = current.data;
				if (previous != null)
					previous.next = current.next;
				else
					head = null;
				return temp;
			}
			count++;
			previous = current;
			current = current.next;
		}

		return null;
	}

	@Override
	public void remove(T object) {
		removeAt(indexOf(object));
	}

	@Override
	public int indexOf(Object object) {
		Node current = head;
		int count = 0;
		while (current != null) {
			if (current.data.equals(object))
				return count;

			count++;
			current = current.next;
		}

		return -1;
	}

	@Override
	public int size() {
		int count = 0;
		Node current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		Node current = head;
		while (current != null) {
			builder.append(current.data.toString());
			if (current.next != null)
				builder.append(" -> ");

			current = current.next;
		}


		return builder.toString();
	}

	private void verifyIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new RuntimeException("Invalid index " + index + " for an " + getClass().getSimpleName() + " of size " + size());
		}
	}
}
