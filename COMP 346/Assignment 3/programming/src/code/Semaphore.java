/**
 *
 */

package code;

public class Semaphore {

	private int value;

	public Semaphore(int value) {
		this.value = value;
	}

	public Semaphore() {
		this(0);
	}

	public synchronized void Wait() {

		while (this.value <= 0) {
			System.out.println("\tWaiting...");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.value--;
	}

	public synchronized void Signal() {
		++this.value;
		System.out.println("Waking up...");
		notify();
	}
}
