/***
 *
 * Driver class
 * */

package app;

import ShadoMath.Vector;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ShadoMath.Util.random;
import static ShadoMath.Util.randomColor;

public class DrawingComponent extends JComponent implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 4659119803554492770L;

	private final int FPS = 60;
	private boolean HAS_INIT = false;
	private Timer tm = new Timer(1000 / FPS, this);

	// Need to define global variables to animate them (to avoid flicker)
	List<Shado.Circle> circles = new ArrayList<Shado.Circle>();
	List<Vector> velocities = new ArrayList<Vector>();

	// Init stuff
	private void init() {
		// Example generate 100 circles
		for (int i = 0; i < 100; i++) {
			int x = random(0, (int) getSize().getWidth());
			int y = random(0, (int) getSize().getHeight());
			int r = random(10, 50);
			Shado.Circle temp = new Shado.Circle(x, y, r);
			temp.setFill(randomColor());
			circles.add(temp);

			// Random velocities
			velocities.add(new Vector(random(-0.5, 0.5), random(-0.5, 0.5)));
		}
	}

	// This is the renderer (Only draw). You also can put logic here (collision, forces, etc)
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Init stuff if first loop
		if (!HAS_INIT) {
			this.init();
			HAS_INIT = true;
		}

		// Draw stuff
		circles.parallelStream()
				.forEachOrdered(circle -> {

					int vectorIndex = circles.indexOf(circle);
					circle.move(velocities.get(vectorIndex));

					if (circle.getX() + circle.getR() > getSize().getWidth() || circle.getX() < 0) {
						velocities.get(vectorIndex).x *= -1;
					}

					if (circle.getY() + circle.getR() > getSize().getHeight() || circle.getY() < 0) {
						velocities.get(vectorIndex).y *= -1;
					}

					circle.draw(g2);
				});

		tm.start();
	}

	// Here YOU CAN perform logic (add forces/velocity, check collision, etc)
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
