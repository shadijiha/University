/**
 *
 */
package com.engin.shapes;

import com.engin.io.Input;

import java.awt.*;

public class Rectangle extends Shape {

	public Rectangle(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public Rectangle(float x, float y, float w, float h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {

		if (texture != null) {
			g.drawImage(texture.getImage(), (int) position.x, (int) position.y, null);
			return;
		}

		var __color = g.getColor();

		g.setColor(fill);
		g.fillRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(stroke);
		g.drawRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(__color);
	}

	/**
	 * @return Returns true if the mouse if being clicked and hovering the shape
	 * Must be overridden by children because each shape has different hit box
	 */
	@Override
	protected boolean isClicked() {
		return (position.x < Input.getMouseX() + 1 &&
				position.x + dimension.x > Input.getMouseX() &&
				position.y < Input.getMouseY() + 1 &&
				position.y + dimension.y > Input.getMouseY()) && Input.mouseIsPressed();
	}
}
