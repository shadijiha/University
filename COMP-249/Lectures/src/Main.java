/***
 * Driver class
 */

public abstract class Main {
	public static void main(String[] args)	{

		Account myAccount[] = new Account[10];

		for (int i = 0; i < myAccount.length; i++)	{
			myAccount[i] = new Account("Tom", 123 + i, 100 + (i * 20));
			System.out.println("Balance of " + i + ": " + myAccount[i].getBalance());
		}

		System.out.println("Accounts:" + Account.getTotalAccounts());
	}
}
