package com;

public class Consumer extends ShadoProcess {
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
		System.out.println("Consumming item");

		wait(Main.SEMAPHORE);
		// See if buffer is empty
		boolean isEmpty = true;
		int itemIndex = -1;
		for (int i = 0; i < Main.BUFFER.length; i++) {
			var d = Main.BUFFER[i];
			if (d != null) {
				isEmpty = false;
				itemIndex = i;
				break;
			}
		}

		if (isEmpty) {
			System.out.println("Buffer is empty cannot consume");
			return;
		}

		// Otherwise
		System.out.println("Consummed:\t" + Main.BUFFER[itemIndex]);
		Main.BUFFER[itemIndex] = null;

		signal(Main.SEMAPHORE);
	}
}
