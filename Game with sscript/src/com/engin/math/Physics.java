/**
 *
 */
package com.engin.math;

import com.engin.shapes.*;

public abstract class Physics {

	private Physics() {
	}

	public static boolean collisionBetween(Shape shape1, Shape shape2) {
		if (shape1 instanceof Rectangle && shape2 instanceof Rectangle)
			return collisionBetween((Rectangle) shape1, (Rectangle) shape2);

		if (shape1 instanceof Circle && shape2 instanceof Circle)
			return collisionBetween((Circle) shape1, (Circle) shape2);

		if (shape1 instanceof Rectangle && shape2 instanceof Circle)
			return collisionBetween((Rectangle) shape1, (Circle) shape2);

		if (shape2 instanceof Rectangle && shape1 instanceof Circle)
			return collisionBetween((Rectangle) shape2, (Circle) shape1);

		if (shape1 instanceof Rectangle && shape2 instanceof Coordinates2f)
			return collisionBetween((Rectangle) shape1, (Coordinates2f) shape2);

		if (shape2 instanceof Rectangle && shape1 instanceof Coordinates2f)
			return collisionBetween((Rectangle) shape2, (Coordinates2f) shape1);

		if (shape1 instanceof Circle && shape2 instanceof Coordinates2f)
			return collisionBetween((Circle) shape1, (Coordinates2f) shape2);

		if (shape2 instanceof Circle && shape1 instanceof Coordinates2f)
			return collisionBetween((Circle) shape2, (Coordinates2f) shape1);

		return false;
	}

	public static boolean collisionBetween(Rectangle rect1, Rectangle rect2) {
		return rect1.getPosition().x < rect2.getPosition().x + rect2.getDimensions().x &&
				rect1.getPosition().x + rect1.getDimensions().x > rect2.getPosition().x &&
				rect1.getPosition().y < rect2.getPosition().y + rect2.getDimensions().y &&
				rect1.getPosition().y + rect1.getDimensions().y > rect2.getPosition().y;
	}

	public static boolean collisionBetween(Rectangle rect1, Circle circle) {
		Rectangle rect2 = new Rectangle((int) circle.getPosition().x, (int) circle.getPosition().y, (int) circle.getDimensions().x, (int) circle.getDimensions().y);
		return collisionBetween(rect1, rect2);
	}

	public static boolean collisionBetween(Rectangle rect1, Coordinates2f point) {
		return rect1.getPosition().x < point.getX() + 1 &&
				rect1.getPosition().x + rect1.getDimensions().x > point.getX() &&
				rect1.getPosition().y < point.getY() + 1 &&
				rect1.getPosition().y + rect1.getDimensions().y > point.getY();
	}

	public static boolean collisionBetween(Circle circle, Coordinates2f point) {
		return collisionBetween(circle, new Circle(point.getX(), point.getY(), 1, 1));
	}

	public static boolean collisionBetween(Circle circle1, Circle circle2) {
		return Math.pow(circle2.getPosition().y - circle1.getPosition().y, 2) + Math.pow(circle2.getPosition().x - circle1.getPosition().x, 2)
				<= Math.pow(((circle1.getDimensions().x + circle1.getDimensions().y) / 2 + (circle2.getDimensions().x + circle2.getDimensions().y) / 2) / 2, 2);
	}

	public static double distance(Coordinates2f point1, Coordinates2f point2) {
		return Math.sqrt(Math.pow(point1.getY() - point2.getY(), 2) + Math.pow(point1.getX() - point2.getY(), 2));
	}
}
