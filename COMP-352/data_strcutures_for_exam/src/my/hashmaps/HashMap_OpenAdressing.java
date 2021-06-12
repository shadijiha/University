package my.hashmaps;

import java.util.Arrays;

public class HashMap_OpenAdressing {
	private Integer[] data;
	private int numOfBuckets;
	private int size;
	private int collisions;

	private int cluster = 0;

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
		int firstHash = key % numOfBuckets;
		int secondHash = 0;//int secondHash = 11 - key % numOfBuckets;

		int cluster = 0;
		while (data[firstHash] != null) {
			secondHash++;
			firstHash += secondHash;
			firstHash %= numOfBuckets;
			collisions++;
			cluster++;
		}
		resgisterCluster(cluster);

		data[firstHash] = value;
		size++;
	}

	public void remove(int key) {

		int index = key % numOfBuckets;
		System.out.println("Removed " + data[index]);
		data[index] = null;
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
}
