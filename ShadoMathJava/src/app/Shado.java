/***
 * Shado shapes
 * */

package app;

import ShadoMath.Vector;
import ShadoMath.Vertex;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.css.Rect;

public abstract class Shado {

	public static class Rectangle {
		private Rectangle2D.Double rectangle; // Stores shape
		private Color fill = new Color(255, 255, 255, 255); // Stores color
		private Color stroke = new Color(0, 0, 0);

		// Main constructor
		public Rectangle(double x, double y, double w, double h) {
			rectangle = new Rectangle2D.Double(x, y, w, h);
		}

		// Renderer
		public void draw(Graphics2D graphics) {
			graphics.setColor(this.fill);
			graphics.fill(this.rectangle);
			graphics.setColor(this.stroke);
			graphics.draw(this.rectangle);
		}

		public void move(double dx, double dy) {
			this.rectangle.x += dx;
			this.rectangle.y += dy;
		}

		public void move(Vector v) {
			this.move(v.x, v.y);
		}

		// Math operations
		public boolean collides(Rectangle other) {
			return this.getX() + this.getWidth() >= other.getX() && this.getX() <= other.getX() + other.getWidth()
					&& this.getY() + this.getHeight() >= other.getY()
					&& this.getY() <= other.getY() + other.getHeight();
		}

		public boolean collides(Circle circle) {
			return circle.collides(this);
		}

		// Getters
		public Color getFill() {
			return this.fill;
		}

		public Color getStroke() {
			return this.stroke;
		}

		public double getX() {
			return this.rectangle.x;
		}

		public double getY() {
			return this.rectangle.y;
		}

		public double getWidth() {
			return this.rectangle.width;
		}

		public double getHeight() {
			return this.rectangle.width;
		}

		// Setters
		public void setFill(Color c) {
			this.fill = c;
		}

		public void setStroke(Color c) {
			this.stroke = c;
		}
	}

	public static class Circle {
		private Ellipse2D.Double ellipse; // Stores shape
		private Color fill = new Color(255, 255, 255, 255); // Stores fill color
		private Color stroke = Color.BLACK; // Stores stroke color

		// Constructors
		public Circle(double x, double y, double r) {
			this.ellipse = new Ellipse2D.Double(x, y, r * 2, r * 2);
		}

		// Graphics operations
		public void draw(Graphics2D graphics) {
			graphics.setColor(this.fill);
			graphics.fill(this.ellipse);
			graphics.setColor(this.stroke);
			graphics.draw(this.ellipse);
		}

		public void move(double dx, double dy) {
			this.ellipse.x += dx;
			this.ellipse.y += dy;
		}

		public void move(Vector v) {
			this.move(v.x, v.y);
		}

		// Math operations and collisions
		public Vertex getCenter() {
			return new Vertex(this.getX() + this.ellipse.width / 2, this.getY() + this.ellipse.height / 2);
		}

		public boolean collides(Circle other) {
			Vertex posThis = this.getCenter();
			Vertex posOther = other.getCenter();
			return posThis.getDistance(posOther) <= this.getR() + other.getR();
		}

		public boolean collides(Rectangle rect) {
			Rectangle hitBox = new Rectangle(this.getX(), this.getY(), this.getR() * 2, this.getR() * 2);
			return hitBox.collides(rect);
		}

		// Setters
		public void setFill(Color c) {
			this.fill = c;
		}

		public void setStroke(Color c) {
			this.stroke = c;
		}

		public void setPosition(Vertex newPosition) {
			this.ellipse.x = newPosition.x;
			this.ellipse.y = newPosition.y;
		}

		public void setRadius(double r) {
			this.ellipse.width = r * 2;
			this.ellipse.height = r * 2;
		}

		// Getters
		public Color getFill() {
			return this.fill;
		}

		public Color getStroke() {
			return this.stroke;
		}

		public double getX() {
			return this.ellipse.x;
		}

		public double getY() {
			return this.ellipse.y;
		}

		public double getR() {
			return this.ellipse.width / 2;
		}
	}

	public static class Line {
		private Line2D.Double line; // Stores shape
		private Color color; // Store color

		public Line(int fromX, int fromY, int toX, int toY) {
			this.line = new Line2D.Double(fromX, fromY, toX, toY);
		}

		public void draw(Graphics2D graphics) {
			graphics.setColor(this.color);
			graphics.draw(this.line);
		}

		public void move(double dx, double dy) {
			this.line.x1 += dx;
			this.line.y1 += dy;
			this.line.x2 += dx;
			this.line.y2 += dy;
		}

		public void move(Vector v) {
			this.move(v.x, v.y);
		}

		public void setColor(Color c) {
			this.color = c;
		}

		public Color getColor() {
			return this.color;
		}
	}

	public static class Text {

		private String text;
		private Font font = new Font("Times new Roman", Font.BOLD, 14);
		private Vertex vertex;

		// Constructors
		public Text(String text, Vertex coodinates, Font font) {
			this.text = text;
			this.font = font;
			this.vertex = coodinates;
		}

		public Text(String text, Vertex coodinates) {
			this.text = text;
			this.vertex = coodinates;
		}

		public Text(String text, double x, double y) {
			this.text = text;
			this.vertex = new Vertex(x, y);
		}

		// Graphics
		public void draw(Graphics2D graphics) {
			graphics.setFont(this.font);
			graphics.drawString(this.text, (int) vertex.x, (int) vertex.y);
		}

		// Math operations & collisions
		public boolean collides(Rectangle other) throws Exception {
			throw new Exception("Not coded yet");
			// Rectangle hitBox = new Rectangle(this.vertex.x, this.vertex.y,)
		}

		// Setters
		public void setCoordinates(Vertex v) {
			this.vertex = v;
		}

		public void setX(double x) {
			this.vertex.x = x;
		}

		public void setY(double y) {
			this.vertex.y = y;
		}

		public void setFont(Font font) {
			this.font = font;
		}

		public void setText(String text) {
			this.text = text;
		}

		// Getters
		public Vertex getCoordinates() {
			return this.vertex;
		}

		public double getX() {
			return this.vertex.x;
		}

		public double getY() {
			return this.vertex.y;
		}

		public Font getFont() {
			return this.font;
		}

		public String getText() {
			return this.text;
		}
	}
}