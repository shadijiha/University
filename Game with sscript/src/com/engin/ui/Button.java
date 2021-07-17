/**
 *
 */
package com.engin.ui;

import com.engin.io.*;

import java.awt.*;

public class Button extends UIComponent {

	private String text;
	private UIStyle style;
	private UIStyle hoverStyle;

	public Button(String text) {
		position.x = 0;
		position.y = 0;
		dimension.x = 75;
		dimension.y = 50;
		this.text = text;
		style = new UIStyle()
				.backgroundColor(Color.DARK_GRAY)
				.color(Color.WHITE)
				.border(Color.BLACK, 1)
				.font(new Font("Arial", Font.BOLD, 14));
		hoverStyle = new UIStyle()
				.backgroundColor(Color.LIGHT_GRAY)
				.color(Color.BLACK)
				.border(Color.BLACK, 1)
				.font(new Font("Arial", Font.ITALIC, 14));
	}

	public Button() {
		this("Button");
	}

	public Button setStyle(UIStyle style) {
		this.style = style;
		return this;
	}

	public Button setHoverStyle(UIStyle style) {
		this.hoverStyle = style;
		return this;
	}

	@Override
	public void render(Graphics2D g) {

		var c = g.getColor();
		var f = g.getFont();
		var s = g.getStroke();

		UIStyle currentStyle = style;

		// Handle hover
		if (this.isMouseOver()) {
			currentStyle = hoverStyle;

			// Handle click
			if (Input.mouseIsPressed()) {
				this.executeClickAction();
			}

		}

		g.setColor(currentStyle.backgroundColor);
		g.fillRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setStroke(new BasicStroke(currentStyle.borderThickness));
		g.setColor(currentStyle.borderColor);
		g.drawRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(currentStyle.color);
		g.setFont(currentStyle.font);
		g.drawString(text, (int) (position.x + dimension.x / 2), (int) (position.y + dimension.y / 2));

		g.setColor(c);
		g.setFont(f);
		g.setStroke(s);


	}


	/**
	 * Changes the text content of the button
	 * @param text The new text
	 */
	public void setText(String text) {
		this.text = text;
	}
}
