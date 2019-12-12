/***
 * 
 * Shado Complex class library
 * 
 * */

package ShadoMath;

public class Complex {

	private double a = 0.0;
	private double b = 0.0;
	private double r = 0.0;
	private double phi = 0.0;

	public Complex(double _a, double _b)	{
		this.a = _a;
		this.b = _b;
		this.r = Math.sqrt(_a * _a + _b * _b);
		this.phi = (double)Math.atan( _b / _a);
	}
	
	public Complex(Complex other)	{
		this(other.getA(), other.getB());
	}
	
	public Complex()	{
		this(0.0, 0.0);
	}	
	
	public void fromPolar(double magnitude, double angle)	{
		this.r = magnitude;
		this.phi = angle;
		this.a = this.r * Math.cos(this.phi);
		this.a = this.r * Math.sin(this.phi);
	}

	public double mag()	{
		return Math.sqrt(this.a * this.a + this.b * this.b);
	}
	
	public Complex add(final Complex other)	{
		return new Complex(this.a + other.getA(), this.b + other.getB());
	}
	
	public Complex substract(final Complex other)	{
		return new Complex(this.a - other.getA(), this.b - other.getB());
	}
	
	public Complex multiply(final Complex other)	{
		Complex temp = new Complex();
		temp.fromPolar(this.r * other.getR(), this.phi + other.getPhi());
		return temp;
	}
	
	public Complex divide(final Complex other)	{
		Complex temp = new Complex();
		temp.fromPolar(this.r / other.getR(), this.phi - other.getPhi());
		return temp;
	}

	public Complex power(int exponant)	{
		Complex temp = new Complex();
		temp.fromPolar(Math.pow(this.r, exponant), this.phi * exponant);
		return temp;		
	}

	public Complex[] root(int exponant)	{
		
		Complex result[] = new Complex[exponant];
		
		double tempR = Math.pow(this.r, 1 / exponant);

		for (int i = 0; i < exponant; i++) {
			double tempPhi = (phi + 2 * Math.PI * i) / exponant;
			Complex temp = new Complex();
			temp.fromPolar(tempR, tempPhi);

			result[i] = temp;
		}
		
		return result;		
	}
	
	// Setters
	public void setA(double _a)	{
		this.a = _a;
		this.r = Math.sqrt(this.a * this.a + this.b * this.b); 
		this.phi = Math.atan( this.b / this.a);
	}
	
	public void setB(double _b)	{
		this.b = _b;
		this.r = Math.sqrt(this.a * this.a + this.b * this.b); 
		this.phi = Math.atan( this.b / this.a);
	}
	
	public void setR(double _r)	{
		this.r = _r;
		this.a = this.r * Math.cos(this.phi);
		this.b = this.r * Math.sin(this.phi);
	}
	
	public void setAngle(double angle)	{
		this.phi = angle;
		this.a = this.r * Math.cos(this.phi);
		this.b = this.r * Math.sin(this.phi);
	}
	
	// Getters
	public double getA()	{	return this.a;}
	public double getB()	{	return this.b;}
	public double getR()	{	return this.r;}
	public double getPhi()	{	return this.phi;}
	
	public String toString()	{
		return String.format("%1.2f + %1.2f i", this.a, this.b);
	}
	
	public String toStringPolar()	{
		return String.format("%1.2f * (cos(%f) + i * sin(%f))", this.r, this.phi, this.phi);
	}

}

