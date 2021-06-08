/**
 *
 */
package my;

public class ArrayQueue<T> implements Queue<T> {
	private ArrayList<T> data;

	public ArrayQueue(int initial_size) {
		data = new ArrayList<>(initial_size);
	}

	public ArrayQueue() {
		this(10);
	}

	@Override
	public void enqueue(T obj) {
		data.add(obj);
	}

	@Override
	public T dequeue() {
		if (isEmpty())
			throw new EmptyQueueException();
		return data.removeAt(0);
	}

	@Override
	public T front() {
		if (isEmpty())
			throw new EmptyQueueException();
		return data.get(0);
	}

	@Override
	public void addFirst(T e) {
		data.addAt(0, e);
	}

	@Override
	public void addLast(T e) {
		this.enqueue(e);
	}

	@Override
	public T removeFirst() {
		return this.dequeue();
	}

	@Override
	public T removeLast() {
		return data.removeAt(size() - 1);
	}

	@Override
	public T getFirst() {
		if (isEmpty())
			throw new EmptyQueueException();
		return data.get(0);
	}

	@Override
	public T getLast() {
		if (isEmpty())
			throw new EmptyQueueException();
		return data.get(size() - 1);
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	public T[] rawArray() {
		return data.rawArray();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[");
		for (int i = 0; i < data.size(); i++) {
			builder.append(data.get(i));
			if (i < size() - 1)
				builder.append(", ");
		}

		builder.append("]");

		return builder.toString();
	}

	private static class EmptyQueueException extends RuntimeException {
	}
}
