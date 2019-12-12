package ShadoMath;

public class Vertex {

	public double x;
	public double y;

	// Constructors
	public Vertex(double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}

	public Vertex(Vertex v) {
		this(v.x, v.y);
	}

	public Vertex() {
		this(0, 0);
	}

	// Math operations
	public double getDistance(Vertex v) {
		return Math.sqrt(Math.pow(v.x - this.x, 2) + Math.pow(v.y - this.y, 2));
	}

	// Setters
	public void setX(int _x) {
		this.x = _x;
	}

	public void setY(int _y) {
		this.y = _y;
	}

	public void set(Vertex v) {
		this.x = v.x;
		this.y = v.y;
	}


}
