/**
 *
 */
package com.main.algorithms;

import java.util.*;

public abstract class Statistics {

	public static final byte WAIT_TIME = 0;
	public static final byte RESPONSE_TIME = 1;
	public static final byte TURN_AROUND_TIME = 2;

	private static long start;
	private static long end;
	private static long delta;

	private static List<Long> waitTime = new ArrayList<>();
	private static List<Long> responseTime = new ArrayList<>();
	private static List<Long> turnaroundTime = new ArrayList<>();

	public static void add(long time, byte type) {
		switch (type) {
			case WAIT_TIME:
				waitTime.add(time);
				break;
			case RESPONSE_TIME:
				responseTime.add(time);
				break;
			case TURN_AROUND_TIME:
				turnaroundTime.add(time);
				break;
		}
	}

	public static double average(byte type) {
		switch (type) {
			case WAIT_TIME:
				return sum(waitTime) / (double) waitTime.size();
			case RESPONSE_TIME:
				return sum(responseTime) / (double) responseTime.size();
			case TURN_AROUND_TIME:
				return sum(turnaroundTime) / (double) turnaroundTime.size();
		}
		return -1.0D;
	}

	private static long sum(List<Long> list) {
		return list.stream().mapToLong(e -> e).sum();
	}

	/**
	 * Starts the timer
	 * @return Returns the time at which the timer has started
	 */
	public static long startTimer() {
		start = System.currentTimeMillis();
		return start;
	}

	/**
	 * Stops the timer
	 * @return Returns the time at which the timer has stopped
	 */
	public static long stopTimer() {
		end = System.currentTimeMillis();
		delta = end - start;
		return end;
	}

	/**
	 * @return Returns the time the timer was active
	 */
	public static long delta() {
		return end - start;
	}
}
