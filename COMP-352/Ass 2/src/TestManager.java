import java.io.*;
import java.util.*;

/**
 *
 */

public class TestManager {

	private static Map<String, Long> sessions = new HashMap<>();

	public static void start(String session) {
		long milli = System.nanoTime();
		sessions.put(session, milli);
		//return milli;
	}

	public static void end(String session) {
		long milli = System.nanoTime();

		Long start = sessions.get(session);
		long delta = milli - start;

		sessions.replace(session, delta);

		//return milli;
	}

	public static long get(String session) {
		return sessions.get(session);
	}

	public static void store(String session, long ns) {
		sessions.put(session, ns);
	}

	/**
	 * Runs the function 100 times and returns the average time taken by the function to execute
	 *
	 * @param function the function to run
	 * @return Returns the average time taken by the function to execute
	 */
	public static double getAverageRuntimeOf(Runnable function) {

		ArrayList<Long> runs = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			function.run();
			long end = System.nanoTime();
			runs.add(end - start);
		}
		return runs.stream().mapToLong(e -> e).average().getAsDouble();
	}

	public static String listAll() {
		return sessions.toString();
	}

	public static void toFile() {

		try (PrintWriter writer = new PrintWriter(new FileOutputStream("testrun.txt"))) {

			writer.println("operation\t\ttime(ns)");

			for (var e : sessions.entrySet()) {
				writer.printf("%s\t\t%s\n", e.getKey(), e.getValue());
			}

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static int[] generateArray(int elements) {
		int[] array = new int[elements];

		for (int i = 0; i < elements; i++)
			array[i] = (int) random(0, 1000);

		return array;
	}

	private static double random(double min, double max) {
		return Math.random() * (max - min) + min;
	}
}
