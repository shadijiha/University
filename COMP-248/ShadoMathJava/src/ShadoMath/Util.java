package ShadoMath;

import java.awt.*;

public class Util {

	public static double random(double min, double max) {
		return Math.floor(Math.random() * (max - min) + min);
	}

	public static int random(int min, int max) {
		return (int) random((double) min, (double) max);
	}

	public static Color randomColor() {
		return new Color(random(0, 255), random(0, 255), random(0, 255));
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
