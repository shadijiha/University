/**
 *
 */
package com.main;

public class CPU {

	private volatile ShadoProcess process;
	private volatile long timeAtStart;
	public volatile int clockCycle;
	private volatile int index;

	private long coreID;    // A unique if of the cpu

	/**
	 * Shoud maxumize CPU utilization
	 * Maximizr throughout
	 * Minimize turnaround time
	 * Minimize waiting time
	 * Minimize response time
	 */
	public CPU(int index) {
		coreID = (long) (Math.random() * Long.MAX_VALUE);
		clockCycle = 0;
		this.index = index;
	}

	/**
	 * Give the CPU a process to execute
	 * @param process The process to execute
	 */
	public void submit(ShadoProcess process) {
		this.process = process;
		//this.process.task().run();
		this.timeAtStart = System.currentTimeMillis();
	}

	/**
	 * Switch the running process with a new one
	 * NOTE: it also changes the burst time of the old process that will be returned
	 * @param newProcess The new process to execute
	 * @return Returns the old process
	 */
	public ShadoProcess contextSwitch(ShadoProcess newProcess) {
		long elapsedTime = System.currentTimeMillis() - timeAtStart;

		var old = this.process;

		// Remove the time that the process took in the cpu
		// e.g.: The CPU required 8 ms and it took 2 ms in the cpu
		// Then the new burst time is 6 ms
		this.process.setBurstTime(
				Math.max(0, this.process.burstTime() - elapsedTime
				));

		this.process = newProcess;

		return old;
	}

	/**
	 * This function checks if the process want to access the IO. If yes
	 * the process will be connected to the IO handler and will free the cpu
	 */
	public void checkIORequests() {
		if (process == null)
			return;

		for (long time : process.IORequests())
			if (time == clockCycle) {
				IOHandler.submit(process);    // Submit the task to the IO
				this.contextSwitch(null); // Free the cpu
			}
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

	/**
	 * @return Returns the process that is being handled
	 */
	public ShadoProcess getProcess() {
		return process;
	}

	/**
	 * @return Returns the time that the process has spent on the CPU
	 */
	public long timeTakenByProcess() {
		return System.currentTimeMillis() - timeAtStart;
	}

	@Override
	public String toString() {
		return "cpu" + index;
	}
}
