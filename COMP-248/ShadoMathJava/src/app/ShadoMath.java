
package app;

public class ShadoMath {
	
	public class Complex extends ShadoMath	{

		public double a = 0.0;
		public double b = 0.0;
		public double r = 0.0;
		public double phi = 0.0;

		public Complex(double magnitude, double angle)	{
			this.r = magnitude;
			this.phi = angle;
		}

		public Complex(int _a, int _b)	{
			this( (double)Math.sqrt(_a * _a + _b * _b) , (double)Math.atan( _b / _a));
			this.a = _a;
			this.b = _b;			
		}

		public double mag()	{
			return this.r;
		}

		public String toString()	{
			return String.format("%1.2f + %1.2f i", this.a, this.b);
		}

	}

}
