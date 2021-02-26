package code;

import java.util.*;

public class Demo {

	public static double Q = 0.5;
	public static final Double[] BUFFER = new Double[10];
	public static final Semaphore SEMAPHORE = new Semaphore(1);

	public static final long SLEEP_TIME = 100;

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Q value > ");
		Q = scanner.nextDouble();

		Thread producer = new Thread(new Producer());
		Thread consumer = new Thread(new Consumer());

		producer.start();
		consumer.start();
//		var zipper = new Zipper("out.txt");
//		zipper.zip();
	}

	public synchronized static final void sleep() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
