/**
 *
 */

package driver;

import java.util.*;

public class CVR {

	public static class Accident {
		private Date date;
		private String details;

		public Accident(Date date, String details) {
			this.date = date;
			this.details = details;
		}

		public Accident() {
			GregorianCalendar gc = new GregorianCalendar();

			int year = randBetween(1900, 2010);
			gc.set(gc.YEAR, year);
			int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
			gc.set(gc.DAY_OF_YEAR, dayOfYear);

			this.date = gc.getGregorianChange();
			this.details = "Details test";
		}

		private static int randBetween(int start, int end) {
			return start + (int) Math.round(Math.random() * (end - start));
		}

		public static Accident random() {
			return new Accident();
		}

		public String toString() {
			return date.toString() + " " + details;
		}
	}

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
		list[index].addLast(new Entry(key, Accident.random()));
		size++;

	}

	public Accident remove(String key) {

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

		return removed.value.get(0);
	}

	/**
	 * Finds all the values that have a given key
	 * O(n)
	 * @param key Key to search
	 * @return Returns an array of values of the given key
	 */
	public Accident[] getValues(String key) {

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
		Accident[] values = new Accident[query.size()];

		int index = 0;
		for (Entry e : query) {
			values[index] = e.value.get(0);
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

			// Sort individual subarrays of size RUN
			final int RUN = 32;
			int n = result.length;
			for (int i = 0; i < n; i += RUN) {
				insertionSort(result, i, Math.min((i + 31), (n - 1)));
			}

			// start merging from size RUN (or 32). It will merge
			// to form size 64, then 128, 256 and so on ....
			for (int size = RUN; size < n; size = 2 * size) {

				// pick starting point of left sub array. We
				// are going to merge arr[left..left+size-1]
				// and arr[left+size, left+2*size-1]
				// After every merge, we increase left by 2*size
				for (int left = 0; left < n; left += 2 * size) {

					// find ending point of left sub array
					// mid+1 is starting point of right sub array
					int mid = left + size - 1;
					int right = Math.min((left + 2 * size - 1), (n - 1));

					// merge sub array arr[left.....mid] &
					// arr[mid+1....right]
					mergeSort(result);
				}
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

	public Accident[] prevAccids(String key) {

		// Get the list of that key
		List<Accident> accidents = new ArrayList<>();
		for (LinkedList<Entry> l : list) {
			if (l == null)
				continue;
			for (Entry e : l) {
				if (e.key.equals(key))
					accidents.addAll(e.value);
			}
		}

		return accidents.toArray(new Accident[0]);
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

	private static void mergeSort(String arr[]) {

		if (arr.length < 2)
			return;

		// original array is broken in two parts
		// left and right array
		var middle = arr.length / 2;
		String[] left = new String[middle];
		for (int i = 0; i < middle; i++)
			left[i] = arr[i];

		String[] right = new String[arr.length - middle];
		for (int i = middle; i < arr.length; i++)
			right[i - middle] = arr[i];

		// Sort each half
		mergeSort(left);
		mergeSort(right);

		// Merge the result
		merge_helper(left, right, arr);
	}

	private static void merge_helper(String[] left, String[] right, String[] result) {
		int i = 0, j = 0, k = 0;

		while (i < left.length && j < right.length) {
			if (left[i].compareTo(right[j]) <= 0)
				result[k++] = left[i++];
			else
				result[k++] = right[j++];
		}

		// Copy the remaining items
		while (i < left.length)
			result[k++] = left[i++];

		while (j < right.length)
			result[k++] = right[j++];
	}

	private class Entry {
		private String key;
		private List<Accident> value;

		public Entry(String key, Accident accident) {
			this.key = key;
			this.value = new ArrayList<>();
			this.value.add(accident);
		}

		public Entry(final Entry other) {
			this.key = new String(other.key);
			this.value = new ArrayList<>(other.value);
		}

		public void add(Accident a) {
			value.add(a);
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
