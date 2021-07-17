/**
 *
 */
package com.engin.io;

import com.engin.*;
import com.engin.math.*;

import java.awt.*;
import java.awt.event.*;

public final class Input extends Scene {

	public static final Input singleton = new Input();

	private Vector2f mouse;
	private boolean ispressed;

	public static int getMouseX() {
		return (int) singleton.mouse.x;
	}

	public static int getMouseY() {
		return (int) singleton.mouse.y;
	}

	public static boolean mouseIsPressed() {
		return singleton.ispressed;
	}

	private Input() {
		super("Input scene", Integer.MIN_VALUE);
	}

	@Override
	public void init(Renderer renderer) {
		mouse = new Vector2f();
	}

	@Override
	public void update(float dt) {

	}

	@Override
	public void draw(Graphics g) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ispressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ispressed = false;
	}
}
