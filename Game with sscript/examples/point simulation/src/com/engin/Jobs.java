/**
 *
 */
package com.engin;

import com.engin.interfaces.*;
import com.engin.logger.*;

import java.util.*;

public abstract class Jobs {

	private static final Jobs singleton = new Jobs() {
	};

	private final Queue<IShouldQueue> jobs;
	private final Thread thread;
	private volatile boolean busy;

	private Jobs() {
		jobs = new ArrayDeque<>();

		// Start the job thread
		thread = new Thread(() -> {
			while (true) {

				IShouldQueue job = jobs.poll();
				if (job != null) {
					busy = true;

					//Log.info("Executing job %s", job.name());

					long time1 = System.currentTimeMillis();

					try {
						job.handle();
					} catch (Throwable e) {
						Log.error("An error has occurred while executing %s.\t\t%s", job.name(), e.getMessage());
					}

					long time2 = System.currentTimeMillis();

					Log.info("%s job finished. took: %d ms", job.name(), time2 - time1);

					busy = false;
				}

				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					Log.error(e);
				}
			}
		});
		thread.start();
	}

	/**
	 * Queues a job to be executed and returns its position
	 * @param job The job the queue
	 * @return Returns the position
	 */
	public static int add(IShouldQueue job) {
		singleton.jobs.add(job);
		return singleton.jobs.size();
	}

	/**
	 * @return Returns true of there's a job currently ongoing
	 */
	public static boolean isBusy() {
		return singleton.busy;
	}
}
