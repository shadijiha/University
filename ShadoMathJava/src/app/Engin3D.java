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
	public static final class Triangle {

		private Vector[] vectors = new Vector[3];

		public Triangle(Vector vector1, Vector vector2, Vector vector3) {
			this.vectors[0] = vector1;
			this.vectors[1] = vector2;
			this.vectors[2] = vector3;
		}

	}

	public static final class Mesh {

		private List<Triangle> tris = new ArrayList<Triangle>();

		// Constructors
		public Mesh() {
		}

		// Setters
		public void add(Triangle element) {
			this.tris.add(element);
		}

		public void remove(Triangle element) {
			this.tris.remove(element);
		}

		// Getters
		public void indexOf(Triangle element) {
			this.tris.indexOf(element);
		}

		public Triangle getData(int index) {
			return this.tris.get(index);
		}
	}
}