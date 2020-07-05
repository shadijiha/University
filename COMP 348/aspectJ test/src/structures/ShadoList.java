package structures;

import java.util.*;

public class ShadoList {

	private int[] array;
	private int pointer;

	private int total_resize;

	public ShadoList(int initial_size) {
		array = new int[initial_size];
		pointer = 0;
	}

	public ShadoList(int... es) {
		this(es.length);

		for (var e : es)
			add(e);
	}

	public ShadoList() {
		this(10);
	}

	public void add(int e) {

		if (pointer == array.length - 2) {
			resize();
		}

		array[pointer++] = e;
	}

	public int get(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size());
		}
		return array[index];
	}

	public int remove(int index) {

		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size());
		}

		var return_val = array[index];
		array[index] = 0;

		for (int i = index; i < size() - 1; i++) {
			var temp = array[i];
			array[i] = array[i + 1];
			array[i + 1] = temp;
		}

		pointer--;

		return return_val;
	}

	public int totalResizes() {
		return total_resize;
	}

	public int size() {
		return pointer;
	}

	private void resize() {

		int[] copy = new int[array.length * 2];

		for (int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}

		array = copy;

		total_resize++;
	}

	public String toString() {
		return Arrays.toString(array);
	}
}
