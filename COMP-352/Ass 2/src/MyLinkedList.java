/**
 *
 */

public class MyLinkedList<T> implements List<T> {

	private Node head;
	private int size = 0;

	@Override
	public Boolean add(T t) {

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
	public T remove(int index) {

		Node result = null;

		int counter = 0;
		Node current = head;
		while (current != null) {

			if (counter == index) {

				Node previous = current.previous();
				previous.setNext(current.next());
				current.next().setPrevious(previous);
				result = current;
				break;
			}

			counter++;
			current = current.next();
		}


		return result != null ? result.get() : null;
	}

	@Override
	public Boolean remove(Object o) {
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
