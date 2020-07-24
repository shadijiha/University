/**
 * Immutable class that holdes a task to be executed on the CPU
 */
package com.main;

import java.util.ArrayList;
import java.util.List;

public final class ShadoProcess {

	public static final byte NON_PREEMPTIVE = 0;
	public static final byte PREEMPTIVE = 1;

	//private final Runnable task;    // Holds the task to execute
	private final String id;            // Holds the id of the task
	//private final byte type;        // Holds the type of the task
	private long burstTime;    // Theoretical Time it takes to finish
	private final long arrivalTime;    // Arrival time
	private final List<Long> IORequests;

	public ShadoProcess(String id, long arrivalTime, long burstTime) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		IORequests = new ArrayList<>();
	}

	/**
	 * Adds an IO request for this process
	 *
	 * @param request The time of the request
	 */
	public final void addIORequest(long request) {
		IORequests.add(request);
	}

	public final void setBurstTime(long time) {
		this.burstTime = time;
	}

	/**
	 * @return Returns the id of the process
	 */
	public final String id() {
		return id;
	}

	/**
	 * @return Returns the time that the process takes to complete
	 */
	public final long burstTime() {
		return burstTime;
	}

	/**
	 * @return Returns the time when the process arrived
	 */
	public final long arrivalTime() {
		return arrivalTime;
	}

	/**
	 * @return Returns a deep copy of the process IO request times
	 */
	public final List<Long> IORequests() {
		return new ArrayList<>(IORequests);
	}

	/**
	 * Returns a string representation of the object. In general, the
	 * {@code toString} method returns a string that
	 * "textually represents" this object. The result should
	 * be a concise but informative representation that is easy for a
	 * person to read.
	 * It is recommended that all subclasses override this method.
	 * <p>
	 * The {@code toString} method for class {@code Object}
	 * returns a string consisting of the name of the class of which the
	 * object is an instance, the at-sign character `{@code @}', and
	 * the unsigned hexadecimal representation of the hash code of the
	 * object. In other words, this method returns a string equal to the
	 * value of:
	 * <blockquote>
	 * <pre>
	 * getClass().getName() + '@' + Integer.toHexString(hashCode())
	 * </pre></blockquote>
	 *
	 * @return a string representation of the object.
	 */
	@Override
	public String toString() {
		return String.format("Process:\t%s %d %d %s", id, arrivalTime, burstTime, IORequests.toString());
	}
}
