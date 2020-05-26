package driver;

import java.util.*;

public class ShadoStack<T extends Number & Comparable> implements Iterable<T> {

	private T[] data;
	private int pointer;

	public ShadoStack(int initialCapacity) {
		data = (T[]) new Number[initialCapacity];
		pointer = 0;
	}

	public ShadoStack() {
		this(10);
	}

	private ShadoStack(final ShadoStack<T> other) {

		this(other.data.length);
		pointer = other.pointer;

		for (int i = 0; i < other.data.length; i++)
			this.data[i] = other.data[i];

	}

	public T peek() {
		return data[pointer - 1];
	}

	public void push(T element) {
		data[pointer++] = element;
	}

	public T pop() {
		pointer--;
		T data_to_retrun = data[pointer];
		data[pointer] = null;

		return data_to_retrun;
	}

	public T max() {

		T current_max = null;
		for (T t : this) {
			if (current_max == null) {
				current_max = t;
				continue;
			}

			if (t.compareTo(current_max) > 0)
				current_max = t;
		}
		return current_max;
	}

	public int size() {
		return pointer;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");

		var it = this.iterator();
		while (it.hasNext()) {
			builder.append(it.next());

			if (it.hasNext())
				builder.append(", ");
		}

		builder.append("]");

		return builder.toString();
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return new ShadoStackIterator<T>(new ShadoStack<T>(this));
	}

	private static class ShadoStackIterator<T extends Number & Comparable> implements Iterator<T> {

		private int current_index;
		private ShadoStack<T> stack;

		public ShadoStackIterator(ShadoStack<T> stack) {
			this.stack = stack;
			current_index = this.stack.pointer;
		}

		/**
		 * Returns {@code true} if the iteration has more elements.
		 * (In other words, returns {@code true} if {@link #next} would
		 * return an element rather than throwing an exception.)
		 *
		 * @return {@code true} if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			try {
				return stack.data[current_index - 1] != null;
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public T next() {
			current_index--;
			return stack.pop();
		}
	}
}
