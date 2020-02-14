package driver;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ShadoList<T> implements Iterable<T>, Iterator<T> {
	private T[] data;
	private int length;
	private final int LENGTH_INCREMENT = 10;
	private int MAX_LENGTH;

	public ShadoList(int initialLength) {
		MAX_LENGTH = initialLength;
		length = 0;
		data = (T[]) new Object[MAX_LENGTH];
	}

	public ShadoList() {
		this(10);
	}

	public ShadoList(final ShadoList<T> other) {
		this(other.MAX_LENGTH);
		// Copy content of the original array
		this.length = other.length;
		this.MAX_LENGTH = other.MAX_LENGTH;
		if (length >= 0) {
			System.arraycopy(other.data, 0, this.data, 0, length);
		}
	}

	private void incrementSpace() {
		// Create new larger array
		T[] newArray = (T[]) new Object[MAX_LENGTH + LENGTH_INCREMENT];

		// Copy content of the original array
		if (length >= 0) System.arraycopy(data, 0, newArray, 0, length);

		// Replace the original array
		this.data = newArray;
		this.MAX_LENGTH += LENGTH_INCREMENT;
	}

	public T get(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= length) {
			throw new ArrayIndexOutOfBoundsException("Cannot access index " + index + " in a ShadoList of size " + length);
		}
		return this.data[index];
	}

	public void add(T element) {
		// Check if there's space
		if (length >= data.length) {
			// Add space before adding the element
			incrementSpace();
		}

		// Add element
		this.data[length] = element;

		// update data
		this.length++;
	}

	public T remove(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= length) {
			throw new ArrayIndexOutOfBoundsException("Cannot access index " + index + " in a ShadoList of size " + length);
		}

		// Remove the element
		T returnValue = this.data[index];
		this.data[index] = null;
		length--;

		// Shift everything back 1 index
		for (int i = index; i < length; i++) {
			// Swap the removed index and the next one until the end of the array
			T temp = this.data[i];
			this.data[i] = this.data[i + 1];
			this.data[i + 1] = temp;
		}

		return returnValue;
	}

	public T pop() {
		return this.remove(length - 1);
	}

	public int size() {
		return this.length;
	}

	public Stream<T> stream() {
		return StreamSupport.stream(spliterator(), false);
	}

	public Stream<T> parallelStream() {
		return StreamSupport.stream(spliterator(), true);
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return this;
	}

	/**
	 * Returns {@code true} if the iteration has more elements.
	 * (In other words, returns {@code true} if {@link #next} would
	 * return an element rather than throwing an exception.)
	 *
	 * @return {@code true} if the iteration has more elements
	 */
	private int nextIndex = 0;

	@Override
	public boolean hasNext() {
		return nextIndex < length;
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return the next element in the iteration
	 * @throws NoSuchElementException if the iteration has no more elements
	 */
	@Override
	public T next() {
		if (nextIndex >= length)
			throw new NoSuchElementException();

		nextIndex++;
		return this.get(nextIndex - 1);
	}
}
