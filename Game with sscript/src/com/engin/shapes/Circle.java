/**
 *
 */

package com.engin.shapes;

import com.engin.io.Input;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Shape {

	@Deprecated
	public Circle(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public Circle(int x, int y, int r) {
		this(x, y, r * 2, r * 2);
	}

	public Circle(float x, float y, float w, float h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {

		var clip = g.getClip();
		if (texture != null) {
			g.setClip(new Ellipse2D.Float(position.x, position.y, dimension.x, dimension.y));
			g.drawImage(texture.getImage(), (int) position.x, (int) position.y, null);
			g.setClip(clip);
			return;
		}

		var __color = g.getColor();

		g.setColor(fill);
		g.fillOval((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(stroke);
		g.drawOval((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(__color);
	}

	/**
	 * @return Returns true if the mouse if being clicked and hovering the shape
	 * Must be overridden by children because each shape has different hit box
	 */
	@Override
	protected boolean isClicked() {
		float distX = Input.getMouseX() - position.x;
		float distY = Input.getMouseY() - position.y;

		float distanceSquared = (distX * distX) + (distY * distY);


		if (distanceSquared <= getRadius() * getRadius()) {
			return Input.mouseIsPressed();
		}
		return false;
	}

	public int getRadius() {
		return (int) ((dimension.x + dimension.y) / 2);
	}
}
