package com.main.algorithms;

import com.main.CPU;
import com.main.ShadoProcess;

import java.util.List;
import java.util.PriorityQueue;

public class SJF extends DispatcherAlgorithm {

	public SJF(CPU[] cpus) {
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

		// Transform the list into a priotiry queue depending
		// on the time of execution
		PriorityQueue<ShadoProcess> queue = new PriorityQueue<>(
				(ShadoProcess a, ShadoProcess b) -> {
					return Long.compare(a.burstTime(), b.burstTime());
				}
		);
		queue.addAll(processes);

		while (queue.size() != 0) {

			for (CPU cpu : cpus) {
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
				}
			}
		}
	}
}
