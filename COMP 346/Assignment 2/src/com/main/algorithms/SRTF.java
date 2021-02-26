package com.main.algorithms;

import com.main.*;

import java.util.*;

public class SRTF extends DispatcherAlgorithm {

	public SRTF(CPU[] cpus) {
		super(cpus);
	}

	/**
	 * Takes a collection of CPU process to handle and
	 * dispatch them depending on the algorithm used
	 *
	 * @param processes
	 */
	@Override
	public void handle(List<ShadoProcess> processes) {

		PriorityQueue<ShadoProcess> queue = new PriorityQueue<>(processComparator);

		// Because each iteration is 1 time stamp
		// Remove 1 time stamp from each process and reevaluate
		while (queue.size() != 0) {

			// Reinitialize the queue with the updated remaining times
			queue = new PriorityQueue<>(processComparator);

			List<ShadoProcess> processesSubmited = new ArrayList<>();

			for (CPU cpu : cpus) {

				// Update the cycle for the cpu
				cpu.clockCycle++;

				// IF the cpu is available
				// Give it a task
				var process = queue.poll();

				// If the queue is empty then continue
				if (process == null)
					continue;

				// Otherwise if the cpu is not busy
				// Then submit the task
				// Otherwise, context swap
				if (cpu.isAvailable()) {
					cpu.submit(process);
					System.out.println("Running: \t" + process.toString());
				} else {
					System.out.println("Swapped: " + cpu.getProcess().id() + " with " + process.id());
					cpu.contextSwitch(process);
				}

				// Check for IO requests
				cpu.checkIORequests();

				processesSubmited.add(process);
			}

			// Remove 1 time stamp from all the process that has started
			// This is important for the evaluation next iteration
			for (ShadoProcess process : processesSubmited)
				process.setBurstTime(process.burstTime() - 1);
		}
	}

	private Comparator<ShadoProcess> processComparator = new Comparator<ShadoProcess>() {
		@Override
		public int compare(ShadoProcess a, ShadoProcess b) {
			return Long.compare(a.burstTime(), b.burstTime());
		}
	};
}
