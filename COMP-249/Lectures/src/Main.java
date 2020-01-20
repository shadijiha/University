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
		
		
		int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		
		for (int i = 0; i < test.length / 2; i++)	{
			
			int temp = test[i];
			test[i] = test[test.length - i - 1];
			test[test.length - i - 1] = temp;
			
		}
		

		System.out.println("Accounts:" + Account.getTotalAccounts());
	}
}
