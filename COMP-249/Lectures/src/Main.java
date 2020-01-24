
/***
 * Driver class
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import ShadoMath.Vector;

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

		var t = 2;
		Vector v = new Vector(-1 * (-8 * t), 17 * t, -15 * t);

		v.print();
	}

	public static void formatJButton(JButton b) {
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
}