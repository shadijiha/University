import java.util.*;

/**
 *
 */

public class MyArrayList<T> implements List<T> {

	transient private T[] data;
	private int pointer;

	public MyArrayList(int initialSize) {
		data = (T[]) new Object[initialSize];
		pointer = 0;
	}

	public MyArrayList() {
		this(10);
	}

	@Override
	public boolean add(T t) {

		if (pointer >= data.length) {
			resize();
		}

		data[pointer++] = t;
		return true;
	}

	@Override
	public void add(int index, T element) {
		if (index >= pointer || index < 0)
			return;

		data[index] = element;
	}

	@Override
	public void clear() {
		data[0] = null;
		pointer = 0;
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
		if (index >= pointer || index < 0)
			return null;

		T tempData = data[index];
		data[index] = null;

		for (int i = index; i < pointer - 1; i++) {
			T temp = data[i];
			data[i] = data[i + 1];
			data[i + 1] = temp;
		}

		pointer--;
		return tempData;
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

		// Get the index of the object
		int index = -1;
		for (int i = 0; i < pointer; i++) {
			if (data[i].equals(o)) {
				index = i;
			}
		}

		return remove(index) != null;
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
		return pointer;
	}

	private void resize() {
		T[] newData = (T[]) new Object[data.length * 2];

		for (int i = 0; i < size(); i++) {
			newData[i] = data[i];
		}

		this.data = newData;
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
		return Arrays.toString(data);
	}
}
