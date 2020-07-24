package com.main.algorithms;

import com.main.CPU;
import com.main.ShadoProcess;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

		while (queue.size() != 0) {

			// Reinitialize the queue with the updated remaining times
			queue = new PriorityQueue<>(processComparator);

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

	private Comparator<ShadoProcess> processComparator = new Comparator<ShadoProcess>() {
		@Override
		public int compare(ShadoProcess a, ShadoProcess b) {
			return Long.compare(a.burstTime(), b.burstTime());
		}
	};
}
