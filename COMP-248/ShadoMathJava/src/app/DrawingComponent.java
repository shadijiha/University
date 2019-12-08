/***
 *
 * Driver class
 * */

package app;

import ShadoMath.Vector;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
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
	private JFrame parentWindow;
	private Timer tm = new Timer(1000 / FPS, this);
	private long deltaTime;

	// Need to define global variables to animate them (to avoid flicker)
	List<Shado.Circle> circles = new ArrayList<Shado.Circle>();
	List<Vector> velocities = new ArrayList<Vector>();
	private long time1 = new Date().getTime();

	// Constructor
	DrawingComponent(JFrame window) {
		this.parentWindow = window;
	}

	// Init stuff
	private void init() {
		// Empty variables
		circles = new ArrayList<Shado.Circle>();
		velocities = new ArrayList<Vector>();

		// Example generate 100 circles
		for (int i = 0; i < 100; i++) {
			int r = random(10, 25);
			int x = random(r * 2, (int) getSize().getWidth() - r * 2);
			int y = random(r * 2, (int) getSize().getHeight() - r * 2);
			Shado.Circle temp = new Shado.Circle(x, y, r);
			temp.setFill(randomColor());
			circles.add(temp);

			// Random velocities
			velocities.add(new Vector(random(-1, 1), random(-1, 1)));
		}
	}

	// This is the renderer (Only draw). You also can put logic here (collision,
	// forces, etc)
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Init stuff if first loop
		if (!HAS_INIT) {
			this.init();
			HAS_INIT = true;
		}

		// Draw stuff
		circles.parallelStream().forEachOrdered(circle -> {

			int vectorIndex = circles.indexOf(circle);
			circle.move(velocities.get(vectorIndex));

			if (circle.getX() + circle.getR() * 2 > getSize().getWidth() || circle.getX() < 0) {
				velocities.get(vectorIndex).x *= -1;
			}

			if (circle.getY() + circle.getR() * 2 > getSize().getHeight() || circle.getY() < 0) {
				velocities.get(vectorIndex).y *= -1;
			}

			circles.parallelStream().forEachOrdered(other -> {
				if (circle != other && circle.collides(other)) {
					velocities.get(vectorIndex).inverse();
					velocities.get(vectorIndex).scale(0.80); // Circles losese 20% Eenergy on collision
				}
			});

			circle.draw(g2);
		});

		tm.start();

		// Calculate real FPS
		long time2 = new Date().getTime();
		this.deltaTime = time2 - time1;
		this.parentWindow.setTitle(Long.toString((long) 1000 / deltaTime) + " frames per second");
		time1 = new Date().getTime();
	}

	// Here YOU CAN perform logic (add forces/velocity, check collision, etc)
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
