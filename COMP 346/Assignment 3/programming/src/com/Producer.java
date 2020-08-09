/**
 *
 */
package com;

public class Producer extends ShadoProcess {

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {
		System.out.println("Producing item");

		wait(Main.SEMAPHORE);
		// See if buffer is full
		boolean isFull = true;
		int emptyIndex = -1;
		for (int i = 0; i < Main.BUFFER.length; i++) {
			var d = Main.BUFFER[i];
			if (d == null) {
				isFull = false;
				emptyIndex = i;
				break;
			}
		}

		if (isFull) {
			System.out.println("Buffer is full cannot produce");
			return;
		}

		// Otherwise
		Main.BUFFER[emptyIndex] = Math.random();

		System.out.println("Produced:\t" + Main.BUFFER[emptyIndex]);

		signal(Main.SEMAPHORE);
	}
}
