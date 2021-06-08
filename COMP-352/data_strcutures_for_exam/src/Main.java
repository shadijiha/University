import my.ArrayList;

import java.util.Arrays;

/**
 *
 */

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < 10; i++)
			list.add(i);

		System.out.println(list);
		list.addAt(1, 13);
		System.out.println(list);

		list.remove(13);
		System.out.println(list);

		list.removeAt(1);
		System.out.println(list);

		System.out.println(Arrays.toString(list.rawArray()));
	}
}
