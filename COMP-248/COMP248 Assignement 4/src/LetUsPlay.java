/***
 * 
 *  Main java file for "Nancy's 3D Warrior Game"
 * 
 */

import java.util.Scanner;
import java.util.Random;

public class LetUsPlay {
	
	final static boolean DEBUG_MODE = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Variables
		Scanner scan = new Scanner(System.in);
		Random random = new Random();
		int userChoice = 0;		
		int userLevelCount = 3;	
		int userSize = 4;
		Board board;
		Player[] players = new Player[2];
		Dice dice = new Dice();
		
		// Display welcome banner
		println("		*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
		println("		*					    *");
		println("		*    WELCOME to Nancy's 3D Warrior Game!    *");
		println("		*					    *");
		println("		*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
		
		println("The default game board has 3 levels and each level has a 4x4 board.");
		println("You can use this default board size or change the size");
		println("	0 to use the default board size");
		println("	-1 to enter your own size");
		print("==> What do you want to do? ");
		
		// Chose default or costume
		do	{
			userChoice = scan.nextInt();
			if (userChoice > 0 || userChoice < -1)	{
				println(String.format("Sorry but %d is not a legal choice.", userChoice));
			}			
		} while (userChoice > 0 || userChoice < -1);
		
		// If user want to enter costume level and size
		if (userChoice == -1)	{
			
			// Get user's level
			print("How many levels would you like? (minimum size 3, max 10) ");		
			do {
				userLevelCount = scan.nextInt();
				if (userLevelCount < 3 || userLevelCount > 10)	{
					println(String.format("Sorry but %d is not a legal choice.", userLevelCount));
				}			
			} while (userLevelCount < 3 || userLevelCount > 10);
			
			// Get users board size
			println("\nWhat size do you want to nxn boards on each level to be?");
			println("Minimum size is 4x4, max is 10x10.");
			print("==> Enter the value of n: ");
			do {
				userSize = scan.nextInt();
				if (userSize < 4 || userSize > 10)	{
					println(String.format("Sorry but %d is not a legal choice.", userSize));
				}			
			} while (userSize < 4 || userSize > 10);
		}		
		
		// Everything OK set up the board
		board = new Board(userLevelCount, userSize);
		println("\nYour 3D board has been set up and looks like this:\n\n");
		println(board.toString());
		
		// get players names
		for (int i = 0; i < players.length; i++)	{
			print(String.format("What is player %d's name (one word only): ", i));
			String tempName = scan.next();
			
			players[i] = new Player(tempName);
		}
		
		/* START OF THE GAME */
		int turn = random.nextInt(2);
		println(String.format("\nThe game has started %s goes first", players[turn].getName()));
		println("==================================");
		
		
		//============= GAME LOGIC =============
		debugger(players[turn]);
		
		// If player if out of energy generate some
		if (!hasEnoughEnergy(players[turn]))	{
			generateEnergy(players[turn], dice);
		}
		
		movePlayer(players[turn], board, dice, players[Math.abs(turn - 1)]);
		turn = nextTurn(turn);
	

		//======================================
		scan.close();		
	}
	
	public static void debugger(Player p)	{
		if (DEBUG_MODE)	{
			
			// Do debug stuff here
			p.setEnergy(0);
			println(String.format("%s now has %d energy because of DEBUG mode", p.getName(), p.getEnergy()));
		}
	}
	
	public static void movePlayer(Player p, Board b, Dice d, Player opponent)	{
		
		if (!hasEnoughEnergy(p))	{
			return;
		} else	{
			
			Player temp = new Player(p);
			boolean allowMove = true;
			
			int roll = d.rollDice();	// Roll the dice
			
			// Modify TEMP player x and y
			int newX = temp.getX() + (roll / b.getSize());
			int newY = temp.getY() + (roll % b.getSize());
			
			if (newY > b.getSize() - 1)	{
				newX = newX + newY / b.getSize();
				newY = newY % b.getSize();
			}
			
			if (newX > b.getSize())	{
				
				// if x is off board increase the level
				if (temp.getLevel() + 1 >= b.getLevel())	{
					temp.setEnergy(temp.getEnergy() - 2);
					allowMove = false;
				} else	{
					temp.setLevel(temp.getLevel() + 1);
				}				
				
				newX = 6 % b.getSize();			
			}
			
			// Potential location (newX, newY);
			if (allowMove)	{
				
				Player playerWithNewPos = new Player(temp);
				playerWithNewPos.setX(temp.getX() + newX);
				playerWithNewPos.setY(temp.getY() + newY);
				
				if (playerWithNewPos.equals(opponent))	{
					// the new position lead to a position occupied by the opponant
					
				}
				
				temp.setX(temp.getX() + newX);
				temp.setY(temp.getY() + newY);
			}			
		}		
	}
	
	public static int nextTurn(int currentTurn)	{
		return Math.abs(currentTurn - 1);
	}
	
	public static boolean hasEnoughEnergy(Player p)	{
		return p.getEnergy() > 0;
	}
	
	public static int generateEnergy(Player p, Dice d)	{
		//Player temp = new Player(p);
		// roll the dice 3 times
		for (int i = 0; i < 3; i++)	{
			d.rollDice();
			if (d.isDouble())	{
				p.setEnergy(p.getEnergy() + 2);
			}
		}			
	
		return p.getEnergy();
	}
	
	public static <T> void print(T x)	{
		System.out.print(x);
	}
	
	public static <T> void println(T x)	{
		System.out.println(x);
	}

}
