/***
 * 
 * Driver class
 * */

package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Main {

	public static final void main(String[] args) {

		// TODO:
//		JFrame window = new JFrame();
//		window.setSize(640, 480);
//		window.setTitle("Java GUI test");
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setLocationRelativeTo(null);
//		window.setVisible(true);
//
//		DrawingComponent DC = new DrawingComponent();
//		window.add(DC);
//		Engin3D.Mesh test = new Engin3D.Mesh();
//
//		SetInterval(function() {}, 1000);
//		new Timer().scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				print("?XD");
//			}
//		}, 0, 1000);

		List<Integer> A = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> B = Arrays.asList(6, 7, 8, 9, 10);

		List<List<Integer>> R = new ArrayList<List<Integer>>();

		/*
		 * for (int a : A) { for (int b : B) { R.add(Arrays.asList(a, b)); } }
		 */

		A.stream().forEach(a -> {
			B.stream().forEach(b -> R.add(Arrays.asList(a, b)));
		});

		R.stream().forEach(System.out::println);
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
