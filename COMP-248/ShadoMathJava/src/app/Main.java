/***
 *
 * Driver class
 */

package app;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Main {

	public static final void main(String[] args) {

		// TODO:
		JFrame window = new JFrame();
		window.setSize(1920, 1080);
		window.setTitle("Java GUI test");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		DrawingComponent DC = new DrawingComponent();
		window.add(DC);

		// SetInterval(function() {}, 1000);
		// new Timer().scheduleAtFixedRate(new TimerTask() {
		// @Override
		// public void run() {
		// print("?XD");
		// }
		// }, 0, 1000);

		Hehexd test = new Hehexd();
		Alpha test2 = new Alpha();
		Alpha test3 = (Alpha) test;
	}
}

class Hehexd {
	public String emote;

	public Hehexd() {
		emote = "LULW";
	}
}

class Alpha extends Hehexd {
	public String emote;

	public Alpha() {
		emote = "OMEGALUL";
	}
}

