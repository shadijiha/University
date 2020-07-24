/**
 *
 */
package com.main;

public class CPU {

	private ShadoProcess process;
	private long timeAtStart;

	private long coreID;    // A unique if of the cpu

	/**
	 * Shoud maxumize CPU utilization
	 * Maximizr throughout
	 * Minimize turnaround time
	 * Minimize waiting time
	 * Minimize response time
	 */
	public CPU() {
		coreID = (long) (Math.random() * Long.MAX_VALUE);
	}

	/**
	 * Give the CPU a process to execute
	 * @param task The process to execute
	 */
	public void submit(ShadoProcess process) {
		this.process = process;
		//this.process.task().run();
		this.timeAtStart = System.currentTimeMillis();
	}

	/**
	 * Switch the running process with a new one
	 * @param newProcess The new process to execute
	 */
	public void contextSwitch(ShadoProcess newProcess) {
		long elapsedTime = System.currentTimeMillis() - timeAtStart;

		// Remove the time that the process took in the cpu
		// e.g.: The CPU required 8 ms and it took 2 ms in the cpu
		// Then the new burst time is 6 ms
		this.process.setBurstTime(
				Math.max(0, this.process.burstTime() - elapsedTime
				));

		this.process = newProcess;
	}

	/**
	 * a processor is available if the elapsed time since the beginning
	 * of the task is less than or equal to the THEORETICAL time of the process
	 * @return If the processor is available
	 */
	public boolean isAvailable() {
		long elapsedTime = System.currentTimeMillis() - timeAtStart;
		return process == null || elapsedTime >= process.burstTime();
	}
}
