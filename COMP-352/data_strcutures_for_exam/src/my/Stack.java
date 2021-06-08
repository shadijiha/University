/**
 *
 */
package my;

import java.util.EmptyStackException;

public class Stack<T> {

	private transient T data[];
	private int pointer;

	public Stack(int initialCapacity) {
		data = (T[]) new Object[initialCapacity];
		pointer = 0;
	}

	public Stack() {
		this(10);
	}

	public void push(T element) {
		verifySize();
		data[pointer++] = element;
	}

	public T pop() {
		if (isEmpty())
			throw new EmptyStackException();

		T temp = data[pointer - 1];
		data[--pointer] = null;
		return temp;
	}

	public T top() {
		if (isEmpty())
			throw new EmptyStackException();

		return data[pointer - 1];
	}

	public T peek() {
		return top();
	}

	public int size() {
		return pointer;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public T[] rawArray() {
		Object arra[] = new Object[data.length];
		System.arraycopy(data, 0, arra, 0, data.length);
		return (T[]) arra;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[");
		for (int i = 0; i < pointer; i++) {
			builder.append(data[i]);
			if (i < pointer - 1)
				builder.append(", ");
		}

		builder.append("]");

		return builder.toString();
	}

	private void verifySize() {
		if (pointer >= data.length - 1) {
			resize();
		}
	}

	private void resize() {
		T[] newData = (T[]) new Object[data.length * 2];
		System.arraycopy(data, 0, newData, 0, data.length);
		data = newData;
	}
}
