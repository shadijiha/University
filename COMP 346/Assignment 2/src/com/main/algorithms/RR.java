package com.main.algorithms;

import com.main.*;

import java.util.*;

public class RR extends DispatcherAlgorithm {

	private int q;

	public RR(CPU[] cpus, int q) {
		super(cpus);
		this.q = q;
	}

	/**
	 * Takes a collection of CPU process to handle and
	 * dispatch them depending on the algorithm used
	 *
	 * @param processes
	 */
	@Override
	public void handle(List<ShadoProcess> processes) {

		// Put the processes in a queue
		Queue<ShadoProcess> queue = new ArrayDeque<>(processes);

		int clockCycle = 0;
		while (queue.size() != 0) {

			// Give a process for each CPU
			for (CPU cpu : cpus) {

				// Update the cycle for the cpu
				cpu.clockCycle++;

				// IF the cpu is available
				// Give it a task
				if (cpu.isAvailable()) {

					var process = queue.poll();

					// If the queue is empty then continue
					if (process == null)
						continue;

					// Excute the process
					cpu.submit(process);

					System.out.println("Running: \t" + process.toString());

				} else {

					// IF the cpu is not available then check if the process
					// Has exceeded the Given Q
					if (cpu.timeTakenByProcess() > this.q) {

						var nextProcess = queue.poll();
						if (nextProcess == null)
							continue;

						// Switch the old process with the new one
						// And ADD THE OLD to the queue
						var oldProcess = cpu.contextSwitch(nextProcess);
						queue.add(oldProcess);
					}

				}

				// Check for IO requests
				cpu.checkIORequests();
			}

			clockCycle++;
		}
	}
}
