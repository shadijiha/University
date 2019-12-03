
import java.util.Random;
import java.util.Scanner;

public class LetUsPlay {
	public static int decision;
	public static String play0;
	public static String play1;
	public static int level = 0;
	public static int size = 0;
	static Scanner keyboard = new Scanner(System.in);
	static int[][][] b;
	static int energyAdj = 0;
	static int challengeD = 4;
	static int playerChooser = 2;
	static player player0;
	static player player1;
	static int[] player0R = new int[4];
	static int[] player1R = new int[4];

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int i;
		int[][][] b;
		int l0 = 0;
		int x0 = 0;
		int y0 = 0;
		int energy0 = 10;
		int l1 = 0;
		int x1 = 0;
		int y1 = 0;
		int energy1 = 10;
		String roundContinue;
		for (i = 1; i < 23; i++) {
			System.out.print("* ");
		}
		System.out.println("*");
		for (i = 1; i < 22; i++) {
			System.out.print(" -");
		}
		System.out.println(" -");
		System.out.print("*");
		for (i = 1; i < 22; i++) {
			System.out.print("  ");
		}
		System.out.println(" *");
		System.out.print("*    ");
		System.out.print("WELCOME to Nancy's 3D Warrior Game!");
		System.out.println("    *");
		System.out.print("*");
		for (i = 1; i < 22; i++) {
			System.out.print("  ");
		}
		System.out.println(" *");
		for (i = 1; i < 23; i++) {
			System.out.print("* ");
		}
		System.out.println("*");
		for (i = 1; i < 22; i++) {
			System.out.print(" -");
		}
		System.out.println(" -\n");

		System.out.println(
				"You can use the default board size or change the size\n\t0 to use the default board size\n\t-1 to enter your own size\n");
		System.out.print("==> What do you want to do? ");
		decision = keyboard.nextInt();
		if (decision == 0) {
			board board = new board();
			b = board.board;
			System.out.println("\n\nYour 3D board has been set up and looks like this:\n");
			System.out.println(board);
		}
		if (decision == -1) {
			System.out.print("How many levels would you like? (minimum size 3, max 10) ");
			level = keyboard.nextInt();
			if (level < 3 || level > 10) {
				System.out.println("Sorry but " + level + " is not a legal choice.");
				level = keyboard.nextInt();
			}
			System.out.print(
					"What size do you want the nxn boards on each level to be? \nMinimum size is 3x3, max is 10x10.\n==> Enter the value of n: ");
			size = keyboard.nextInt();
			if (size < 3 || size > 10) {
				System.out.println("Sorry but " + size + " is not a legal choice.");
				size = keyboard.nextInt();
			}
		}
		board board = new board(level, size);
		b = board.board;
		if (level != 0 && size != 0) {
			System.out.println("\n\nYour 3D board has been set up and looks like this:\n");
			System.out.println(board);
		}

		player0 = player0Creator();
		player1 = player1Creator();

		String[] playerArray = new String[2];
		playerArray[0] = play0;
		playerArray[1] = play1;
		Random random = new Random();
		playerChooser = random.nextInt(2);
		System.out.println("The game has started " + playerArray[playerChooser] + " goes first");
		for (i = 0; i < 33; i++) {
			System.out.print("=");
		}
		System.out.println();

		while (player0.won(board) == false || player1.won(board) == false) {
			if (playerChooser == 0) {
				p0Run();
				p1Run();
				System.out.print("Any key to continue to next round ...");
				roundContinue = keyboard.next();
				if (roundContinue != "") {
					continue;
				}
			} else {
				p1Run();
				p0Run();
				System.out.print("Any key to continue to next round ...");
				roundContinue = keyboard.next();
				if (roundContinue != "") {
					continue;
				}
			}

		}

	}

	public static player player0Creator() {
		System.out.print("What is player 0's name (one word only): ");
		play0 = keyboard.next();
		player player0 = new player(play0);
		return player0;
	}

	public static player player1Creator() {
		System.out.print("What is player ]1's name (one word only): ");
		play1 = keyboard.next();
		player player1 = new player(play1);
		return player1;
	}

	public static void normalRoll() {
		dice dice = new dice();
		int sum = 0;
		int energyAdj = 0;
		sum = sum + dice.rollDice();
		if (dice.isDouble() == true) {
			energyAdj = energyAdj + 2;
			System.out.println("Congratulations you rolled double " + dice.die1 + ". Your energy went up by 2 units.");
		}
		dice.toString();
	}

	public static void noEnergyRoll() {
		dice dice = new dice();
		int sum = 0;
		int energyAdj = 0;

		sum = sum + dice.rollDice();
		if (dice.isDouble() == true) {
			energyAdj = energyAdj + 2;
			System.out.println("Congratulations you rolled double " + dice.die1 + ". Your energy went up by 2 units.");
		}
		dice.toString();
		sum = sum + dice.rollDice();
		if (dice.isDouble() == true) {
			energyAdj = energyAdj + 2;
			System.out.println("Congratulations you rolled double " + dice.die1 + ". Your energy went up by 2 units.");
		}
		dice.toString();
		sum = sum + dice.rollDice();
		if (dice.isDouble() == true) {
			energyAdj = energyAdj + 2;
			System.out.println("Congratulations you rolled double " + dice.die1 + ". Your energy went up by 2 units.");
		}
		dice.toString();

	}

	public static int[] movementp0() {
		board board = new board();
		int sum = 0;
		int energyAdj = 0;
		int calculatedX = player0.getX() + sum / 4;
		int calculatedY = player0.getY() + sum % 4;
		int calculatedLevel = player0.getLevel();
		if (calculatedY > board.getSize()) {
			calculatedY = calculatedY % 4;
		}
		if (calculatedX > board.getSize()) {
			calculatedLevel = calculatedLevel + 1;
			calculatedX = (calculatedX + 1) % 4;

		}
		if (calculatedLevel + player0.getLevel() > board.getLevel()) {
			calculatedLevel = 0;
			calculatedX = 0;
			calculatedY = 0;
			energyAdj = -2;
			System.out.println(
					"!!! Sorry you need to stay where you are - that throw takes you off the grid and you \nlose 2 energy.");
		} else {
			energyAdj = board.board[calculatedLevel][calculatedX][calculatedY];
		}
		player0R[0] = calculatedLevel;
		player0R[1] = calculatedX;
		player0R[2] = calculatedY;
		player0R[3] = energyAdj;
		return player0R;
	}

	public static void setMovementp0() {
		movementp0();
		player0.setLevel(player0.getLevel() + player0R[0]);
		player0.setX(player0.getX() + player0R[1]);
		player0.setY(player0.getY() + player0R[2]);
		player0.setEnergy(player0.getEnergy() + player0R[3]);
	}

	public static int[] movementp1() {
		board board = new board();
		int sum = 0;
		int energyAdj = 0;
		int calculatedX = player1.getX() + sum / 4;
		int calculatedY = player1.getY() + sum % 4;
		int calculatedLevel = player1.getLevel();
		if (calculatedY > board.getSize()) {
			calculatedY = calculatedY % 4;
		}
		if (calculatedX > board.getSize()) {
			calculatedLevel = calculatedLevel + 1;
			calculatedX = (calculatedX + 1) % 4;

		}
		if (calculatedLevel + player1.getLevel() > board.getLevel()) {
			calculatedLevel = 0;
			calculatedX = 0;
			calculatedY = 0;
			energyAdj = -2;
			System.out.println(
					"!!! Sorry you need to stay where you are - that throw takes you off the grid and you \nlose 2 energy.");
		} else {
			energyAdj = board.board[calculatedLevel][calculatedX][calculatedY];
		}
		player1R[0] = calculatedLevel;
		player1R[1] = calculatedX;
		player1R[2] = calculatedY;
		player1R[3] = energyAdj;
		return player1R;
	}

	public static void setMovementp1() {
		movementp1();
		player1.setLevel(player1.getLevel() + player1R[0]);
		player1.setX(player1.getX() + player1R[1]);
		player1.setY(player1.getY() + player1R[2]);
		player1.setEnergy(player1.getEnergy() + player1R[3]);
	}

	public static void challengep0() // If player0 on board 1 lands on player1
	{
		movementp0();

		int challenge = (int) (Math.random() * 11);
		if (challenge < 6) {
			player0.setLevel(player0.getLevel());
			player0.setX(player0.getX());
			player0.setY(player0.getY());
			player0.setEnergy(player0.getEnergy() / 2);

		} else {
			int player0NL = player1.getLevel();
			int player0NX = player1.getX();
			int player0NY = player1.getY();
			int player0OL = player0.getLevel();
			int player0OX = player0.getX();
			int player0OY = player0.getY();
			player0.setLevel(player0NL);
			player0.setX(player0NX);
			player0.setY(player0NY);
			player1.setLevel(player0OL);
			player1.setX(player0OX);
			player1.setY(player0OY);
			player1.setEnergy(player1.getEnergy() / 2);
			System.out.println("Bravo!! You won the challenge.");

		}
	}

	public static void setupp0() {
		movementp0();
		int calculatedLevel = player0.getLevel();
		int calculatedX = player0.getX();
		int calculatedY = player0.getY();
		player0.setLevel(player0.getLevel() + calculatedLevel);
		player0.setX(player0.getX() + calculatedX);
		player0.setY(player0.getY() + calculatedY);

	}

	public static void challengep1() // If player1 on board 1 lands on player0
	{
		movementp1();
		System.out.println("Player " + play0 + " is at your new location");
		System.out.println(
				"What do you want to do?\n\t0 - Challenge and risk losing 50% of your energy units if you lose\n\t\tor move to new location and get 50% of other player's energy units.");
		System.out.println("1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy\nunits");
		int challenge = (int) (Math.random() * 11);
		if (challenge < 6) {
			player1.setLevel(player1.getLevel());
			player1.setX(player1.getX());
			player1.setY(player1.getY());
			player1.setEnergy(player1.getEnergy() / 2);

		} else {
			int player1NL = player0.getLevel();
			int player1NX = player0.getX();
			int player1NY = player0.getY();
			int player1OL = player1.getLevel();
			int player1OX = player1.getX();
			int player1OY = player1.getY();
			player1.setLevel(player1NL);
			player1.setX(player1NX);
			player1.setY(player1NY);
			player0.setLevel(player1OL);
			player0.setX(player1OX);
			player0.setY(player1OY);
			player0.setEnergy(player0.getEnergy() / 2);
			System.out.println("Bravo!! You won the challenge.");

		}
	}

	public static void setupp1() {
		movementp1();
		int calculatedLevel = player1.getLevel();
		int calculatedX = player1.getX();
		int calculatedY = player1.getY();
		player1.setLevel(player1.getLevel() + calculatedLevel);
		player1.setX(player1.getX() + calculatedX);
		player1.setY(player1.getY() + calculatedY);

	}

	public static void forfeitChallengep0() {
		player0.setX(0);
		player0.setY(0);
		player0.setEnergy(player0.getEnergy() - 2);

	}

	public static void forfeitChallengep1() {
		player1.setX(0);
		player1.setY(0);
		player1.setEnergy(player1.getEnergy() - 2);

	}

	public static void p0Run() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("It is " + play0 + "'s turn\n\t");
		if (player0.getEnergy() == 0) {
			noEnergyRoll();
			if (player0.getEnergy() == 0) {
				System.out.println("Sorry but you are too weak to move.");
				return;
			} else {
				movementp0();
				if (player0.getLevel() + player0R[0] == player1.getLevel()
						&& player0.getX() + player0R[1] == player1.getX()
						&& player0.getY() + player0R[2] == player1.getY()) {
					if (player0.getLevel() != 0 && player0.getX() != 0 && player0.getY() != 0) {
						System.out.println(
								"What do you want to do?\n\t0 - Challenge and risk losing 50% of your energy units if you lose\n\t\tor move to new location and get 50% of other player's energy units.");
						System.out.println(
								"1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy\nunits");
						while (challengeD != 0 && challengeD != 1) {
							challengeD = keyboard.nextInt();
							System.out.println("Sorry but " + challengeD + "is not a valid response");
						}
						if (challengeD == 0) {
							challengep0();
						} else {
							forfeitChallengep0();
						}

					}
				}
				setMovementp0();
			}
		} else {
			normalRoll();
			movementp0();
			if (player0.getLevel() + player0R[0] == player1.getLevel() && player0.getX() + player0R[1] == player1.getX()
					&& player0R[2] + player0.getY() == player1.getY()) {
				if (player0.getLevel() != 0 && player0.getX() != 0 && player0.getY() != 0) {
					System.out.println(
							"What do you want to do?\n\t0 - Challenge and risk losing 50% of your energy units if you lose\n\t\tor move to new location and get 50% of other player's energy units.");
					System.out.println(
							"1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy\nunits");
					while (challengeD != 0 && challengeD != 1) {
						challengeD = keyboard.nextInt();
						System.out.println("Sorry but " + challengeD + "is not a valid response");
					}
					if (challengeD == 0) {
						challengep0();
					} else {
						forfeitChallengep0();
					}

				}
			}
			setMovementp0();
		}
		player0.toString();
	}

	public static void p1Run() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("It is " + play1 + "'s turn\n\t");
		if (player1.getEnergy() == 0) {
			noEnergyRoll();
			if (player1.getEnergy() == 0) {
				System.out.println("Sorry but you are too weak to move.");
				return;
			} else {
				movementp1();
				if (player1.getLevel() + player1R[0] == player0.getLevel()
						&& player1.getX() + player1R[1] == player0.getX()
						&& player1.getY() + player1R[2] == player0.getY()) {
					System.out.println(
							"What do you want to do?\n\t0 - Challenge and risk losing 50% of your energy units if you lose\n\t\tor move to new location and get 50% of other player's energy units.");
					System.out.println(
							"1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy\nunits");
					while (challengeD != 0 && challengeD != 1) {
						challengeD = keyboard.nextInt();
						System.out.println("Sorry but " + challengeD + "is not a valid response");
					}
					if (challengeD == 0) {
						challengep1();
					} else {
						forfeitChallengep0();
					}

				}

				setMovementp1();
			}
		} else {
			normalRoll();
			movementp1();
			if (player1.getLevel() + player1R[0] == player0.getLevel() && player1.getX() + player1R[1] == player0.getX()
					&& player1R[2] + player1.getY() == player0.getY()) {
				if (player1.getLevel() != 0 && player1.getX() != 0 && player1.getY() != 0) {
					System.out.println(
							"What do you want to do?\n\t0 - Challenge and risk losing 50% of your energy units if you lose\n\t\tor move to new location and get 50% of other player's energy units.");
					System.out.println(
							"1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy\nunits");
					while (challengeD != 0 && challengeD != 1) {
						challengeD = keyboard.nextInt();
						System.out.println("Sorry but " + challengeD + " is not a valid response");
					}
					if (challengeD == 0) {
						challengep1();
					} else {
						forfeitChallengep1();
					}

				}
			}
			setMovementp1();
		}
		player1.toString();
	}
}
