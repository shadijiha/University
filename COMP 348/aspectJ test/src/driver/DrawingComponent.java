/**
 *
 */

package driver;

import javax.swing.*;
import java.awt.*;

public class DrawingComponent extends JPanel {

	private Rectangle[][] list;
	private final static int RECT_WIDTH = 5;
	private int added;

	/**
	 * Creates a new <code>JPanel</code> with a double buffer
	 * and a flow layout.
	 */
	public DrawingComponent(int width, int height) {
		this.setSize(width, height);

		int countX = width / RECT_WIDTH;
		int countY = height / RECT_WIDTH;

		list = new Rectangle[countY][countX];
		added = 0;

		generate();
	}

	private void generate() {

		// First Rect
		if (added == 0) {

			int indexY = random(0, list.length - 1);
			int indexX = random(0, list[indexY].length - 1);

			var rect = new Rectangle(
					indexX * RECT_WIDTH,
					indexY * RECT_WIDTH,
					RECT_WIDTH,
					RECT_WIDTH);

			list[indexY][indexX] = rect;

		} else {

			if (random(0, 1000) == 1) {

				int indexY = random(0, list.length);
				int indexX = random(0, list[indexY].length);

				var rect = new Rectangle(
						indexX * RECT_WIDTH,
						indexY * RECT_WIDTH,
						RECT_WIDTH,
						RECT_WIDTH);

				if (list[indexY][indexX] == null)
					list[indexY][indexX] = rect;
				else
					generate();
			} else {

				// Get a valid Rectangle
				int indexY = random(0, list.length);
				int indexX = random(0, list[indexY].length);

				while (list[indexY][indexX] == null) {
					indexY = random(0, list.length);
					indexX = random(0, list[indexY].length);
				}

				int dirX = randomNonZero(-2, 2);
				int dirY = randomNonZero(-2, 2);

				var rect = new Rectangle(
						(indexX + dirX) * RECT_WIDTH,
						(indexY + dirY) * RECT_WIDTH,
						RECT_WIDTH,
						RECT_WIDTH);

				list[indexY][indexX] = rect;
			}
		}

		added++;
	}

	@Override
	public void paintComponent(Graphics g) {

		for (Rectangle[] rectangles : list) {
			for (Rectangle rectangle : rectangles) {
				if (rectangle != null)
					g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			}
		}


		generate();
		repaint();
		try {
			Thread.sleep(1 / 24);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	public static int randomNonZero(int min, int max) {

		int i = random(min, max);
		while (i == 0)
			i = random(min, max);
		return i;
	}
}
