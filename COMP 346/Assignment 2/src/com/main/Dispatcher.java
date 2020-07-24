/**
 *
 */
package com.main;

import com.main.algorithms.DispatcherAlgorithm;
import com.main.algorithms.FCFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dispatcher {
	private List<ShadoProcess> processes;
	private int completed;
	private final CPU[] cpus;

	private long latency; // Time takes to stop a process and start another process

	private DispatcherAlgorithm algorithm;

	public Dispatcher(CPU[] cpus, DispatcherAlgorithm mode) {
		this.algorithm = mode;
		processes = new ArrayList<>();
		this.cpus = cpus;
	}

	public Dispatcher(CPU[] cpus) {
		this(cpus, new FCFS(cpus));
	}

	public void start() {
		algorithm.handle(processes);
	}

	public void submit(ShadoProcess[] process) {
		processes.addAll(Arrays.asList(process));
	}

	public void use(DispatcherAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
}
