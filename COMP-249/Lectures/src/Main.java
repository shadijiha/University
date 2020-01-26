
/***
 * Driver class
 */

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;

import ShadoMath.Vertex;

public class Main {
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(new FlowLayout());
//
//		JButton b = new JButton("Log In");
//		formatJButton(b);
//
//		frame.add(b);
//		frame.pack();
//		frame.setVisible(true);

		List<Vertex> S = new ArrayList<Vertex>();
		List<Vertex> E = new ArrayList<Vertex>();

		// Init
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				S.add(new Vertex(i + 1, j + 1));
			}
		}

		// Add to E
		E = S.stream().filter(e -> (e.x + e.y) % 2 != 0 && e.x != 1).collect(Collectors.toList());

		// Display
		int result = combination(11, 1) * combination(10, 1) * combination(9, 1) * combination(8, 4)
				* combination(4, 4);
		System.out.println(result);

	}

	public static int combination(int n, int r) {
		return f(n) / (f(n - r) * f(r));
	}

	public static int f(int n) {
		return n >= 1 ? (n * f(n - 1)) : 1;
	}

	public static void display(List<Vertex> list) {
		var count = 0;
		for (var temp : list) {
			System.out.printf("(%1.0f,%1.0f), ", temp.x, temp.y);
			count++;
			if (count % 6 == 0) {
				System.out.print("\n");
			}
		}
	}

	public static void formatJButton(JButton b) {
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
}