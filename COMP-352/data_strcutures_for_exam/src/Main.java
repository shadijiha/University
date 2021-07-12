import java.util.*;

/**
 *
 */

public class Main {

	static Random random = new Random();

	public static void main(String[] args) {

		/*int[] temp = {16, 27, 22, 59, 44, 32, 59, 33, 32, 71};
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
		H.put(71);*/

		List<String> participants = new ArrayList<>();
		participants.add("Alfred");
		participants.add("Sam");
		participants.add("Roger");
		participants.add("Bob");
		participants.add("Jean");

		int num = 3;
		displayWinners(participants, 3, new ArrayList<>());
	}

	public static void displayWinners(List<String> A, int p, List<String> C) {

		int n = A.size();
		if (n <= p) {
			System.out.println(A);
			return;
		}

		int randomStart = random.nextInt(n);
		int radomEnd = random.nextInt(n);
		displayWinnersUtil(A, new ArrayList<>(), Math.min(randomStart, radomEnd), Math.max(randomStart, radomEnd), 0, p);
	}

	public static void displayWinnersUtil(List<String> A, List<String> Temp, int start, int end, int index, int p) {

		if (index == p)
			System.out.println(Temp);

		for (int i = start; i <= end; i++) {
			Temp.add(A.get(i));
			displayWinnersUtil(A, Temp, i + 1, end, index + 1, p);
		}

	}
}
