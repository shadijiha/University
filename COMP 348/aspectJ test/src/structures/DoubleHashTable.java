package structures;

/* Class LinkedHashEntry */





/* Class HashTable */

import java.util.*;

public class DoubleHashTable extends AbstractMap {

	private int TABLE_SIZE;
	private int size;
	private HashEntry[] table;
	private int primeSize;
	private Integer prime;

	private class HashEntry {
		int key;
		int value;
		/* Constructor */

		HashEntry(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public String toString() {
			return key + "=" + value;
		}
	}

	/* Constructor */

	public DoubleHashTable(int ts) {
		super(ts);

		size = 0;

		TABLE_SIZE = ts;

		table = new HashEntry[TABLE_SIZE];

		for (int i = 0; i < TABLE_SIZE; i++)

			table[i] = null;

		primeSize = getPrime();

	}

	/* Function to get prime number less than table size for myhash2 function */

	public int getPrime() {

		for (int i = TABLE_SIZE - 1; i >= 1; i--) {

			int fact = 0;

			for (int j = 2; j <= (int) Math.sqrt(i); j++)

				if (i % j == 0)

					fact++;

			if (fact == 0)

				return i;

		}

		/* Return a prime number */

		return 3;

	}

	/* Function to get number of key-value pairs */

	public int size() {

		return size;

	}

	@Override
	protected int hash(int key) {
		return myhash1(key);
	}

	@Override
	protected int double_hash(int key) {
		return myhash2(key);
	}

	public boolean isEmpty() {

		return size == 0;

	}

	/* Function to clear hash table */

	public void makeEmpty() {

		size = 0;

		for (int i = 0; i < TABLE_SIZE; i++)

			table[i] = null;

	}

	/* Function to get value of a key */

	public Integer get(int key) {

		int hash1 = myhash1(key);

		int hash2 = myhash2(key);


		while (table[hash1] != null && table[hash1].key != key) {

			hash1 += hash2;

			hash1 %= TABLE_SIZE;

		}

		return table[hash1].value;

	}

	/* Function to insert a key value pair */

	public void put(int key, int value) {

		if (size == TABLE_SIZE) {

			System.out.println("Table full");

			return;

		}

		int hash1 = myhash1(key);

		int hash2 = myhash2(key);

		while (table[hash1] != null) {

			hash1 += hash2;

			hash1 %= TABLE_SIZE;

			collisions++;
		}

		table[hash1] = new HashEntry(key, value);

		size++;

	}

	/* Function to remove a key */

	public Integer remove(int key) {

		int hash1 = myhash1(key);

		int hash2 = myhash2(key);

		while (table[hash1] != null && table[hash1].key != key) {

			hash1 += hash2;

			hash1 %= TABLE_SIZE;

		}

		var val = table[hash1];
		table[hash1] = null;

		size--;

		return val.value;
	}

	@Override
	public boolean contains(int key) {
		throw new RuntimeException("Not implemented");
	}

	/* Function myhash which gives a hash value for a given string */

	private int myhash1(int x) {

		int hashVal = x;

		hashVal %= TABLE_SIZE;

		if (hashVal < 0)

			hashVal += TABLE_SIZE;

		return hashVal;

	}

	/* Function myhash function for double hashing */

	private int myhash2(int x) {

		int hashVal = x;

		hashVal %= TABLE_SIZE;

		if (hashVal < 0)

			hashVal += TABLE_SIZE;

		return primeSize - hashVal % primeSize;

	}

	/* Function to print hash table */

	public void printHashTable() {

		System.out.println("\nHash Table");

		for (int i = 0; i < TABLE_SIZE; i++)

			if (table[i] != null)

				System.out.println(table[i].key + " " + table[i].value);

	}

	public String toString() {
		return Arrays.toString(table);
	}
}
