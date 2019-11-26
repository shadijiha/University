/**
 * 
 */
package app;

import java.util.ArrayList;
import java.util.List;

import ShadoMath.Vector;

/**
 * @author shadi
 *
 */
public abstract class Engin3D {

	/**
	 * 
	 */	
	public static final class Triangle	{
		
		private Vector[] vectors = new Vector[3];
		
		public Triangle(Vector vector1, Vector vector2, Vector vector3)	{
			this.vectors[0] = vector1;
			this.vectors[1] = vector2;
			this.vectors[2] = vector3;
		}
		
	}
	
	public static final class Mesh	{
		
		public List<Triangle> tris = new ArrayList<Triangle>();
		
		public Mesh()	{}
	}
}
