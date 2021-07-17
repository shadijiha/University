/**
 *
 */
package com.engin.shapes;

import com.engin.interfaces.*;
import com.engin.math.*;

import java.awt.*;
import java.io.*;

public abstract class Shape extends UUID implements Serializable, IMovable, IResizeable {

	private static final long serialVersionUID = 3183164399459748671L;

	protected Vector2f position;
	protected Vector2f dimension;
	protected int lineWidth;

	protected Color fill;
	protected Color stroke;
	protected Texture texture;

	private Shape(int x, int y, int w, int h, Texture t) {
		this.position = new Vector2f(x, y);
		this.dimension = new Vector2f(w, h);
		this.texture = t;
		this.fill = Color.WHITE;
		this.stroke = Color.BLACK;
		this.lineWidth = 1;
	}

	public Shape(int x, int y, int w, int h) {
		this(x, y, w, h, null);
	}

	public Shape(float x, float y, float w, float h) {
		this((int) x, (int) y, (int) w, (int) h, null);
	}

	public abstract void draw(Graphics g);

	/**
	 * Moves the shape to new location
	 *
	 * @param x The new X
	 * @param y The new Y
	 */
	public void moveTo(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}

	public void moveTo(ICoordinates2F ICoordinates2F) {
		moveTo((int) ICoordinates2F.getX(), (int) ICoordinates2F.getY());
	}

	/**
	 * Increments the position of the object by the parameters passed
	 *
	 * @param x The X to add
	 * @param y The Y to add
	 */
	public void moveBy(float x, float y) {
		position.x += x;
		position.y += y;
	}

	public void moveBy(ICoordinates2F ICoordinates2F) {
		moveBy((int) ICoordinates2F.getX(), (int) ICoordinates2F.getY());
	}

	/**
	 * Changes the fill of the shape
	 *
	 * @param c The new Color
	 */
	public void setFill(Color c) {
		this.fill = c;
	}

	/**
	 * Changes the fill of the shape
	 *
	 * @param c The new Color
	 */
	public void setStroke(Color c) {
		this.stroke = c;
	}

	/**
	 * Changes the line width of the shape
	 *
	 * @param c The new line Width
	 */
	public void setLineWidth(int c) {
		this.lineWidth = c;
	}

	/**
	 * Changes the dimension of the shape
	 *
	 * @param d
	 */
	public void setDimensions(final ICoordinates2F d) {
		this.dimension.x = d.getX();
		this.dimension.y = d.getY();

		if (texture != null)
			this.texture = new Texture(Texture.resize(texture.getImage(), (int) d.getX(), (int) d.getY()));
	}

	/**
	 * Changes the texture of the object
	 *
	 * @param t
	 */
	public void setTexture(final Texture t) {
		var temp = new Texture(t.getPath(), (int) dimension.x, (int) dimension.y);
		this.texture = temp;
	}

	public void setTexture(final String path) {
		this.texture = new Texture(path, (int) dimension.x, (int) dimension.y);
	}

	public void removeTexture() {
		this.texture = null;
	}

	@Override
	public void setPosition(ICoordinates2F position) {
		moveTo(position);
	}

	/**
	 * @return Returns a deep copy of the position of this shape
	 */
	public ImmutableVec2f getPosition() {
		return position.toImmutableVector();
	}

	/**
	 * @return Returns a deep copy of the dimensions of this object
	 */
	public ImmutableVec2f getDimensions() {
		return dimension.toImmutableVector();
	}

	/**
	 * @return Returns the fill of the shape
	 */
	public Color getFill() {
		return fill;
	}

	/**
	 * @return Returns the stroke of the shape
	 */
	public Color getStroke() {
		return stroke;
	}

	/**
	 * @return Returns the fill of the shape
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @return Returns the linewidth of the shape
	 */
	public int getLineWidth() {
		return lineWidth;
	}
}
