/**
 *
 */
package com.main.algorithms;

import com.main.*;

import java.util.*;

public class FCFS extends DispatcherAlgorithm {

	public FCFS(CPU[] cpus) {
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

		// Put the jobs to a queue first comes first served
		Queue<ShadoProcess> queue = new ArrayDeque<>(processes);

		while (queue.size() != 0) {

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

					System.out.println(cpu.toString() + " Running: \t" + process.toString());
				}

				// Check for IO requests
				cpu.checkIORequests();
			}
		}
	}
}
