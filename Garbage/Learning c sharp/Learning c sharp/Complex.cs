using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
	public class Complex
	{
		private double a = 0.0;
		private double b = 0.0;
		private double r = 0.0;
		private double phi = 0.0;

		public Complex(double _a, double _b)
		{
			this.a = _a;
			this.b = _b;
			this.r = Math.Sqrt(_a * _a + _b * _b);
			this.phi = (double)Math.Atan(_b / _a);
		}

		public Complex(Complex other)
		{
			this.a = other.getA();
			this.b = other.getB();
			this.r = Math.Sqrt(this.a * this.a + this.b * this.b);
			this.phi = (double)Math.Atan(this.b / this.a);
		}

		public Complex()
		{
			this.a = 0.0;
			this.b = 0.0;
			this.r = Math.Sqrt(this.a * this.a + this.b * this.b);
			this.phi = (double)Math.Atan(this.b / this.a);
		}

		public void fromPolar(double magnitude, double angle)
		{
			this.r = magnitude;
			this.phi = angle;
			this.a = this.r * Math.Cos(this.phi);
			this.a = this.r * Math.Sin(this.phi);
		}

		public double mag()
		{
			return Math.Sqrt(this.a * this.a + this.b * this.b);
		}

		public Complex add(ref Complex other)
		{
			return new Complex(this.a + other.getA(), this.b + other.getB());
		}

		public Complex substract(ref Complex other)
		{
			return new Complex(this.a - other.getA(), this.b - other.getB());
		}

		public Complex multiply(ref Complex other)
		{
			Complex temp = new Complex();
			temp.fromPolar(this.r * other.getR(), this.phi + other.getPhi());
			return temp;
		}

		public Complex multiply(double number) {
			Complex temp = new Complex();
			temp.fromPolar(this.r * number, this.phi);
			return temp;
		}

		public Complex divide(ref Complex other)
		{
			Complex temp = new Complex();
			temp.fromPolar(this.r / other.getR(), this.phi - other.getPhi());
			return temp;
		}

		public Complex power(int exponant)
		{
			Complex temp = new Complex();
			temp.fromPolar(Math.Pow(this.r, exponant), this.phi * exponant);
			return temp;
		}

		public Complex[] root(int exponant)
		{

			Complex[] result = new Complex[exponant];

			double tempR = Math.Pow(this.r, 1 / exponant);

			for (int i = 0; i < exponant; i++)
			{
				double tempPhi = (phi + 2 * Math.PI * i) / exponant;
				Complex temp = new Complex();
				temp.fromPolar(tempR, tempPhi);

				result[i] = temp;
			}

			return result;
		}

		// Overload operators
		public static Complex operator +(Complex first, Complex other) {
			return first.add(ref other);
		}
		public static Complex operator -(Complex first, Complex other)
		{
			return first.substract(ref other);
		}
		public static Complex operator *(Complex first, Complex other)
		{
			return first.multiply(ref other);
		}
		public static Complex operator *(Complex first, double number)
		{
			return first.multiply(number);
		}
		public static Complex operator /(Complex first, Complex other)
		{
			return first.divide(ref other);
		}
		public static bool operator ==(Complex first, Complex other)
		{
			return first.getR() == other.getR() && first.getPhi() == other.getPhi();
		}
		public static bool operator !=(Complex first, Complex other)
		{
			return !(first == other);
		}

		public static explicit operator Vector(Complex number)	{
			return new Vector(number.getA(), number.getB());
		}

		// Setters
		public void setA(double _a)
		{
			this.a = _a;
			this.r = Math.Sqrt(this.a * this.a + this.b * this.b);
			this.phi = Math.Atan(this.b / this.a);
		}

		public void setB(double _b)
		{
			this.b = _b;
			this.r = Math.Sqrt(this.a * this.a + this.b * this.b);
			this.phi = Math.Atan(this.b / this.a);
		}

		public void setR(double _r)
		{
			this.r = _r;
			this.a = this.r * Math.Cos(this.phi);
			this.b = this.r * Math.Sin(this.phi);
		}

		public void setAngle(double angle)
		{
			this.phi = angle;
			this.a = this.r * Math.Cos(this.phi);
			this.b = this.r * Math.Sin(this.phi);
		}

		// Getters
		public double getA() { return this.a; }
		public double getB() { return this.b; }
		public double getR() { return this.r; }
		public double getPhi() { return this.phi; }

		public string toString()
		{
			return string.Format("{0} + {1}f i", this.a, this.b);
		}

		public string toStringPolar()
		{
			return string.Format("{0}f * (cos({1}) + i * sin({2}))", this.r, this.phi, this.phi);
		}

	}
}
