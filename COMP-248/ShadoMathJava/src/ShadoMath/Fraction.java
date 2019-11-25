/***
 * 
 * Shado Fraction class library
 * 
 * */

package ShadoMath;

public class Fraction {
	
	private int numerator;
	private int denumerator;
	
	public Fraction(int numerator, int denumerator)	{
		this.numerator = numerator;
		this.denumerator = denumerator;
	}
	
	public Fraction(double number)	{
		
		double tempNum = number;
		int tries = 0;
		
		while (tempNum - (int)tempNum != 0.0)	{
			tempNum *= 10;
			tries++;
		}
		
		this.numerator = (int)tempNum;
		this.denumerator = (int)Math.pow(10, tries);
		
		this.simplify();
		
	}

	public Fraction(final Fraction other)	{
		this(other.numerator, other.denumerator);
	}
	
	public void simplify()	{
		for (int i = 10; i > 0; i--) {
			if (this.numerator % i == 0 && this.denumerator % i == 0) {
				this.numerator = this.numerator / i;
				this.denumerator = this.denumerator / i;
			}
		}
	}
	
	public void inverse()	{
		int temp = this.numerator;
		this.numerator = this.denumerator;
		this.denumerator = temp;
		this.simplify();
	}

	public void opposite()	{
		this.numerator = -this.numerator;
	}
	
	public Fraction add(final Fraction other)	{
		Fraction commonA = new Fraction(this.numerator * other.denumerator, this.denumerator * other.denumerator);
		Fraction commonB = new Fraction(other.numerator * this.denumerator, other.denumerator * this.denumerator);
	
		Fraction result = new Fraction(commonA.getNumerator() + commonB.getNumerator(), commonA.getDenumerator());
		result.simplify();
		return result;
	}
	
	public Fraction substract(final Fraction other)	{
		other.opposite();
		return this.add(other);
	}
	
	public Fraction multiply(final Fraction other)	{
		return new Fraction(this.numerator * other.getNumerator(), this.denumerator * other.getDenumerator());
	}
	
	public Fraction divide(final Fraction other)	{
		other.inverse();
		return this.multiply(other);
	}
	
	public Fraction power(int exponent)	{
		return new Fraction( (int)Math.pow(this.numerator, exponent), (int)Math.pow(this.denumerator, exponent) );
	}	
	
	// Getters
	public int getNumerator()	{	return this.numerator;}
	public int getDenumerator()	{	return this.denumerator;}
	
	// String and print
	public String toString()	{
		return String.format("%d / %d", this.numerator, this.denumerator);
	}

	public void print()	{
		System.out.println(this.toString());
	}
}
