/***
 * 
 * Driver class
 * */

package app;

import java.util.ArrayList;
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

		Integer x[] = { 1, 2, 3, 4, 5 };

		System.out.println(test(x));
	}

	private static int test(Integer[] array) {
		List<Integer> x = toList(array);

		return x.stream().filter(e -> e < 5).reduce((acc, e) -> acc += e).orElse(-1);
	}

	private static <T> List<T> toList(T[] array) {
		List<T> result = new ArrayList<T>();
		for (final T temp : array) {
			result.add(temp);
		}
		return result;
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
