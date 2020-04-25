package Lab11; /**
 *
 */

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		LinkedList list = new LinkedList();
		Scanner scan = new Scanner(System.in);

		System.out.println("********* WELCOME TO LINKED LIST LAB **********\n");

		int number = 0;
		boolean ok = false;
		while (!ok) {
			try {
				System.out.print("Enter how many players you wish to enter > ");
				number = Integer.parseInt(scan.next());
				ok = true;
			} catch (Exception e) {
				System.out.println("\nPlease enter a valid number!\n");
			}
		}


		System.out.print("Enter all names separated by a space ' ' (type !auto for auto selected names) > ");
		String allNames = scan.nextLine();
		String[] names = null;

		if (allNames.equalsIgnoreCase("!auto") || allNames.equals("")) {
			names = new String[]{"Winston", "Jean", "Frost", "Jakal", "Tony", "Kevin"};
		} else {
			names = allNames.split(" ");
		}

		System.out.println("\n\nEach player health will be generated randomly between 0 and 1000.\n");


		Player[] players = new Player[number];
		for (int i = 0; i < number; i++) {
			players[i] = new Player(names[i % names.length], random(0, 1000));
			list.insert(players[i]);
			System.out.println("\t Lab11.Player " + i + ":\t" + players[i].toString());
		}

		System.out.println("\nHere is what the Sorted linked list looks like.");
		System.out.println(list.toString() + "\n");

		System.out.println("============== REMOVE METHOD TEST ==============");

		ok = false;
		int toRemove = 0;
		while (!ok) {
			try {
				System.out.print("Enter the index of the player you want to remove (from 0 to " + (number - 1) + ") > ");
				toRemove = Integer.parseInt(scan.next());

				if (toRemove < 0 || toRemove >= number) {
					throw new Exception();
				}
				ok = true;
			} catch (Exception e) {
				System.out.println("Please enter a valid number!\n");
			}
		}

		System.out.println("You removed " + players[toRemove].toString() + ". Your list now looks like that:");
		list.remove(players[toRemove]);
		System.out.println(list.toString());

		scan.close();
	}

	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

}
