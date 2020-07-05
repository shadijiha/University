/**
 *
 */

package com.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class Transaction {

	private Date time;
	private float amount;
	private Account from;
	private Account to;

	private Transaction(Account from, Account to, float amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.time = new Date();
	}

	public String readFile(String filename) {

		StringBuilder builder = new StringBuilder();

		try {
			Scanner scan = new Scanner(new FileInputStream(filename));

			while (scan.hasNextLine())
				builder.append(scan.nextLine()).append("\n");

			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public String toString() {
		return String.format("[from: %s, To: %s, %.2f \t %s]", from.toString(), to.toString(), amount, time.toString());
	}
}
