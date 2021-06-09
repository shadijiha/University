import java.util.*;

/**
 *
 */

public class MyLinkedList<T> implements List<T> {

	private Node head;
	private int size = 0;

	@Override
	public boolean add(T t) {

		if (head == null) {
			head = new Node(t, null, null);
			size++;
			return true;
		}

		Node previous = null;
		Node current = head;
		while (current != null) {

			if (!current.hasNext()) {
				current.setNext(new Node(t, null, previous));
				break;
			}

			previous = current;
			current = current.next();
		}

		head.setPrevious(current.next());

		size++;
		return true;
	}

	@Override
	public void add(int index, T element) {

		if (index < 0 || index <= size())
			return;

		Node current = head;
		int counter = 0;
		while (current != null) {
			if (counter == index) {
				Node temp = current.next();
				current.setNext(new Node(element, current, temp));
				break;
			}
			counter++;
			current = current.next();
		}

	}

	@Override
	public void clear() {

		// Remove all links to the nodes so it gets collected by the garbage collector
		Node current = head;
		while (current != null) {
			Node temp = current.next();

			current.setNext(null);
			current.setPrevious(null);
			current = temp;
		}

		head = null;
		size = 0;
	}

	@Override
	public T get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int index) {
		int count = 0;
		Node current = head;
		Node previous = null;
		while (current != null) {
			if (count == index) {
				T temp = current.data;
				if (previous != null) {
					previous.next = current.next;
					current.next.previous = previous;
				} else
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
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		Node current = head;
		while (current != null) {
			if (current.get().equals(o)) {
				Node previous = current.previous();
				previous.setNext(current.next());
				current.next().setPrevious(previous);
				return true;
			}
			current = current.next();
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		/*int counter = 0;

		Node current = head;
		while (current != null) {
			counter++;
			current = current.next();
		}

		return counter;*/

		return size;
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		int size = size();

		int counter = 0;
		Node current = head;
		while (current != null) {

			builder.append(current.get());

			if (counter < size - 1)
				builder.append(" --> ");

			current = current.next();
			counter++;
		}

		return builder.toString();
	}

	public class Node {
		private T data;
		private Node next;
		private Node previous;

		public Node(T data, Node next, Node previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

		public boolean hasNext() {
			return next != null;
		}

		public boolean hasPrevious() {
			return previous != null;
		}

		public T get() {
			return data;
		}

		public Node next() {
			return this.next;
		}

		public Node previous() {
			return this.previous;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public void setData(T data) {
			this.data = data;
		}
	}
}
