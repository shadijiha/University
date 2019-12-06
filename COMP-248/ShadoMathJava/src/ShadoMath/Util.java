package ShadoMath;

import java.awt.*;

public class Util {

	public static int random(int min, int max) {
		return (int) Math.floor(Math.random() * (max - min) + min);
	}

	public static Color randomColor() {
		return new Color(random(0, 255), random(0, 255), random(0, 255));
	}

}
