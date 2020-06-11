/**
 *
 */

package driver;

import java.util.*;

public class CVR {

	private int threshold;
	private int key_length;

	private Map<String, String> list;

	public CVR() {
		list = new HashTable<>();
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
		list.put(key, value);
	}

	public void remove(String key) {
		list.remove(key);
	}

	public void getValues(String key) {
		list.get(key);
	}

	public Collection<String> allKeys() {
		var keys = new ArrayList<>(list.keySet());

		Collections.sort(keys);

		return keys;
	}

	public void test() {

		for (int i = 0; i < threshold; i++) {
			list.put(generate(), "car " + i);
		}
	}
}
