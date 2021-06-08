/**
 *
 */

package my;

public class ArrayList<T> implements MyList<T> {

	private transient T[] data;
	private int pointer;

	public ArrayList(int capacity) {
		data = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(5);
	}

	public void add(T element) {
		verifySize();

		data[pointer++] = element;
	}

	public void addAt(int index, T element) {
		verifyIndex(index);
		verifySize();

		for (int i = pointer - 1; i >= index; i--)
			data[i + 1] = data[i];

		data[index] = element;
		pointer++;
	}

	public T get(int index) {
		verifyIndex(index);
		return data[index];
	}

	public T removeAt(int index) {
		verifyIndex(index);
		T temp = data[index];

		data[index] = null;
		for (int i = index; i <= pointer; i++) {
			data[i] = data[i + 1];
		}
		pointer--;
		return temp;
	}

	public void remove(T object) {
		removeAt(indexOf(object));
	}

	public int indexOf(Object object) {
		for (int i = 0; i < pointer; i++)
			if (data[i].equals(object))
				return i;

		return -1;
	}

	public int size() {
		return pointer;
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

	private void verifyIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new RuntimeException("Invalid index " + index + " for an " + getClass().getSimpleName() + " of size " + size());
		}
	}

	private void resize() {
		T[] newData = (T[]) new Object[data.length * 2];
		System.arraycopy(data, 0, newData, 0, data.length);
		data = newData;
	}

}
