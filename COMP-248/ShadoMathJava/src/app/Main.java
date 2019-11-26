/***
 * 
 * Driver class
 * */

package app;

import javax.swing.JFrame;

public abstract class Main {

	public static void main(String[] args) {

		// TODO: Transpose Matrix, inverse Matrix, projection Vector
        JFrame window = new JFrame();
        window.setSize(640, 480);
        window.setTitle("Java GUI test");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        DrawingComponent DC = new DrawingComponent();
        window.add(DC);
	}

	public static <T> void print(T element) {
		System.out.println(element);
	}

}
