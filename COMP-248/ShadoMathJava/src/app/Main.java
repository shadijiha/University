/***
 *
 * Driver class
 */

package app;

import ShadoMath.Vector;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
		Engin3D.Mesh test = new Engin3D.Mesh();

		// SetInterval(function() {}, 1000);
		// new Timer().scheduleAtFixedRate(new TimerTask() {
		// @Override
		// public void run() {
		// print("?XD");
		// }
		// }, 0, 1000);
	}

	private static List<Character> vowelCount(Character[] charArray) {
		List<Character> vowel = Arrays.asList('a', 'e', 'i', 'o', 'u');
		List<Character> input = Arrays.asList(charArray);

		return input.parallelStream()
				.filter(e -> vowel
						.parallelStream()
						.anyMatch(v -> e == v))
				.collect(Collectors.toList());
	}

	public static String getObjectName(Object obj) {
		String[] temp = obj.getClass().getName().split("\\.");
		return temp[temp.length - 1];
	}

	public static <T> void print(@SuppressWarnings("unchecked") T... args) {
		for (T arg : args)
			System.out.print(arg + " ");
		System.out.println();
	}
}
