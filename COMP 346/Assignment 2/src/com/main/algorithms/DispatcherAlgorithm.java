/**
 *
 */
package com.main.algorithms;

import com.main.CPU;
import com.main.ShadoProcess;

import java.util.List;

public abstract class DispatcherAlgorithm {

	protected CPU[] cpus;

	public DispatcherAlgorithm(CPU[] cpus) {
		this.cpus = cpus;
	}

	/**
	 * Takes a collection of CPU process to handle and
	 * dispatch them depending on the algorithm used
	 * @param processes
	 */
	public abstract void handle(List<ShadoProcess> processes);

	protected CPU getAvailableCPU() {
		for (CPU cpu : cpus)
			if (cpu.isAvailable())
				return cpu;

		return null;
	}
}
