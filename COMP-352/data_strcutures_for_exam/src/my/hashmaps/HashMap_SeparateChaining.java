/**
 *
 */
package my.hashmaps;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;

public class HashMap_SeparateChaining {
	private LinkedList<Integer>[] data;
	private int numOfBuckets;
	private int size;
	private int collisions;

	private Function<Integer, Integer> hashFunction;

	public HashMap_SeparateChaining(int size) {
		data = new LinkedList[size];
		numOfBuckets = 13;
		size = 0;
		hashFunction = (Integer key) -> {
			return key % 13;
		};
	}

	public HashMap_SeparateChaining() {
		this(13);
	}

	public void put(int key, int value) {
		int index = getBucketIndexOf(key);

		// Get the chain
		LinkedList<Integer> list = data[index];
		if (list == null) {
			data[index] = new LinkedList<>();
			list = data[index];
		}

		if (list.size() > 0)
			collisions++;

		list.add(value);
		size++;
	}

	public int size() {
		return size;
	}

	private int getBucketIndexOf(int key) {
		int hashCode = hashFunction.apply(key);
		int index = hashCode % numOfBuckets;
		return index;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int getNumberOfCollisions() {
		return collisions;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[\n");
		for (int i = 0; i < data.length; i++) {
			builder.append(i).append(":\t");

			if (data[i] != null)
				for (var x : data[i])
					builder.append(x).append(" --> ");

			builder.append("\n");
		}
		builder.append("]");

		return builder.toString();
	}

	public String getRawArrayAsString() {
		return Arrays.toString(data);

	}
}
