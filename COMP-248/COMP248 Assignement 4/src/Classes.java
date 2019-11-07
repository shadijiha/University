/***
 * 
 * This file contains the main classes used for "Nancy's 3D Warrior game"
 * 
 */

import java.util.Random;

class Dice  {

    private int die1 = 0;
    private int die2 = 0;

    Dice()  {}

    public int getDie1()    {   return this.die1;}
    public int getDie2()    {   return this.die2;}

    public int rollDice()  {

        Random random = new Random();

        this.die1 = random.nextInt(6) + 1;
        this.die2 = random.nextInt(6) + 1;

        return this.die1 + this.die2;
    }

    public boolean isDouble()   {
        return this.die1 == this.die2;
    }

    public String toString()    {
        return "Die1: " + this.die1 + " Die2: " + this.die2;
    }

}

class Board {

    private int[][][] board;
    public static final int MIN_LEVEL = 3;
    public static final int MIN_SIZE = 3;
    private int level;
    private int size;

    Board() {
        this.level = 3;
        this.size = 4;
        createBoard(this.level, this.size);
    }

    Board(int l, int x) {
        this.level = l;
        this.size = x;
        createBoard(l, x);
    }

    private void createBoard(int _level, int _size)  {
    	
    	this.board = new int[_level][_size][_size];

        for (int i = 0; i < _level; i++)    {
            for (int j = 0; j < _size; j++) {
                for (int k = 0; k < _size; k++)  {

                    int tempSum = i + j + k;

                    if (tempSum % 3 == 0 && tempSum != 0)
                        this.board[i][j][k] = -3;
                    else if (tempSum % 5 == 0 && tempSum != 0)
                        this.board[i][j][k] = -2;
                    else if (tempSum % 7 == 0 && tempSum != 0)
                        this.board[i][j][k] = 2;
                    else
                        this.board[i][j][k] = 0;                    
                }
            }
        }

    }

    public int getLevel()   {   return this.level;}
    public int getSize()    {   return this.size;}

    public int getEnergyAdj(int l, int x, int y)    {
        return this.board[l][x][y];
    }
    
    public String toString()    {

        String master = "";

        for (int i = 0; i < this.board.length; i++)    {
            master += "\nLevel " + i + "\n";
            master += "----------\n";

            for (int j = 0; j < this.board[i].length; j++) {
                for (int k = 0; k < this.board[i][j].length; k++)  {
                    master += this.board[i][j][k] + "\t";
                }
                master += "\n";
            }
        }
        
        master += "\n";

        return master;
    }

}


class Player	{
	
	private String name;
	private int level;
	private int x;
	private int y;
	private int energy;
	
	Player()	{
		this.name = "";
		this.energy = 10;
		this.level = 0;
		this.x = 0;
		this.y = 0;
	}
	
	Player(String _name)	{
		this.name = _name;
		this.energy = 10;
		this.level = 0;
		this.x = 0;
		this.y = 0;
	}
	
	Player(int _l, int _x, int _y)	{
		this.name = "";
		this.level = _l;
		this.x = _x;
		this.y = _y;
	}

	// Copy constructor and method
	Player(Player objToCopy)	{
		
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
	
	Player copy()	{
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
	
	public boolean won(Board b)	{
		if (this.level == b.getLevel() && this.x == b.getSize() -1 && this.y == b.getSize() - 1)
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
