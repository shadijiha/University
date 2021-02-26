package code;


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

		while (true) {

			Demo.sleep();

			double C = Math.random();

			// Consume
			if (!(C < 1 - Demo.Q)) {
				continue;
			}


			//System.out.println("Consumming item");

			// See if buffer is empty
			boolean isEmpty = true;
			int itemIndex = -1;
			for (int i = 0; i < Demo.BUFFER.length; i++) {
				var d = Demo.BUFFER[i];
				if (d != null) {
					isEmpty = false;
					itemIndex = i;
					break;
				}
			}

			if (isEmpty) {
				System.out.println("Buffer is empty cannot consume");
				continue;
			}

			Demo.SEMAPHORE.Wait();
			// Otherwise
			System.out.println("Consummed:\t" + Demo.BUFFER[itemIndex]);
			Demo.BUFFER[itemIndex] = null;

			Demo.SEMAPHORE.Signal();
		}
	}
}

