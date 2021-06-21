/**
 *
 */

public privileged aspect Modifier {

	private static int shape_id = 0;
	
	declare parents:Rectangle implements Shape;
	declare parents:Circle implements Shape;

	/******************** Question 4 ********************/
	public String Circle.getName()	{
		return "Circle";
	}
	
	public String Rectangle.getName()	{
		return "Rectangle";
	}
	
	/******************** Question 5 ********************/
	public String Circle.toString()	{
		return this.getName() + "(" + (int)this.radius + ")";
	}
	
	public String Rectangle.toString()	{
		return this.getName() + "(" + (int)this.width + ", " + (int)this.height + ")";
	}
	
	/******************** Question 6 ********************/	
	double around(Shape s) : this(s) && (execution(double Circle.getArea()) || execution(double Rectangle.getArea()))	{
		if (s.getClass() == Circle.class)	{
			Circle c = (Circle)s;
			return Math.PI * c.radius * c.radius;
		} else	{
			Rectangle r = (Rectangle)s;
			return r.width * r.height;
		}
	}
	
	/******************** Question 7 ********************/
	double around(Shape s): this(s) && ( execution(double *.getArea()) || execution(double *.getPerimeter() ))	{
		if (s.getClass() == Circle.class)	{
			
			Circle c = (Circle)s;
			 if ( c.radius < 0 )
				 return 0.0D;
			 else
				return proceed(s);
			 
		} else if (s.getClass() == Rectangle.class)	{
			
			Rectangle r = (Rectangle)s;			
			if ( r.width < 0 || r.height < 0 )
				 return 0.0D;
			 else
				return proceed(s);
			
		}
		return 0.0D;
	}
	
	/******************** Question 8 ********************/
	declare parents: Rectangle implements Identifiable;
	declare parents: Circle implements Identifiable;
	
	int Circle.id;
	int Rectangle.id;
	
	after(Circle c): this(c) && execution(Circle.new(..))	{
		c.id = shape_id++;
	}
	
	after(Rectangle c): this(c) && execution(Rectangle.new(..))	{
		c.id = shape_id++;
	}
	
	public int Circle.getId()	{
		return this.id;
	}
	
	public int Rectangle.getId()	{
		return this.id;
	}

}
