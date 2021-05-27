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

	@Override
	public Boolean add(T t) {

		if (pointer == data.length)
			return false;

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
	public Boolean remove(Object o) {

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
	public int size() {
		return pointer;
	}

	@Override
	public String toString() {
		return Arrays.toString(data);
	}
}
