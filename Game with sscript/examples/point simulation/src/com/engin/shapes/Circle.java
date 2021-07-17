/**
 *
 */

package com.engin.shapes;

import java.awt.*;
import java.awt.geom.*;

public class Circle extends Shape {

	public Circle(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public Circle(float x, float y, float w, float h) {
		super(x, y, w, h);
	}

	public Circle(int x, int y, int r) {
		this(x, y, r * 2, r * 2);
	}

	@Override
	public void draw(Graphics g) {

		var clip = g.getClip();
		if (texture != null) {
			g.setClip(new Ellipse2D.Float(position.x, position.y, dimension.x, dimension.y));
			texture.setPosition((int) position.x, (int) position.y);
			texture.draw(g);
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
}
