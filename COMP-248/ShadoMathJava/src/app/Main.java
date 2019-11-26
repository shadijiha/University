/***
 * 
 * Driver class
 * */

package app;

import java.util.Timer;
import java.util.TimerTask;

import ShadoMath.Complex;
import ShadoMath.Fraction;
import ShadoMath.Matrix;
import ShadoMath.Vector;
import nancy3D.Dice;

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
		// Engin3D.Mesh test = new Engin3D.Mesh();
		print("LOL XD");

		double x = Math.pow(10, 2);
		print(x);
		// SetInterval(function() {}, 1000);
		/*
		 * new Timer().scheduleAtFixedRate(new TimerTask(){
		 * 
		 * @Override public void run(){ print("?XD"); } }, 0, 1000);
		 */

	}

	public static <T> void print(T element) {
		System.out.println(element);
	}
}
