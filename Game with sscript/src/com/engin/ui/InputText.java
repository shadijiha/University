/**
 *
 */
package com.engin.ui;

import com.engin.io.*;

import java.awt.*;

public class InputText extends UIComponent {

	public enum Type {
		TEXT, PASSWORD
	}

	private final Type type;
	private final UIStyle style;
	private final UIStyle hoverStyle;
	private final String value;
	private final String placeholder;
	private boolean isFocus;

	private long frames;

	public InputText(String value, String placeholder, Type type) {
		this.value = value;
		this.placeholder = placeholder;
		this.type = type;
		dimension.x = 150;
		dimension.y = 50;
		style = new UIStyle()
				.backgroundColor(Color.WHITE)
				.color(Color.BLACK)
				.border(Color.BLACK, 1)
				.font(new Font("Arial", Font.BOLD, 14))
				.padding(5);
		hoverStyle = new UIStyle(style);


		onClick(() -> {
			isFocus = !isFocus;
		});
	}

	public InputText(String placeholder, Type type) {
		this(null, placeholder, type);
	}

	public InputText(String placeholder) {
		this(null, placeholder, Type.TEXT);
	}

	public String getValue() {
		return value;
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

		// Render border
		g.setStroke(new BasicStroke(currentStyle.borderThickness));
		g.setColor(currentStyle.borderColor);
		g.drawRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);

		g.setColor(currentStyle.color);
		g.setFont(currentStyle.font);

		// Render text
		if (value != null && !value.isEmpty())
			g.drawString(value, (int) (position.x + currentStyle.padding), (int) (position.y + currentStyle.padding + dimension.y / 2));
		else {
			g.setColor(Color.GRAY);
			g.drawString(placeholder, (int) (position.x + currentStyle.padding), (int) (position.y + currentStyle.padding + dimension.y / 2));
		}

		// Render cursor
		if (isFocus && frames % 20 == 0) {
			int stringWidth = g.getFontMetrics().stringWidth(value == null ? "" : value);
			g.setColor(Color.BLACK);
			g.fillRect((int) position.x + stringWidth + 5, (int) (position.y + dimension.y * 0.2), 2, (int) (dimension.y * 0.6));
		}

		g.setColor(c);
		g.setFont(f);
		g.setStroke(s);

		frames++;
	}

}
