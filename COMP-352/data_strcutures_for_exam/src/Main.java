import my.hashmaps.HashMap_OpenAdressing;

/**
 *
 */

public class Main {

	public static void main(String[] args) {

		HashMap_OpenAdressing map = new HashMap_OpenAdressing();

		int[] test = {37, 17, 24, 36, 62, 28, 58, 47, 19};

		int[] insert = {245, 28, 10, 49, 70, 225, 122, 12, 180, 140, 177, 65, 223, 85, 111, 256, 18, 69, 59, 185, 105, 120, 44};
//		for (int e : test)
		//map.put(e, e);

		map.put(37);
		map.put(17);
		map.put(24);
		map.put(36);
		map.remove(62);
		map.remove(28);
		map.put(58);
		map.put(47);
		map.remove(19);

		System.out.println(map);
		System.out.println("CLuster " + map.getHighestCluster());

		System.out.println(map.getNumberOfCollisions());

	}
}
