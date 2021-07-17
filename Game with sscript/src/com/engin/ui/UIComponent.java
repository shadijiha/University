/**
 *
 */
package com.engin.ui;

import com.engin.*;
import com.engin.interfaces.*;
import com.engin.io.*;
import com.engin.math.*;

import java.awt.*;

public abstract class UIComponent extends UUID implements IResizeable, IMovable {

	//private static final List<UIComponent> allComponents = new ArrayList<>();

	protected static final long CLICK_DELAY = 200;

	protected final Vector2f position;
	protected final Vector2f dimension;
	protected Runnable clickAction;

	protected UIComponent() {
		position = new Vector2f(0, 0);
		dimension = new Vector2f(0, 0);

		//allComponents.add(this);
	}

	/**
	 * This function is called by the Renderer to draw the UI element to the screen
	 */
	public abstract void render(Graphics2D g);

	public UIComponent onClick(final Runnable clickAction) {
		this.clickAction = clickAction;
		return this;
	}

	/**
	 * @return Gets the width and the height on the component
	 */
	public ImmutableVec2f getPosition() {
		return position.toImmutableVector();
	}

	/**
	 * Changes the width and the height of the component
	 * @param position The new width and height
	 */
	public void setPosition(Coordinates2f position) {
		this.position.x = position.getX();
		this.position.y = position.getY();
	}

	/**
	 * @return Gets the width and the height on the component
	 */
	public ImmutableVec2f getDimensions() {
		return dimension.toImmutableVector();
	}

	/**
	 * Changes the width and the height of the component
	 * @param dimension The new width and height
	 */
	public void setDimensions(Coordinates2f dimension) {
		this.dimension.x = dimension.getX();
		this.dimension.y = dimension.getY();
	}

	protected boolean isMouseOver() {
		return position.x < Input.getMouseX() + 1 &&
				position.x + dimension.x > Input.getMouseX() &&
				position.y < Input.getMouseY() + 1 &&
				position.y + dimension.y > Input.getMouseY();
	}

	protected void executeClickAction() {
		if (clickAction != null) {
			clickAction.run();
			// Set a cooldown to avoid excessive clicking
			Util.sleep(CLICK_DELAY);
		}
	}
}
