// #include "ShadoMath.java"

package app;

import nancy3D.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Test new Nancy 3D game
		GameObject game = new GameObject("Main_Game");

		String[] names = { "Shadi", "xd" };
		Player players[] = game.initPlayers(names);
		Dice dice = game.initDice();
		Board board = game.initBoard(3, 4);

		System.out.println(dice.toString());

		System.out.print("\n\n\n\n");

	}

}
