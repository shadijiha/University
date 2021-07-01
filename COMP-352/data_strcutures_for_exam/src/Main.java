import my.hashmaps.*;

import java.util.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {

		int[] temp = {16, 27, 22, 59, 44, 32, 59, 33, 32, 71};
		for (int i : temp)
			System.out.printf("%d: %d\n", i, i % 11);

		HashMap_OpenAdressing H = new HashMap_OpenAdressing(11);
		H.setFirstHashFunc((k, size) -> k % 11);
		H.setSecondHashFunc((i, k, size) -> 3 - (k % 3));
		H.put(16);
		H.put(27);
		H.put(22);
		H.put(59);
		H.put(44);
		H.put(32);

		H.remove(59);
		H.put(33);
		H.remove(32);
		H.put(71);

		System.out.println(H.getRawArrayAsString());

		List<String> participants = new ArrayList<>();
		participants.add("Alfred");
		participants.add("Sam");
		participants.add("Roger");
		participants.add("Bob");
		participants.add("Jean");

		int num = 3;
	}

	public static void displayWinners(List<String> participants, int numOfPrices) {

		// Everybody is a winner
		if (participants.size() < numOfPrices) {
			System.out.println(participants);
		}

	}
}
