package com;

public abstract class ShadoProcess implements Runnable {

	private Thread thread;

	public ShadoProcess() {
		thread = new Thread(this);
	}

	public final void wait(Semaphore s) {
		s.value--;
		if (s.value < 0) {
			s.processes.add(this);
			block(s);
		}
	}

	public final void signal(Semaphore s) {
		s.value++;
		if (s.value <= 0) {
			s.processes.remove(this);
			wakeup();
		}
	}

	private final void block(Semaphore s) {
		while (s.value <= 0) {
			System.out.println("Busy waiting...");
		}    // Block
	}

	public final void wakeup() {
		thread.run();
	}

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
	public abstract void run();
}
