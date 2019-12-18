using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    public class Fraction
    {
		private int numerator;
		private int denumerator;

		public Fraction(int numerator, int denumerator)
		{
			this.numerator = numerator;
			this.denumerator = denumerator;
		}

		public Fraction(double number)
		{

			double tempNum = number;
			int tries = 0;

			while (tempNum - (int)tempNum != 0.0)
			{
				tempNum *= 10;
				tries++;
			}

			this.numerator = (int)tempNum;
			this.denumerator = (int)Math.Pow(10, tries);

			this.simplify();

		}

		public Fraction(ref Fraction other)
		{
			this.numerator = other.numerator;
			this.denumerator = other.denumerator;
		}

		public void simplify()
		{
			for (int i = 10; i > 0; i--)
			{
				if (this.numerator % i == 0 && this.denumerator % i == 0)
				{
					this.numerator = this.numerator / i;
					this.denumerator = this.denumerator / i;
				}
			}
		}

		public void inverse()
		{
			int temp = this.numerator;
			this.numerator = this.denumerator;
			this.denumerator = temp;
			this.simplify();
		}

		public void opposite()
		{
			this.numerator = -1 * this.numerator;
		}

		public Fraction add(ref Fraction other)
		{
			Fraction commonA = new Fraction(this.numerator * other.denumerator, this.denumerator * other.denumerator);
			Fraction commonB = new Fraction(other.numerator * this.denumerator, other.denumerator * this.denumerator);

			Fraction result = new Fraction(commonA.getNumerator() + commonB.getNumerator(), commonA.getDenumerator());
			result.simplify();
			return result;
		}

		public Fraction substract(ref Fraction other)
		{
			other.opposite();
			return this.add(ref other);
		}

		public Fraction multiply(ref Fraction other)
		{
			return new Fraction(this.numerator * other.getNumerator(), this.denumerator * other.getDenumerator());
		}

		public Fraction divide(ref Fraction other)
		{
			other.inverse();
			return this.multiply(ref other);
		}

		public Fraction power(int exponent)
		{
			return new Fraction((int)Math.Pow(this.numerator, exponent), (int)Math.Pow(this.denumerator, exponent));
		}

		// Operator overloading
		public static Fraction operator +(Fraction a, Fraction b) {
			return a.add(ref b);
		}
		public static Fraction operator -(Fraction a, Fraction b)
		{
			return a.substract(ref b);
		}
		public static Fraction operator *(Fraction a, Fraction b)
		{
			return a.multiply(ref b);
		}
		public static Fraction operator /(Fraction a, Fraction b)
		{
			return a.divide(ref b);
		}
		public static bool operator ==(Fraction a, Fraction b)
		{
			return (a.getNumerator() / a.getDenumerator()) == (b.getNumerator() / b.getDenumerator());
		}
		public static bool operator !=(Fraction a, Fraction b)
		{
			return !(a == b);
		}

		public static explicit operator double(Fraction frac) {
			return frac.getNumerator() / frac.getDenumerator();
		}
		public static explicit operator float(Fraction frac)
		{
			return (float)frac.getNumerator() / (float)frac.getDenumerator();
		}
		public static explicit operator Vector(Fraction frac) {
			return new Vector(frac.getNumerator(), frac.getDenumerator());
		}

		// Getters
		public int getNumerator() { return this.numerator; }
		public int getDenumerator() { return this.denumerator; }

		// String and print
		public string toString()
		{
			return string.Format("{0} / {1}", this.numerator, this.denumerator);
		}

		public void print()
		{
			Console.WriteLine(this.toString());
		}
	}
}
