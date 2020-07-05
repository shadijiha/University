/**
 *
 */

package com.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Transaction {

	private Date time;
	private float amount;
	private Account from;
	private Account to;
	private Transaction[] transactions;

	private Transaction(Account from, Account to, float amount, Date time) {
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.time = time;
	}

	private Transaction(Account from, Account to, float amount) {
		this(from, to, amount, Calendar.getInstance().getTime());
	}

	/**
	 * Reads a file that contains transactions data. The file is organized in the following way.
	 * from_account_id, to_account_id, amount, date_as_long_number
	 * @param filename
	 */
	public static Transaction[] readFile(String filename) {

		List<Transaction> list = new ArrayList<>();

		try {

			Scanner scan = new Scanner(new FileInputStream(filename));
			while (scan.hasNextLine()) {

				var tokens = scan.nextLine().split(" ");

				var temp_from = Account.getAccountById(Long.parseLong(tokens[0]));
				var temp_to = Account.getAccountById(Long.parseLong(tokens[1]));
				var temp_amount = Float.parseFloat(tokens[2]);
				var temp_date = new Date(Long.parseLong(tokens[3]));

				var account = new Transaction(temp_from, temp_to, temp_amount, temp_date);
				list.add(account);
			}

			scan.close();

		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		// Convert the list to an array
		return list.toArray(new Transaction[0]);
	}

	public String toString() {
		return String.format("[from: %s, To: %s, %.2f \t %s]", from.toString(), to.toString(), amount, time.toString());
	}
}
