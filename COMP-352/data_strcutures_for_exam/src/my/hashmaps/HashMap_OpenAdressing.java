package my.hashmaps;

import java.util.*;

public class HashMap_OpenAdressing {
	private Integer[] data;
	private int numOfBuckets;
	private int size;
	private int collisions;

	private int cluster = 0;

	private FirstHashFunction firstHashFunc = (k, size) -> k % size;
	private SecondHashFunction secondHashFunc = (i, k, size) -> i + 1;

	public HashMap_OpenAdressing(int size) {
		data = new Integer[size];
		numOfBuckets = size;
		size = 0;
	}

	public HashMap_OpenAdressing() {
		this(19);
	}

	public void put(int key) {
		put(key, key);
	}

	public void put(int key, int value) {
		int firstHash = firstHashFunc.hash(key, numOfBuckets);
		int secondHash = 0;//int secondHash = 11 - key % numOfBuckets;

		int cluster = 0;
		while (data[firstHash] != null) {
			secondHash = secondHashFunc.hash(secondHash, key, numOfBuckets);
			firstHash += secondHash;
			firstHash %= numOfBuckets;
			collisions++;
			cluster++;
		}
		resgisterCluster(cluster);

		data[firstHash] = value;
		size++;
	}

	public void remove(Integer key) {

		for (int i = 0; i < numOfBuckets; i++) {
			if (data[i] != null && data[i].equals(key)) {
				data[i] = null;
				break;
			}
		}
	}

	private void resgisterCluster(int cluster) {
		if (cluster > this.cluster)
			this.cluster = cluster;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int getNumberOfCollisions() {
		return collisions;
	}

	public int getHighestCluster() {
		return cluster;
	}

	public String toString() {
		return getRawArrayAsString();
	}

	public String getRawArrayAsString() {
		return Arrays.toString(data);

	}

	public void setFirstHashFunc(FirstHashFunction func) {
		firstHashFunc = func;
	}

	public void setSecondHashFunc(SecondHashFunction func) {
		secondHashFunc = func;
	}

	public interface FirstHashFunction {
		public Integer hash(int key, int mapSize);
	}

	public interface SecondHashFunction {
		public Integer hash(int index, int key, int mapsize);
	}
}
