/***
 *
 * Driver class
 */

package app;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

import static ShadoMath.Util.getHour;

public abstract class Main {

	public static void main(String[] args) {

		// TODO:
		JFrame window = new JFrame();
		window.setSize(1920, 1080);
		window.setTitle("Java GUI test");
		window.getContentPane().setBackground(getHour() < 16 && getHour() > 7 ? Color.WHITE : Color.BLACK);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		DrawingComponent DC = new DrawingComponent(window);

		window.add(DC);

		// SetInterval(function() {}, 1000);
		// new Timer().scheduleAtFixedRate(new TimerTask() {
		// @Override
		// public void run() {
		// print("?XD");
		// }
		// }, 0, 1000);
	}
}
