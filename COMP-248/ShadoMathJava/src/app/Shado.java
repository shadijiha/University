/***
 * Shado shapes
 * */

package app;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public abstract class Shado {

	public static class Rectangle {
		private Rectangle2D rectangle;                        // Stores shape
		private Color color = new Color(0, 0, 0);    // Stores color

		// Main constructor
		Rectangle(double x, double y, double w, double h) {
			rectangle = new Rectangle2D.Double(x, y, w, h);
		}

		void draw(Graphics2D graphics) {
			graphics.setColor(this.color);
			graphics.fill(this.rectangle);
		}

		Color getColor() {
			return this.color;
		}

		void setColor(Color c) {
			this.color = c;
		}
	}

	public static class Circle {
		private Ellipse2D.Double ellipse;    // Stores shape
		private Color color;                // Stores color

		Circle(double x, double y, double r) {
			this.ellipse = new Ellipse2D.Double(x, y, r, r);
		}

		void draw(Graphics2D graphics) {
			graphics.setColor(this.color);
			graphics.fill(this.ellipse);
		}

		void setColor(Color c) {
			this.color = c;
		}

		Color getColor() {
			return this.color;
		}
	}

	public static class Line {
		private Line2D.Double line;        // Stores shape
		private Color color;            // Store color

		Line(int fromX, int fromY, int toX, int toY) {
			this.line = new Line2D.Double(fromX, fromY, toX, toY);
		}

		void draw(Graphics2D graphics) {
			graphics.setColor(this.color);
			graphics.draw(this.line);
		}

		void setColor(Color c) {
			this.color = c;
		}

		Color getColor() {
			return this.color;
		}
	}

}


