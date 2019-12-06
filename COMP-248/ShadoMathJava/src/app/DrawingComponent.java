/***
 *
 * Driver class
 * */

package app;

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
	private Timer tm = new Timer(1 / FPS, this);

	// Need to define global variables to animate them (to avoid flicker)
	List<Shado.Circle> circles = new ArrayList<Shado.Circle>();

	// Initi stuff
	private void init() {

		// Example generate 20 circles
		for (int i = 0; i < 100; i++) {
			int x = random(0, (int) getSize().getWidth());
			int y = random(0, (int) getSize().getHeight());
			int r = random(10, 50);
			Shado.Circle temp = new Shado.Circle(x, y, r);
			temp.setColor(randomColor());
			circles.add(temp);
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
		circles.stream()
				.forEach(e -> e.draw(g2));

//		g2.setFont(new Font("Times new Roman", Font.BOLD, 14));
//		g2.drawString("hehexd", 50, 50);

		tm.start();
	}

	// Here YOU CAN perform logic (add forces/velocity, check collision, etc)
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
