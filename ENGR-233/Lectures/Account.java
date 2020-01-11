
public class Account	{
	private String name;
	private int id;
	private int balance;

	public Account(String _name, int _id, int _balance)	{
		name = _name;
		id = _id;
		balance = _balance;
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
}