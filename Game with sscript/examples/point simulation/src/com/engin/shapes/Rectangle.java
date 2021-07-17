/**
 *
 */
package com.engin.shapes;

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
			texture.setPosition((int) position.x, (int) position.y);
			texture.draw(g);
			return;
		}

		var __color = g.getColor();

		g.setColor(fill);
		g.fillRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(stroke);
		g.drawRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(__color);
	}
}
