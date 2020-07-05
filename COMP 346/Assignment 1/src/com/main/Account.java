/**
 *
 */
package com.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Account {

	public static final byte WITHDRAW = 0;
	public static final byte DEPOSIT = 1;
	public static final byte TRANSFER = 2;

	protected static final List<Account> allAccounts = new ArrayList<>();

	private String holderName;
	private long id;
	private double balance;

	public Account(String holderName, long id, double balance) {
		this.id = id;
		this.holderName = holderName;
		this.balance = balance;

		allAccounts.add(this);
	}

	public Account(String holderName) {
		this(holderName, (long) (Math.random() * Long.MAX_VALUE), 0.0);
	}

	public static Account getAccountById(long id) {

		for (Account a : allAccounts)
			if (a.id == id)
				return a;
		return null;
	}

	/**
	 * Reads a file that contains account data. The file is organized in the following way.
	 * Name, id, balance
	 * @param filename The file to read
	 */
	public static Account[] readFile(String filename) {

		List<Account> list = new ArrayList<>();

		try {

			Scanner scan = new Scanner(new FileInputStream(filename));
			while (scan.hasNextLine()) {
				var account = new Account(scan.next(), scan.nextLong(), scan.nextDouble());
				list.add(account);
			}

			scan.close();

		} catch (NoSuchElementException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		// Convert the list to an array
		return list.toArray(new Account[0]);
	}

	public String getHolderName() {
		return holderName;
	}

	public void deposit(double money) {
		this.balance += money;
	}

	public void withdraw(double money) {
		this.balance -= money;
	}

	public void query(Account from, Account to, float amount, byte operation) {
		switch (operation) {
			case WITHDRAW:
				from.withdraw(amount);
				to.withdraw(amount);
				break;
			case DEPOSIT:
				from.deposit(amount);
				to.deposit(amount);
				break;
			case TRANSFER:
				from.withdraw(amount);
				to.deposit(amount);
				break;
		}
	}

	public double getBalance() {
		return balance;
	}

	public long getId() {
		return id;
	}

	public String toString() {
		return String.format("%s: %.2f", holderName, balance);
	}
}
