/**
 *
 */

package driver;

import java.util.*;

public class CVR {

	private int threshold;
	private int key_length;

	private LinkedList<Entry>[] list;
	private int size;

	public CVR(int threshold, int key_length) {
		this.threshold = threshold;
		this.key_length = key_length;
		list = new LinkedList[threshold];
	}

	public CVR() {
		this(100, 10);
	}

	public CVR setThreshold(int threshold) {

		if (threshold < 100 || threshold > 900_000)
			throw new IllegalArgumentException("100 <= Threshold <= 900 000! Cannot be " + threshold);

		this.threshold = threshold;

		return this;
	}

	public CVR setKeyLength(int length) {
		if (length < 10 || length > 17)
			throw new IllegalArgumentException("10 <= length <= 17! Cannot be " + length);

		this.key_length = length;

		return this;
	}

	public String generate() {

		StringBuilder builder = new StringBuilder();
		String[] tokens = "abcdefghijklmnopqrstuvwxyz0123456789".split("");

		for (int i = 0; i < key_length; i++) {
			String token = tokens[(int) (Math.random() * tokens.length)];
			builder.append(token);
		}

		return builder.toString().toUpperCase();
	}

	public void add(String key, String value) {

		if (size() >= list.length)
			throw new IllegalStateException("CVR is full!");

		int index = hash(key);

		// First element to be added
		if (list[index] == null)
			list[index] = new LinkedList<>();

		// Add element
		list[index].addLast(new Entry(key, value));
		size++;

	}

	public String remove(String key) {

		Entry removed = null;

		// FInd and remove the key
		OuterLoop:
		for (LinkedList<Entry> entries : list) {

			if (entries == null)
				continue;

			for (Entry entry : entries) {

				// If the key is found remove that entry
				if (entry.key.equals(key)) {
					removed = entry;
					entries.remove(entry);
					size--;
					break OuterLoop;
				}
			}
		}

		if (removed == null)
			return null;

		return removed.value;
	}

	/**
	 * Finds all the values that have a given key
	 * O(n)
	 * @param key Key to search
	 * @return Returns an array of values of the given key
	 */
	public String[] getValues(String key) {

		// Find the values by key
		LinkedList<Entry> query = new LinkedList<>();

		OuterLoop:
		for (LinkedList<Entry> entries : list) {

			if (entries == null)
				continue;

			for (Entry entry : entries) {

				// If the key is found remove that entry
				if (entry.key.equals(key)) {
					query = entries;
					break OuterLoop;
				}
			}
		}

		// Convert the values to an array
		String[] values = new String[query.size()];

		int index = 0;
		for (Entry e : query) {
			values[index] = e.value;
			index++;
		}

		return values;
	}

	public String[] allKeys() {

		// Find all keys
		Set<String> keys = new HashSet<>();

		for (var l : list) {

			if (l == null)
				continue;

			// Add the key to the set
			for (var e : l)
				keys.add(e.key);
		}

		// Convert the set to an array and sort it
		String[] result = keys.toArray(new String[0]);

		// If the space is priority (threshold < 1000)
		if (threshold < 1000) {

			// Using Heapsort
			// Best: O(n log n), Worst: O(n log n), Space: O(1)

			// Build heap
			int n = result.length;
			for (int i = n / 2 - 1; i >= 0; i--)
				heapify(result, n, i);

			// Extract elements from the heap
			for (int i = n - 1; i > 0; i--) {
				String temp = result[0];
				result[0] = result[i];
				result[i] = temp;

				// Reduce the heap
				heapify(result, i, 0);
			}

		} else {
			// Speed is priority
			// Using TimSort
			// Best: O(n), Worst: O(n log n), Space: O(n)

			final int RUN = 32;    // Devide array into blocks
			int n = result.length;
			for (int i = 0; i < n; i += RUN) {
				//https://www.geeksforgeeks.org/timsort/
			}

		}


		return result;
	}

	public String nextKey(String key) {

		EntryData data = getEntryData(key);

		if (data == null)
			return null;

		// See if the linked list of that data has a second element
		if (list[data.index_list].size() > 1) {

			try {
				return list[data.index_list].get(data.index_linked_list + 1).key;
			} catch (IndexOutOfBoundsException ignored) {

			}
		}

		// If the next key is the next element in the array (rather than the next element inside the same linked list)
		for (int i = data.index_list + 1; i < list.length; i++) {
			if (list[i] != null) {
				return list[i].element().key;
			}
		}

		return null;
	}

	public String prevKey(String key) {

		EntryData data = getEntryData(key);

		if (data == null)
			return null;

		// See if the linked list of that data has a second element
		if (list[data.index_list].size() > 1) {
			try {
				return list[data.index_list].get(data.index_linked_list - 1).key;
			} catch (IndexOutOfBoundsException ignored) {
			}
		}


		// If the next key is the next element in the array (rather than the next element inside the same linked list)
		for (int i = data.index_list - 1; i >= 0; i--) {
			if (list[i] != null) {
				return list[i].element().key;
			}
		}

		return null;
	}

	public void test() {

		for (int i = 0; i < threshold; i++) {
			add(generate(), "car " + i);
		}
	}

	public int getThreshold() {
		return threshold;
	}

	public int size() {
		return size;
	}

	private int hash(String key) {
		return Math.abs(key.hashCode() % list.length);
	}

	private EntryData getEntryData(String key) {

		EntryData data = null;

		OuterLoop:
		for (int i = 0; i < list.length; i++) {

			if (list[i] == null)
				continue;

			int j = 0;
			for (Entry e : list[i]) {

				if (e.key.equals(key)) {
					data = new EntryData(new Entry(e), i, j);
					break OuterLoop;
				}

				j++;
			}

		}

		return data;
	}

	// For sotring stuff
	private void heapify(String[] arr, int n, int i) {

		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l].compareTo(arr[largest]) > 0)
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arr[r].compareTo(arr[largest]) > 0)
			largest = r;

		// If largest is not root
		if (largest != i) {
			String swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);
		}
	}

	private static void insertionSort(String[] arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			String temp = arr[i];
			int j = i - 1;
			while (j >= left && arr[j].compareTo(temp) > 0) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
	}

	private static void mergeSort(String arr[], int l, int m, int r) {

		// Break array into left and right
		int len1 = m - l + 1, len2 = r - m;
		String[] left = new String[len1];
		String[] right = new String[len2];

		for (int x = 0; x < len1; x++)
			left[x] = arr[l + x];

		for (int x = 0; x < len2; x++)
			right[x] = arr[m + 1 + x];

		int i = 0, j = 0, k = l;

		// Compare and merge sub arrays into a large sub array
		while (i < len1 && j < len2) {
			if (left[i].compareTo(right[j]) <= 0) {
				arr[k] = left[i];
				i++;
			} else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements from left
		while (i < len1) {
			arr[k] = left[i];
			k++;
			i++;
		}

		// Copy remaining elements from right
		while (j < len2) {
			arr[k] = right[j];
			k++;
			j++;
		}
	}

	private class Entry {
		private String key;
		private String value;

		public Entry(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public Entry(final Entry other) {
			this(new String(other.key), new String(other.value));
		}
	}

	private class EntryData {
		private Entry entry;
		private int index_list;
		private int index_linked_list;

		public EntryData(Entry entry, int index_list, int index_linked_list) {
			this.entry = entry;
			this.index_list = index_list;
			this.index_linked_list = index_linked_list;
		}
	}
}
