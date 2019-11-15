
public class Player {
	private String name;
	private int level;
	private int x;
	private int y;
	private int energy;
	
	public Player()	{
		this.name = "";
		this.energy = 10;
		this.level = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public Player(String _name)	{
		this.name = _name;
		this.energy = 10;
		this.level = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public Player(int _l, int _x, int _y)	{
		this.name = "";
		this.level = _l;
		this.x = _x;
		this.y = _y;
	}

	// Copy constructor and method
	public Player(Player objToCopy)	{
		
		if (objToCopy == null)	{
			StackTraceElement l = new Exception().getStackTrace()[0];
			System.err.println(String.format("The object to copy is not defined! @ %s.%s:%s", l.getClassName(), l.getMethodName(), l.getLineNumber()));
			
			System.exit(-1);
		}
		
		this.name = objToCopy.getName();
		this.level = objToCopy.getLevel();
		this.x = objToCopy.getX();
		this.y = objToCopy.getY();
		this.energy = objToCopy.getEnergy();
	}
	
	public Player copy()	{
		return new Player(this);
	}
	
	// Getters
	public String getName()		{	return this.name;	}
	public int getLevel()		{	return this.level;	}
	public int getX()			{	return this.x;		}
	public int getY()			{	return this.y;		}
	public int getEnergy()		{	return this.energy;	}
	
	
	// Setters
	public void setName(String value)	{	this.name = value;	}
	public void setLevel(int _level)	{	this.level = _level;}
	public void setX(int _x)			{	this.x = _x;		}
	public void setY(int _y)			{	this.y = _y;		}
	public void setEnergy(int value)	{	this.energy = value;}
	
	public void moveTo(Player p)	{
		this.level = p.getLevel();
		this.x = p.getX();
		this.y = p.getY();
	}

	public boolean isAtLocation(int __level, int __x, int __y)	{
		return (this.level == __level && this.x == __x && this.y == __y);
	}
	
	public boolean won(Board b)	{
		if (this.level >= b.getLevel() - 1 && this.x >= b.getSize() -1 && this.y >= b.getSize() - 1)
			return true;
		else
			return false;
	}
	
	public boolean equals(Player p )	{
		return (this.level == p.getLevel() && this.x == p.getX() && this.y == p.getY());
	}
	
	public String toString()	{
		return String.format("The player \"%s\" is at level %d, x: %d, y: %d and has %d energy", this.name, this.level, this.x, this.y, this.energy);
	}
}
