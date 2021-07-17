/**
 *
 */
package com.engin.ui;

import com.engin.interfaces.*;

import java.awt.*;

public class UIStyle extends UUID {

	Color color;
	Color backgroundColor;
	Color borderColor;
	Font font;
	int borderThickness;
	int padding = 0;

	public UIStyle(final UIStyle style) {

		if (style.color != null)
			color = new Color(style.color.getRed(), style.color.getGreen(), style.color.getBlue(), style.color.getAlpha());

		if (style.backgroundColor != null)
			backgroundColor = new Color(style.backgroundColor.getRed(), style.backgroundColor.getGreen(), style.backgroundColor.getBlue(), style.backgroundColor.getAlpha());

		if (style.borderColor != null)
			borderColor = new Color(style.borderColor.getRed(), style.borderColor.getGreen(), style.borderColor.getBlue(), style.borderColor.getAlpha());

		if (style.font != null)
			font = new Font(style.font.getName(), style.font.getStyle(), style.font.getSize());

		borderThickness = style.borderThickness;
		padding = style.padding;
	}

	public UIStyle() {
	}

	public UIStyle color(Color color) {
		this.color = color;
		return this;
	}

	public UIStyle backgroundColor(Color color) {
		this.backgroundColor = color;
		return this;
	}

	public UIStyle border(Color color, int thickness) {
		this.borderColor = color;
		this.borderThickness = thickness;
		return this;
	}

	public UIStyle borderColor(Color color) {
		this.borderColor = color;
		return this;
	}

	public UIStyle font(Font font) {
		this.font = font;
		return this;
	}

	public UIStyle padding(int padding) {
		this.padding = padding;
		return this;
	}
}
