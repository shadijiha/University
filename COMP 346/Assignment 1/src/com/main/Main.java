/**
 *
 */

package com.main;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {
		// write your code here
		//generateAccounts();

		Account.readFile("accounts.txt");

		System.out.println(Arrays.toString(Transaction.readFile("transactions.txt")));
	}

	public static void generateTransactions() {

		Supplier<Account> random_account = () -> {
			return Account.allAccounts.get(randBetween(0, Account.allAccounts.size() - 1));
		};

		try {

			PrintWriter write = new PrintWriter(new FileOutputStream("transactions.txt"));

			for (int i = 0; i < 10; i++) {

				var from = random_account.get();
				var to = random_account.get();
				var amount = randBetween(from.getBalance() * 0.05, from.getBalance() * 0.20);
				var date = randomDate();

				write.printf("%d %d %.2f %d\n", from.getId(), to.getId(), amount, date);
			}

			write.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void generateAccounts() {

		try {

			String names[] = {"Jack", "Marc", "Jean-Philippe", "Bobo", "Lory", "Tony", "Felix"};

			PrintWriter write = new PrintWriter(new FileOutputStream("accounts.txt"));

			for (int i = 0; i < 10; i++) {

				var name = names[randBetween(0, names.length - 1)];
				var id = (long) (Math.random() * Long.MAX_VALUE);
				var balance = randBetween(0.0D, 100_000.0D);

				write.printf("%s %d %.2f\n", name, id, balance);
			}

			write.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static double randBetween(double start, double end) {
		return start + Math.random() * (end - start);
	}

	public static int randBetween(int start, int end) {
		return (int) (start + Math.random() * (end - start));
	}

	public static long randomDate() {
		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(2000, 2020);

		gc.set(gc.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		return gc.getTimeInMillis();
	}
}
