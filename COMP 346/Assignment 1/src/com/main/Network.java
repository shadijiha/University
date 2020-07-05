/**
 *
 */

package com.main;

public class Network implements Runnable {

	private Thread thread;
	private Transaction[] inComingPacket;
	private Transaction[] outGoingPacket;

	public Network() {
		thread = new Thread(this);
		thread.start();

		inComingPacket = new Transaction[10];
		outGoingPacket = new Transaction[10];
	}

	@Override
	public void run() {

		while (true) {

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
}
