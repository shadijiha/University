/***
 * Lecture 1 : 10th January 2020
 *
 */

public class Account	{
	private String name;
	private int id;
	private int balance;
	private static int totalAccounts = 0;

	public Account(String _name, int _id, int _balance)	{
		name = _name;
		id = _id;
		balance = _balance;
		Account.totalAccounts++;
	}

	public Account(String _name)	{
		this(_name, 0, 0);
	}

	public Account(Account other)	{
		this(other.name, other.id, other.balance);
	}

	public Account()	{
		this(null, 0, 0);
	}

	public String toString()	{
		return this.name + " " + this.id + " " + this.balance;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// Getters
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getBalance() {
		return balance;
	}

	public static int getTotalAccounts()	{
		return Account.totalAccounts;
	}
}