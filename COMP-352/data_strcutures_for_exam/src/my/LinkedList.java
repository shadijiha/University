/**
 *
 */
package my;

import java.util.Iterator;

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
	public void set(int index, T val) {
		verifyIndex(index);

		Node current = head;
		int count = 0;
		while (count < index) {
			count++;
			current = current.next;
		}

		if (current != null) {
			current.data = val;
		}
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
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator(this);
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

	public class LinkedListIterator implements Iterator<T> {
		private LinkedList<T> list;
		private Node current;

		public LinkedListIterator(LinkedList<T> list) {
			this.list = list;
			this.current = list.head;
		}

		@Override
		public boolean hasNext() {
			return current != null && current.next != null;
		}

		@Override
		public T next() {
			current = current.next;
			return current.data;
		}
	}
}
