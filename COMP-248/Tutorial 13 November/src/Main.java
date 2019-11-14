
import classes.Logger;

public class Main {

	public static final Logger DEBUG = new Logger();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Point	{
	
	private int x;
	private int y;
	
	// A
	public Point(int _x, int _y)	{
		this.x = _x;
		this.y = _y;
	}
	
	// B
	public int getX()	{	return this.x;}
	public int getY()	{	return this.y;}
	
	// C
	public void setX(int value)	{	this.x = value;}
	public void setY(int value)	{	this.y = value;}
	
	// D
	public boolean isEqual(Point other)	{
		return (this.x == other.x && this.y == other.y);
	}
	
	// E
	public Point reverse()	{
		return new Point(this.y, this.x);
	}
	
	public void moveBy(int amount)	{
		this.x += amount;
		this.y += amount;
	}
	
	@Override
	public String toString()	{
		return String.format("Coordinates of point are (%d, %d)", this.x, this.y);
	}
	
}
