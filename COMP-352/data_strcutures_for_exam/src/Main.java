import my.HashMap;

/**
 *
 */

public class Main {

	public static void main(String[] args) {


		HashMap map = new HashMap();

		int[] insert = {245, 28, 10, 49, 70, 225, 122, 12, 180, 140, 177, 65, 223, 85, 111, 256, 18, 69, 59, 185, 105, 120, 44};
		for (int e : insert)
			map.put(e, e);

		System.out.println(map.getRawArrayAsString());
		System.out.println(map.getNumberOfCollisions());
	}
}
