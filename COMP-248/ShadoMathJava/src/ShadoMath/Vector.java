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
	
	public void random(int scale)	{
		this.random2D(scale);
		this.z = Math.floor(Math.random() * scale);
	}
	
	public void random()	{
		this.random(10);
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
	
	/*Vector Vector::crossProduct(Vector& b) {

	Matrix<float, 2, 2> i;
	i.setData(0, 0, y);
	i.setData(0, 1, z);
	i.setData(1, 0, b.y);
	i.setData(1, 1, b.z);

	Matrix<float, 2, 2> j;
	j.setData(0, 0, x);
	j.setData(0, 1, z);
	j.setData(1, 0, b.x);
	j.setData(1, 1, b.z);

	Matrix<float, 2, 2> k;
	k.setData(0, 0, x);
	k.setData(0, 1, y);
	k.setData(1, 0, b.x);
	k.setData(1, 1, b.y);

	return Vector(i.det(), -1.0f * j.det(), j.det());

}*/
	
	public String toString()	{
		return String.format("(x: %f, y: %f, z: %f)", this.x, this.y, this.z);
	}
	
	public void print()	{
		System.out.println(this.toString());
	}
}
