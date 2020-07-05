/**
 *
 */

package com.main;

public class Client {

	private Network network;
	private Thread[] threads;

	public Client() {
		threads = new Thread[2];

		threads[0] = new Thread(() -> thread1Task());
		threads[1] = new Thread(() -> thread2Task());

		for (Thread t : threads) {
			t.start();
		}
	}

	public void connect(Network network) {
		this.network = network;
	}

	public void get() {

		if (network.outputBufferEmpty()) {
			for (Thread t : threads)
				t.yield();
		}

		var transactions = network.receive();

		// Print to the console
		for (var t : transactions) {
			System.out.println(t);
		}
	}

	public void thread1Task() {

		while (true) {

		}

	}

	public void thread2Task() {

		while (true) {

		}
	}

}
