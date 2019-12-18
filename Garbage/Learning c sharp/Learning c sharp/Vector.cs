using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning_c_sharp
{
    public class Vector
    {
		public double x;
		public double y;
		public double z;

		/**
		 * 
		 */
		public Vector(double _x, double _y, double _z)
		{
			this.x = _x;
			this.y = _y;
			this.z = _z;
		}

		public Vector(double _x, double _y)
		{
			this.x = _x;
			this.y = _y;
			this.z = 0;
		}

		public Vector(int scale)
		{
			this.x = 0;
			this.y = 0;
			this.z = 0;
			this.random2D(scale);
		}

		public Vector()
		{
			this.x = 0;
			this.y = 0;
			this.z = 0;
			this.random2D(10);
		}

		public Vector(Vector vectorTocopy)
		{
			this.x = vectorTocopy.x;
			this.y = vectorTocopy.y;
			this.z = vectorTocopy.z;
		}

		public void random2D(int scale)
		{
			Random rand = new Random();
			this.x = rand.Next(0 ,scale);
			this.y = rand.Next(0, scale);
		}

		public void random2D()
		{
			this.random2D(10);
		}

		public void random3D(int scale)
		{
			this.random2D(scale);
			this.z = new Random().Next(0, scale);
		}

		public void random3D()
		{
			this.random3D(10);
		}

		public void inverse()
		{
			this.x = -this.x;
			this.y = -this.y;
			this.z = -this.z;
		}

		public void scale(double scale)
		{
			this.x *= scale;
			this.y *= scale;
			this.z *= scale;
		}

		// Math operations
		public double mag()
		{
			return Math.Sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
		}

		public void normalize()
		{
			double tempMag = this.mag();
			this.x = this.x / tempMag;
			this.y = this.y / tempMag;
			this.z = this.z / tempMag;
		}

		public Vector add(ref Vector other)
		{
			return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
		}

		public Vector substract(ref Vector other)
		{
			other.inverse();
			return this.add(ref other);
		}

		public double dotProduct(ref Vector b)
		{
			return this.x * b.x + this.y * b.y + this.z * b.z;
		}

		public Vector crossProduct(ref Vector b)
		{
			Matrix i = new Matrix(2, 2);
			i.setData(0, 0, y);
			i.setData(0, 1, z);
			i.setData(1, 0, b.y);
			i.setData(1, 1, b.z);

			Matrix j = new Matrix(2, 2);
			j.setData(0, 0, x);
			j.setData(0, 1, z);
			j.setData(1, 0, b.x);
			j.setData(1, 1, b.z);

			Matrix k = new Matrix(2, 2);
			k.setData(0, 0, x);
			k.setData(0, 1, y);
			k.setData(1, 0, b.x);
			k.setData(1, 1, b.y);

			return new Vector(i.determinant(), -1.0f * j.determinant(), k.determinant());
		}

		public Matrix multiply(Matrix matrix)
		{
			Matrix vec = Matrix.vectorToMatrix(this);
			return vec.multiply(matrix);
		}

		public Vector multiply(double scale)
		{
			Vector result = new Vector(this.x, this.y, this.z);
			result.scale(scale);
			return result;
		}

		public Vector project(ref Vector other)
		{
			Vector copy = new Vector(this);
			double multiplier = other.dotProduct(ref copy);

			multiplier = multiplier / (other.mag() * other.mag());

			return other.multiply(multiplier);
		}

		// Static methodes
		public static Vector matrixToVector(Matrix matrix)
		{
			if (matrix.is2D())
			{
				return new Vector((double)matrix.getData(0, 0), (double)matrix.getData(1, 0), 0.0);
			}
			else
			{
				return new Vector((double)matrix.getData(0, 0), (double)matrix.getData(1, 0), (double)matrix.getData(2, 0));
			}
		}

		public static explicit operator Matrix(Vector vec) {
			Matrix temp = new Matrix(3, 1);
			temp.setData(0, 0, vec.x);
			temp.setData(1, 0, vec.y);
			temp.setData(2, 0, vec.z);
			return temp;
		}

		public static explicit operator Vertex(Vector vec) {
			if (vec.z != 0)
				throw new Exception("Cannot convert a 3D vector to a vertex." + vec.toString() + " cannot be converted to vertex");
			return new Vertex(vec.x, vec.y);
		}
		// Printers
		public string toString()
		{
			return string.Format("(x: {0}, y: {1}, z: {2})", this.x, this.y, this.z);
		}

		public void print()
		{
			Console.WriteLine(this.toString());
		}

	}
}
