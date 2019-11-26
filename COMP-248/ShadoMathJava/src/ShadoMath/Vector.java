/**
 * 
 */
package ShadoMath;

/**
 * @author shadi
 *
 */
public class Vector {

	public double x;
	public double y;
	public double z;
	
	/**
	 * 
	 */
	public Vector(double _x, double _y, double _z) {
		this.x = _x; this.y = _y; this.z = _z;
	}
	
	public Vector(double _x, double _y)	{
		this(_x, _y, 0);
	}

	public Vector(int scale)	{
		this(0, 0);
		this.random2D(scale);
	}
	
	public Vector()	{
		this(10);
	}
	
	public Vector(Vector vectorTocopy)	{
		this.x = vectorTocopy.x;
		this.y = vectorTocopy.y;
		this.z = vectorTocopy.z;
	}
	
	public void random2D(int scale)	{
		this.x = Math.floor(Math.random() * scale);
		this.y = Math.floor(Math.random() * scale);
	}
	
	public void random2D()	{
		this.random2D(10);
	}
	
	public void random3D(int scale)	{
		this.random2D(scale);
		this.z = Math.floor(Math.random() * scale);
	}
	
	public void random3D()	{
		this.random3D(10);
	}

	public void inverse()	{
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
	}
	
	public void scale(double scale)	{
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
	}
	
	// Math operations
	public double mag()	{
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public void normalize()	{
		double tempMag = this.mag();
		this.x = this.x / tempMag;
		this.y = this.y / tempMag;
		this.z = this.z / tempMag;
	}
	
	public Vector add(final Vector other)	{
		return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
	}
	
	public Vector substract(final Vector other)	{
		other.inverse();
		return this.add(other);
	}
	
	public double dotProduct(final Vector b)	{
		return this.x * b.x + this.y * b.y + this.z * b.z;
	}
	
	public Vector crossProduct(final Vector b) {
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
	
	public Matrix multiply(final Matrix matrix)	{
		Matrix vec = Matrix.vectorToMatrix(this);
		
		return vec.multiply(matrix);
	}
	
	public Vector multiply(double scale)	{
		Vector result = new Vector(this.x, this.y, this.z);
		result.scale(scale);
		return result;		
	}
	
	public Vector project(final Vector other)	{
		
		double multiplier = other.dotProduct(this);

		multiplier = multiplier / (other.mag() * other.mag());
		
		return other.multiply(multiplier);
	}
	
	// Static methodes
	public static Vector matrixToVector(Matrix matrix)	{
		if (matrix.is2D())	{
			return new Vector(matrix.getData(0, 0), matrix.getData(1, 0), 0.0);
		} else	{
			return new Vector(matrix.getData(0, 0), matrix.getData(1, 0), matrix.getData(2, 0));
		}		
	}
	
	// Printers
	public String toString()	{
		return String.format("(x: %f, y: %f, z: %f)", this.x, this.y, this.z);
	}
	
	public void print()	{
		System.out.println(this.toString());
	}
}
