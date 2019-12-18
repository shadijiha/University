using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    public class Vertex
    {
		public double x;
		public double y;

		// Constructors
		public Vertex(double _x, double _y)
		{
			this.x = _x;
			this.y = _y;
		}

		public Vertex(Vertex v)
		{
			this.x = v.x;
			this.y = v.y;
		}

		public Vertex()
		{
			this.x = 0;
			this.y = 0;
		}

		// Math operations
		public double getDistance(Vertex v)
		{
			return Math.Sqrt(Math.Pow(v.x - this.x, 2) + Math.Pow(v.y - this.y, 2));
		}

		// Setters
		public void setX(int _x)
		{
			this.x = _x;
		}

		public void setY(int _y)
		{
			this.y = _y;
		}

		public void set(Vertex v)
		{
			this.x = v.x;
			this.y = v.y;
		}

		// Printers
		public string toString() {
			return string.Format("(x: {0}, y: {1})", this.x, this.y);
		}

		public void print() {
			Console.WriteLine(this.toString());
		}
	}
}
