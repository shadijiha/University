/**
 *
 */
package code;

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

		while (true) {
			Demo.sleep();

			double P = Math.random();

			// Produce
			if (!(P < Demo.Q)) {
				continue;
			}


			//System.out.println("Producing item");

			// See if buffer is full
			boolean isFull = true;
			int emptyIndex = -1;
			for (int i = 0; i < Demo.BUFFER.length; i++) {
				var d = Demo.BUFFER[i];
				if (d == null) {
					isFull = false;
					emptyIndex = i;
					break;
				}
			}

			if (isFull) {
				System.out.println("Buffer is full cannot produce");
				continue;
			}

			Demo.SEMAPHORE.Wait();
			// Otherwise
			Demo.BUFFER[emptyIndex] = Math.random();

			System.out.println("Produced:\t" + Demo.BUFFER[emptyIndex]);

			Demo.SEMAPHORE.Signal();

		}
	}
}

