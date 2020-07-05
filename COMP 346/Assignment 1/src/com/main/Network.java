/**
 *
 */

package com.main;

public class Network implements Runnable {

	private Thread thread;
	private Transaction[] inComingPacket;
	private Transaction[] outGoingPacket;

	private volatile boolean running;

	public Network() {
		thread = new Thread(this);
		thread.start();

		inComingPacket = new Transaction[10];
		outGoingPacket = new Transaction[10];

		running = true;
	}

	@Override
	public void run() {

		while (running) {

		}

	}

	public void send(Transaction transaction) {

//		if (inputBufferFull())
//			thread.yield();

	}

	public Transaction[] receive() {

//		if (outputBufferEmpty()) {
//			thread.yield();
//		}

		return outGoingPacket;
	}

	public boolean inputBufferFull() {
		for (Transaction t : inComingPacket)
			if (t == null)
				return false;
		return true;
	}

	public boolean outputBufferEmpty() {
		for (var t : outGoingPacket)
			if (t != null)
				return false;
		return true;
	}

	private void pushToOutput(Transaction t) {

		int pointer = 0;
		for (int i = 0; i < outGoingPacket.length; i++)
			if (outGoingPacket[i] == null)
				pointer = i;

		outGoingPacket[pointer] = t;
	}

	public void shutdown() {
		running = false;
	}
}
