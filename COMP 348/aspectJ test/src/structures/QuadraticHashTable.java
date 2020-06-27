package structures;

public class QuadraticHashTable extends AbstractMap {

	private int currentSize, maxSize;
	private Integer[] keys;
	private Integer[] vals;

	/**
	 * Constructor
	 **/

	public QuadraticHashTable(int capacity) {
		super(capacity);
		currentSize = 0;
		maxSize = capacity;
		keys = new Integer[maxSize];
		vals = new Integer[maxSize];
	}


	/**
	 * Function to clear hash table
	 **/

	public void makeEmpty() {
		currentSize = 0;
		keys = new Integer[maxSize];
		vals = new Integer[maxSize];
	}

	/**
	 * Function to get size of hash table
	 **/
	public int size() {
		return currentSize;
	}

	/**
	 * Function to check if hash table is full
	 **/

	public boolean isFull() {
		return currentSize == maxSize;
	}

	/**
	 * Function to check if hash table is empty
	 **/
	public boolean isEmpty() {
		return size() == 0;
	}


	/**
	 * Fucntion to check if hash table contains a key
	 **/

	public boolean contains(int key) {
		return get(key) != null;
	}


	/**
	 * Functiont to get hash code of a given key
	 **/

	protected int hash(int key) {
		return (3 * key + 5) % maxSize;
	}

	@Override
	protected int double_hash(int key) {
		return 0;
	}

	@Override
	protected void resize() {
		maxSize *= 2;
		keys = new Integer[maxSize];
		vals = new Integer[maxSize];
	}

	/**
	 * Function to insert key-value pair
	 **/
	public void put(int key, int val) {

		int tmp = hash(key);

		int i = tmp, h = 1;

		do {

			if (keys[i] == null) {

				keys[i] = key;

				vals[i] = val;

				currentSize++;

				return;

			}

			if (keys[i].equals(key)) {

				vals[i] = val;

				return;

			}

			i = (i + h * h++) % maxSize;

			collisions++;

		} while (i != tmp);

	}


	/**
	 * Function to get value for a given key
	 **/

	public Integer get(int key) {

		int i = hash(key), h = 1;

		while (keys[i] != null) {

			if (keys[i].equals(key))

				return vals[i];

			i = (i + h * h++) % maxSize;

			System.out.println("i " + i);

		}

		return null;

	}


	/**
	 * Function to remove key and its value
	 **/

	public Integer remove(int key) {

		if (!contains(key))
			return null;


		/** find position key and delete **/

		int i = hash(key), h = 1;

		while (key != keys[i])

			i = (i + h * h++) % maxSize;

		var val = vals[i];
		keys[i] = vals[i] = null;


		/** rehash all keys **/

		for (i = (i + h * h++) % maxSize; keys[i] != null; i = (i + h * h++) % maxSize) {

			int tmp1 = keys[i], tmp2 = vals[i];

			keys[i] = vals[i] = null;

			currentSize--;

			put(tmp1, tmp2);

		}

		currentSize--;

		return val;
	}


	/**
	 * Function to print HashTable
	 **/

	public void printHashTable() {

		System.out.println("\nHash Table: ");

		for (int i = 0; i < maxSize; i++)

			if (keys[i] != null)

				System.out.println(keys[i] + " " + vals[i]);

		System.out.println();

	}

	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("[");


		for (int i = 0; i < maxSize; i++) {
			builder.append(keys[i]).append("=").append(vals[i]);

			if (i != size() - 1)
				builder.append(", ");
		}
		builder.append("]");

		return builder.toString();
	}

}
