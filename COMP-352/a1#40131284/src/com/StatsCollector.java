/**
 * This class collects stats about algorithm performance then print them to file
 */
package com;

import java.io.*;

public class StatsCollector {

	public static void record(String name, int n, long timeElapsed) {

		try (PrintWriter writer = new PrintWriter(new FileOutputStream(name + ".csv", true))) {
			writer.printf("%d, %d\n", n, timeElapsed);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
