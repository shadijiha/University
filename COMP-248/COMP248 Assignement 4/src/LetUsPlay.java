/***
 * 
 *  Main java file for "Nancy's 3D Warrior Game"
 * 
 */

import java.util.Scanner;
import java.util.Random;

public class LetUsPlay {
	
	final static boolean DEBUG_MODE = false;

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
		println("\nYour 3D board has been set up and looks like this:\n");
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
		
		boolean gameOver = false;
		Player winner = new Player();
		
		while (!gameOver)	{
			
			println(String.format("\n\nIt is %s's turn", players[turn].getName()));
			dice.rollDice();
			println(String.format("	%s you rolled %s", players[turn].getName(), dice.toString()));
			
			// Roll and Adjust energy based on moved <===== THIS SHOULD BE MODIFIED
			players[turn].setEnergy(adjustEnergy(players[turn], dice));		
			
			int energyOnCurrentPos = board.getEnergyAdj(players[turn].getLevel(), players[turn].getX(), players[turn].getY());
			
			// Change current player location
			players[turn] = movePlayer(players[turn], board, dice, players[Math.abs(turn - 1)]);
			
			// Adjust player energy
			players[turn].setEnergy(players[turn].getEnergy() + energyOnCurrentPos);
			
			println(String.format("	Your energy is adjusted by %d for landing at (%d,%d) at level %d", energyOnCurrentPos, players[turn].getX(), players[turn].getY(), players[turn].getLevel()));
			
			// Display end of round results
			println("At the end of this round: ");
			println(String.format("	%s is on level %d at location (%d,%d) and has %d units of energy", players[0].getName(), players[0].getLevel(), players[0].getX(), players[0].getY(), players[0].getEnergy()));
			println(String.format("	%s is on level %d at location (%d,%d) and has %d units of energy.", players[1].getName(), players[1].getLevel(), players[1].getX(), players[1].getY(), players[1].getEnergy()));
			
			// Pause
			print("Any key to continue to next round ...");
			String pause = scan.next();			
			
			// Determine if a player has won at the end of the round
			for (Player temp : players)	{
				if (temp.getLevel() >= board.getLevel() && temp.getX() == board.getSize() && temp.getY() == board.getSize())	{
					winner = new Player(temp);
					gameOver = true;
				}
			}			
			
			// Advance turn
			turn = nextTurn(turn);			
		}
		
		// Display end message
		println("The winner is " + winner.getName() + ". Well done!!!");	

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
	
	public static Player movePlayer(Player p, Board b, Dice d, Player opponent)	{
		
		if (!hasEnoughEnergy(p))	{
			return p;
		} else	{
			
			Player temp = new Player(p);
			boolean allowMove = true;
			
			int roll = d.getDie1() + d.getDie2();	// Roll the dice
			
			// Modify TEMP player x and y
			int newX = temp.getX() + (roll / b.getSize());
			int newY = temp.getY() + (roll % b.getSize());
			
			if (newY > b.getSize() - 1)	{
				newX = newX + newY / b.getSize();
				newY = newY % b.getSize();
			}
			
			if (newX > b.getSize() - 1)	{
				
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
					// the new position lead to a position occupied by the opponent
					
					String action = "forfeit";
	
					if (action.equalsIgnoreCase("forfeit"))	{
						
						/* ******** Code to forfeit ******** */
						
						if (playerWithNewPos.getLevel() == 0)	{
							// Move player to (0, 0)
							playerWithNewPos.setX(0);
							playerWithNewPos.setY(0);
						} else	{
							// Decrease level
							playerWithNewPos.setLevel(playerWithNewPos.getLevel() - 1);
						}
						
					} else if (action.equalsIgnoreCase("challenge"))	{
						
						/* ******** Code to challenge ******** */
						int challengeResult = new Random().nextInt(11);
						
						if (challengeResult < 6)	{
							// A has lost
							playerWithNewPos.setEnergy(playerWithNewPos.getEnergy() / 2);
						} else	{
							// Swap A and B
							playerWithNewPos.setX(opponent.getX());
							playerWithNewPos.setX(opponent.getY());
							
							opponent.setX(p.getX());
							opponent.setY(p.getY());
							opponent.setEnergy(opponent.getEnergy() / 2);					
						}
						
					}					
				}
				
				return playerWithNewPos;
			} else {
				return p; // <========== CHANGE THIS
			}		
		}		
	}
	
	public static int nextTurn(int currentTurn)	{
		return Math.abs(currentTurn - 1);
	}
	
	public static boolean hasEnoughEnergy(Player p)	{
		return p.getEnergy() > 0;
	}
	
	public static int adjustEnergy(Player p, Dice d)	{
		
		Player tempCopy = new Player(p);
		
		if (!hasEnoughEnergy(tempCopy))	{
			// roll the dice 3 times
			for (int i = 0; i < 3; i++)	{
				d.rollDice();
				if (d.isDouble())	{
					tempCopy.setEnergy(tempCopy.getEnergy() + 2);
				}
			}
		} else	{
			// Add 2 energy if the roll is a double
			if (d.isDouble())	{
				tempCopy.setEnergy(tempCopy.getEnergy() + 2);
				println("	Congratulations you rolled doube " + d.getDie1() + ". Your energy went up by 2 units");
			}
		}
		
		return tempCopy.getEnergy();

	}
	
	public static <T> void print(T x)	{
		System.out.print(x);
	}
	
	public static <T> void println(T x)	{
		System.out.println(x);
	}

}
