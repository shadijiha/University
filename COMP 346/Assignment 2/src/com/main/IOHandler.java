/**
 *
 */
package com.main;

import java.util.*;

public abstract class IOHandler {

	private static final Queue<ShadoProcess> IOQueue = new ArrayDeque<>();
	private static volatile boolean running = false;

	private static final int IO_TIME = 2;

	public static void submit(ShadoProcess p) {
		IOQueue.add(p);

		if (!running) {
			running = true;
			run();
		}
	}

	private static void run() {

		new Thread(new Runnable() {
			@Override
			public void run() {

				int time_took_by_process = 0;    // If this number becomes 2 then then the process has finished with the IO
				ShadoProcess process = IOQueue.poll();
				while (running) {

					// If task has finished with IO
					if (time_took_by_process == IO_TIME) {

						// Get the next task from the queue
						process = IOQueue.poll();

						// If queue if empty
						if (process == null) {
							running = false;
							break;
						}
						time_took_by_process = 0;

					} else {
						// Run the task
						time_took_by_process++;
					}

					System.out.println("On IO:\t" + process);
				}
			}
		}).start();

	}
}
