package com;

import java.io.PrintWriter;

public class Main {

	public static final double Q = 0.5;
	public static final Double[] BUFFER = new Double[10];
	public static final Semaphore SEMAPHORE = new Semaphore();

	public static void main(String[] args) throws Exception {

//		PrintStream stream = new PrintStream(new FileOutputStream("out.txt"));
//		System.setOut(stream);
//
//		// write your code here
//		ShadoProcess producer = new Producer();
//		ShadoProcess consumer = new Consumer();
//
//		int iteration = 0;
//		while (iteration++ < 100) {
//
//			double P = Math.random();
//			double C = Math.random();
//
//			// Produce
//			if (P < Q) {
//				producer.wakeup();
//			}
//
//			// Consume
//			if (C < 1 - Q) {
//				consumer.wakeup();
//			}
//
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//			System.out.flush();
//		}
//
//		System.out.close();

		PrintWriter writer = new PrintWriter("num.txt");

		for (int i = 0; i < 1_000_000; i++) {
			int number = (int) (Math.random() * 100);
			writer.println(number);
		}

		writer.close();

		var zipper = new Zipper("num.txt");
		zipper.zip();

	}
}
