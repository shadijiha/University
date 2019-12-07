/***
 * Shado shapes
 * */

package app;

import ShadoMath.Vector;
import ShadoMath.Vertex;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public abstract class Shado {

	public static class Rectangle {
		private Rectangle2D.Double rectangle;                        // Stores shape
		private Color color = new Color(0, 0, 0);    // Stores color

		// Main constructor
		public Rectangle(double x, double y, double w, double h) {
			rectangle = new Rectangle2D.Double(x, y, w, h);
		}

		public void draw(Graphics2D graphics) {
			graphics.setColor(this.color);
			graphics.fill(this.rectangle);
		}

		public void move(double dx, double dy) {
			this.rectangle.x += dx;
			this.rectangle.y += dy;
		}

		public void move(Vector v) {
			this.move(v.x, v.y);
		}

		public Color getColor() {
			return this.color;
		}

		public void setColor(Color c) {
			this.color = c;
		}
	}

	public static class Circle {
		private Ellipse2D.Double ellipse;    // Stores shape
		private Color fill = new Color(255, 255, 255, 255);  // Stores fill color
		private Color stroke = Color.BLACK;     // Stores stroke color

		// Constructors
		public Circle(double x, double y, double r) {
			this.ellipse = new Ellipse2D.Double(x, y, r, r);
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

		// Math operations
		public Vertex getCenter() {
			return new Vertex(this.getX() + this.ellipse.width / 2, this.getY() + this.ellipse.height / 2);
		}

		// Setters
		public void setFill(Color c) {
			this.fill = c;
		}

		public void setStroke(Color c) {
			this.stroke = c;
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
			return this.ellipse.width;
		}
	}

	public static class Line {
		private Line2D.Double line;        // Stores shape
		private Color color;            // Store color

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


