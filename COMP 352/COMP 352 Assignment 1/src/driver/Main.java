/**
 *
 */

package driver;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

	// This class is here to collect statistics
	public static class StatCollector {
		private volatile static List<StatCollector> stats = Collections.synchronizedList(new ArrayList<>());

		private long time;
		private int chars;

		public StatCollector(uint_t chars, long time) {
			this.chars = chars.intValue();
			this.time = time;

			stats.add(this);
		}

		public String toString() {
			return chars + ":\t" + time;
		}

		/**
		 * Returns an iterator over elements of type {@code T}.
		 *
		 * @return an Iterator.
		 */
		public static Iterator<StatCollector> iterator() {
			return stats.iterator();
		}
	}

	static int fn(int[] a, int n) {

		if (n == 0) return n;

		else return fn(a, n - 1) + a[n - 1];

	}

	public static void main(String[] args) throws Exception {

		// Redirect Output
		System.out.println(fn(new int[]{1, 3, 2, 4, 5}, 3));
		System.setOut(new PrintStream(new FileOutputStream("out.txt")));

		// Generate a random short string from 5 to 200 chars
		PrintWriter writer = new PrintWriter(new FileOutputStream("time_evaluation.txt"));
		writer.println("# letters\tTime (ns)");

		final String longStr = randomString(new uint32_t(200));

		IntStream.range(0, 100).parallel().forEach(i -> {

			uint_t chars = new uint32_t(i * 2);
			String shortStr = generateShortString(longStr, chars);

			var startTime = System.nanoTime();

			try {
				permutation(longStr, shortStr);
			} catch (RecusiveMethodFinished e) {
				// Ignored
			}

			var endTime = System.nanoTime();

			// Write the stats to a file
			writer.println(new StatCollector(chars, endTime - startTime));
			writer.flush();
		});


		writer.close();
	}

	/**
	 *
	 * @param longStr The long string to search inside
	 * @param Shortstr The short string to permutate and search for
	 * @throws RecusiveMethodFinished Throws an exception when it finds a match
	 */
	public static void permutation(String longStr, String Shortstr) throws RecusiveMethodFinished {
		permutation(longStr, "", Shortstr);
	}

	/**
	 * This function finds if any permutations of str is inside longStr
	 * @param longStr The long string to search
	 * @param prefix The accumulator of the permutation
	 * @param str The original Short string
	 * @throws RecusiveMethodFinished Throws an exception when it finds a match
	 */
	private static void permutation(String longStr, String prefix, String str) throws RecusiveMethodFinished {
		int n = str.length();

		if (n == 0) {

			int location = longStr.indexOf(prefix);
			if (location < 0) {
				System.out.println(prefix);
			} else {
				System.out.printf("Found 1 match: %s is in %s at location %d\n", prefix, longStr, location);
				throw new RecusiveMethodFinished();
			}
		} else {
			for (int i = 0; i < n; i++) {
				permutation(longStr, prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
			}
		}
	}

	/**
	 * Generates a random string with letters from [A~Z] all lower case
	 * @param length The length of the string
	 * @return Returns the generated random string
	 */
	public static String randomString(uint_t length) {

		String lettters = "abcdefghijklmnopqrstuvwxyz";

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length.intValue(); i++) {
			builder.append(
					lettters.charAt(
							random(0, lettters.length() - 1)
					)
			);
		}

		return builder.toString();
	}

	/**
	 * Generates a short string: 25% chance to get a random string, 75% chance to get a substring of LongString
	 * @param LongString A long string
	 * @param chars The number of charcters
	 * @return Returns a short string
	 */
	public static String generateShortString(String LongString, uint_t chars) {

		double branch = Math.random();
//		if (branch > 0.25) {
//			return LongString.substring(0, chars.intValue());
//		}

		// Otherwise Generate random string
		return randomString(chars);
	}

	/**
	 * Generates a random number between a specific range
	 * @param min The minimum number to get
	 * @param max The maximum number to get
	 * @return Returns a random number between a specific range
	 */
	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}
}
