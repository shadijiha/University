/***
 * 
 *  Main java file for "Nancy's 3D Warrior Game"
 * 
 */

import java.util.Scanner;
import java.util.Random;

public class LetUsPlay {

	final static Logger DEBUGGER = new Logger();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Variables
		Scanner scan = new Scanner(System.in);
		Random random = new Random();		
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
		String userChoice = "0";		// Here using a string instead of int to avoid app crash if user enters a string to scan.nextInt()
		do	{
			userChoice = scan.next();
			if (!userChoice.equalsIgnoreCase("0") && !userChoice.equalsIgnoreCase("-1"))	{
				println(String.format("Sorry but %s is not a legal choice.", userChoice));
			}			
		} while (!userChoice.equalsIgnoreCase("0") && !userChoice.equalsIgnoreCase("-1"));
		
		// If user want to enter costume level and size
		if (userChoice.equalsIgnoreCase("-1"))	{
			
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
		Player winner = null;
		
		while (winner == null)	{
			
			println(String.format("\n\nIt is %s's turn", players[turn].getName()));
			dice.rollDice();
			println(String.format("	%s you rolled %s", players[turn].getName(), dice.toString()));
			
			// Roll and Adjust energy based on moved <===== THIS SHOULD BE MODIFIED
			players[turn].setEnergy(adjustEnergy(players[turn], dice));		
			
			// Change current player location
			movePlayer(players[turn], board, dice, players[Math.abs(turn - 1)], scan);
			
			// Display end of round results
			println("At the end of this round: ");
			println(String.format("	%s is on level %d at location (%d,%d) and has %d units of energy", players[0].getName(), players[0].getLevel(), players[0].getX(), players[0].getY(), players[0].getEnergy()));
			println(String.format("	%s is on level %d at location (%d,%d) and has %d units of energy.", players[1].getName(), players[1].getLevel(), players[1].getX(), players[1].getY(), players[1].getEnergy()));
			
			// Pause
			print("Any key to continue to next round ...");
			String pause = scan.next();	
			DEBUGGER.debugMethodes(pause);
			
			// Determine if a player has won at the end of the round
			for (Player temp : players)	{
				if (temp.won(board))	{
					winner = temp.copy();
					break;
				}
			}			
			
			// Advance turn
			turn = nextTurn(turn);			
		}
		
		// Display end message
		println("\n\nThe winner is " + winner.getName() + ". Well done!!!");	

		//======================================
		scan.close();
	}
	
	public static void movePlayer(Player p, Board b, Dice d, Player opponent, Scanner scanner)	{
		
		if (!hasEnoughEnergy(p))	{
			return;
		} else	{
			
			Player temp = p.copy();
			boolean allowMove = true;
			
			int roll = d.sumOfDice();
			
			// Modify TEMP player x and y
			int newX = temp.getX() + (roll / b.getSize());
			int newY = temp.getY() + (roll % b.getSize());

			if (newY >= b.getSize())	{
				newX = newX + newY / b.getSize();
				newY = newY % b.getSize();
			}

			if (newX >= b.getSize())	{
				newX = newX % b.getSize();
				temp.setLevel(temp.getLevel() + 1);
			}

			if ( temp.getLevel() < b.getLevel())	{
				allowMove = true;
			} else	{

				// MAX LEVEL cannot move
				temp.setEnergy(temp.getEnergy() - 2);
				allowMove = false;
				println("!!! Sorry you need to stay where you are - That throws you off the grid and you lose 2 units of energy");

				p.setEnergy(temp.getEnergy());
				return;
			}

			if (allowMove)	{

				temp.setX(newX);
				temp.setY(newY);

				// Challenge
				if (temp.equals(opponent))	{

					// the new position lead to a position occupied by the opponent
					// Prompt the user to get his choice					
					String action = "-1";

					println("Player " + opponent.getName() + " is at your new location");
					println("What do you want to do?");
					println("\t0 - Challenge and risk loosing 50% of your energy units if you loose or move to new location and get 50% of ther players energy units");
					println("\t1 - to move down one lovel or move to (0,0) if at level 0 and lose 2 energy units");

					do	{
						action = scanner.next();

						if (!action.equalsIgnoreCase("0") && !action.equalsIgnoreCase("1"))	{
							println("Sorry but " + action + " is not a legal choice.");
						}

					} while(!action.equalsIgnoreCase("0") && !action.equalsIgnoreCase("1"));
	
					if (action.equalsIgnoreCase("1"))	{
						
						/* ******** Code to forfeit ******** */
						
						if (temp.getLevel() == 0)	{
							// Move player to (0, 0)
							temp.setX(0);
							temp.setY(0);
						} else	{
							// Decrease level
							temp.setLevel(temp.getLevel() - 1);
						}
						
					} else if (action.equalsIgnoreCase("0"))	{
						
						/* ******** Code to challenge ******** */
						int challengeResult = new Random().nextInt(11);
						
						if (challengeResult < 6)	{
							// A has lost
							temp.setEnergy(temp.getEnergy() / 2);
							println("Sorry, you lost the challenge. You lost " + temp.getEnergy() + " energy :(");

						} else	{
							// Swap A and B
							println("Bravo!! You won the challenge.");

							int energyToTransfer = opponent.getEnergy() / 2;

							temp.setX(opponent.getX());
							temp.setX(opponent.getY());
							temp.setEnergy(temp.getEnergy() + energyToTransfer);
							
							opponent.setX(p.getX());
							opponent.setY(p.getY());
							opponent.setEnergy(opponent.getEnergy() - energyToTransfer);					
						}
						
					}					
				}

				p.moveTo(temp);		// Move player then exit function

				// Adjust player energy
				int energyOnCurrentPos = b.getEnergyAdj(temp.getLevel(), temp.getX(), temp.getY());
				temp.setEnergy(temp.getEnergy() + energyOnCurrentPos);
			
				println(String.format("	Your energy is adjusted by %d for landing at (%d,%d) at level %d", energyOnCurrentPos, temp.getX(), temp.getY(), temp.getLevel()));

				p.setEnergy(temp.getEnergy());

				return;
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
		
		Player tempCopy = p.copy();
		
		if (!hasEnoughEnergy(tempCopy))	{
			// roll the dice 3 times
			int tempEnergyGained = 0;
			final int REROLLS_ALLOWED = 3;
			for (int i = 0; i < REROLLS_ALLOWED; i++)	{
				d.rollDice();
				if (d.isDouble())	{
					tempCopy.setEnergy(tempCopy.getEnergy() + 2);
					tempEnergyGained += 2;
				}
			}
			println(String.format("%s did not have enough energy to move. He gains a chance to get 2 bonus energy units if he rolls a double. This process is done 3 times for up to %d energy units. %s has gained %d bonus energy units.\n", tempCopy.getName(), REROLLS_ALLOWED * 2, tempCopy.getName(), tempEnergyGained));

		} else	{
			// Add 2 energy if the roll is a double
			if (d.isDouble())	{
				tempCopy.setEnergy(tempCopy.getEnergy() + 2);
				println("	Congratulations! you rolled double " + d.getDie1() + ". Your energy went up by 2 units");
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
