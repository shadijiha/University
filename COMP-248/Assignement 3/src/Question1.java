

// -------------------------------------------------------
// Assignment #3
// Written by: Shadi Jiha #40131284
// For COMP 248 Section (your section) – Fall 2019
//
// This program prints how to make coffee at work using programming
// --------------------------------------------------------

import java.util.Random;
import java.util.Scanner;

public class Question1 {	
	
	// Statistic global variable
	static int totalGen = 0;
	static int failNewLine = 0;
	static int failSingle = 0;
	static int failEqual = 0;
	static int failLength = 0;
	static int failUpper = 0;
	static int failLower = 0;
	static int failSpecial = 0;
	
	final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	final static String[] alphabetArray = alphabet.split("");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random random = new Random();
		
		final String[][][] book = {
					{
					{"ALICE was beginning to get very tired of sitting by her sister on the\n",
					"bank, and of having nothing to do. Once or twice she had peeped into the\n",
					"book her sister was reading, but it had no pictures or conversations in\n",
					"it, \"and what is the use of a book,\" thought Alice, \"without pictures or\n",
					"conversations?\"\n"},
					{"So she was considering in her OWN mind (as well as she could, for the\n",
					"day made her feel very sleepy and stupid), whether the pleasure of\n",
					"making a daisy-chain would be worth the trouble of getting up and\n",
					"picking the daisies, when suddenly a White Rabbit with pink eyes ran\n",
					"close by her.\n"},
					{"There was nothing so very remarkable in that, nor did Alice think it so\n",
					"very much out of the way to hear the Rabbit say to itself, \"Oh dear! Oh\n",
					"dear! I shall be too late!\" But when the Rabbit actually took a watch\n",
					"out of its waistcoat-pocket and looked at it and then hurried on, Alice\n",
					"started to her feet, for it flashed across her mind that she had never\n",
					"before seen a rabbit with either a waistcoat-pocket, or a watch to take\n",
					"out of it, and, burning with curiosity, she ran across the field after\n",
					"it and was just in time to see it pop down a large rabbit-hole, under\n",
					"the hedge. In another moment, down went Alice after it!"}
					},
					{
					{"\"WHAT a curious feeling!\" said Alice. \"I must be shutting up like a\n",
					"telescope!\"\n"},
					{"And so it was indeed! She was now only ten inches high, and her face\n",
					"brightened up at the thought that she was now the right size for going\n",
					"through the little door into that lovely garden.\n"},
					{"After awhile, finding that nothing more happened, she decided on going\n",
					"into the garden at once; but, alas for poor Alice! When she got to the\n",
					"door, she found she had forgotten the little golden key, and when she\n",
					"went back to the table for it, she found she could not possibly reach\n",
					"it: she could see it quite plainly through the glass and she tried her\n",
					"best to climb up one of the legs of the table, but it was too slippery,\n",
					"and when she had tired herself out with trying, the poor little thing\n",
					"sat down and cried.\n"},
					{"\"Come, there's no use in crying like that!\" said Alice to herself rather\n", 
					"sharply. \"I advise you to leave off this minute!\" She generally gave\n",
					"herself very good advice (though she very seldom followed it), and\n",
					"sometimes she scolded herself so severely as to bring tears into her\n",
					"eyes.\n"},
					{"Soon her eye fell on a little glass box that was lying under the table:\n",
					"she opened it and found in it a very small cake, on which the words \"EAT\n",
					"ME\" were beautifully marked in currants. \"Well, I'll eat it,\" said\n",
					"Alice, \"and if it makes me grow larger, I CAN reach the key; and if it\n",
					"makes me grow smaller, I can creep under the door: so either way I'll\n",
					"get into the garden, and I don't care which happens!\"\n"},
					{"She ate a little bit and said anxiously to herself, \"Which way? Which\n",
					"way?\" holding her hand on the top of her head to feel which way she was\n",
					"growing; and she was quite surprised to find that she remained the same\n",
					"size. So she set to work and very soon finished off the cake."}
					},
					{
					{"The King and Queen of Hearts were seated on their throne when they\n",
					"arrived, with a great crowd assembled about them--all sorts of little\n",
					"birds and beasts, as well as the whole pack of cards: the Knave was\n",
					"standing before them, in chains, with a soldier on each side to guard\n",
					"him; and near the King was the White Rabbit, with a trumpet in one hand\n",
					"and a scroll of parchment in the other. In the very middle of the court\n",
					"was a table, with a large DISH of tarts upon it. \"I wish they'd get the\n",
					"trial done,\" Alice thought, \"and hand 'round the refreshments!\"\n"},
					{"The judge, by the way, was the King and he wore his crown over his great\n",
					"wig. \"That's the jury-box,\" thought Alice; \"and those twelve creatures\n",
					"(some were animals and some were birds) I suppose they are the jurors.\"\n"},
					{"Just then the White Rabbit cried out \"Silence in the court!\"\n"},
					{"\"HERALD! read the accusation!\" said the King."}
					}
				};
		
		
		//================LOGIC====================
		
		// Welcome line
		System.out.println("Welcome to the password generator game!\n");
		
		
		// Essential variables
		boolean generatorFinished = false;
		String password = "";
		
		while(!generatorFinished)	{

			// Step 2: Generate random numbers
			int page = random.nextInt(book.length);
			int paragraph = random.nextInt(book[page].length);
			int line = random.nextInt(book[page][paragraph].length);
			
			String[] tempLine = book[page][paragraph][line].split(" ");
			
			// Reset words and password
			totalGen++;
			password = generatePassword(tempLine);
			
			// Password attempt failed generate again
			if (password.equals(""))	{
				log(totalGen);
				continue;
			}			
			
			// Display password since it passed all tests
			System.out.printf("Password = %s	Newline = %d	Single = %d	Equal = %d	Length = %d	Upper = %d	Lower = %d	Special = %d\n", password, failNewLine, failSingle, failEqual, failLength, failUpper, failLower, failSpecial);
			
			
			if (totalGen >= 10000 || failLower > 0)	{
				generatorFinished = true;
				break;
			}			

		}
		
		// Bye screen
		System.out.printf("\nTotal passwords generated: %d\n\n", totalGen);
		System.out.println("Thanks for using the password generator game. Good bye.");
		
		//=========================================
		

	}
	
	public static void log(String xd)	{
		System.out.print(xd + "\n");
	}
	
	public static void log(int xd)	{
		System.out.print(xd + "\n");
	}
	
	public static String generatePassword(String[] tempLine)	{
		
		String password = "";
		String[] words = {"", "", ""};
		Random random = new Random();
		
		// Choose 3 words
		for (int i = 0; i < 3; i++)	{
			
			int randomWord = random.nextInt(tempLine.length);

			// Chosen word contains only 1 char
			if (tempLine[randomWord].length() == 1)	{
				failSingle++;
				return "";
			}				
			
			// Chosen word contains \n
			if (tempLine[randomWord].contains("\n"))	{
				failNewLine++;
				return "";
			}
			
			words[i] = tempLine[randomWord];
			
			// Check if word exists is in array
			for (int j = 0; j < words.length; j++)	{
				for (int k = 0; k < words.length; k++)	{
					if (j != k && words[j].equals(words[k]))	{
						// there are 2 words that are the same
						// return to step 2
						failEqual++;
						return "";
					}
				}
			}				
		}
		
		
		// Transform array of words into a whole string
		for (int i = 0; i < words.length; i++)	{
			password += words[i];
		}
		
		// Validate that the password is between 8 and 16 chars
		if (password.length() < 8 || password.length() >= 16)	{
			// return to step 2
			failLength++;
			return "";
		}
		
		// Validate the the password contains at least 1 lower case
		if ( password.equals(password.toLowerCase()))	{
			// return to step 2
			failLower++;
			return "";
		}
		
		// Validate the the password contains at least 1 upper case			
		if (password.equals(password.toUpperCase()))	{
			failUpper++;
			return "";				
		}
		
		// Validate that the password contains at least 1 special char
		int normalCharCount = 0;
		String[] tempPassArray = password.split("");
		for (int i = 0; i < tempPassArray.length; i++)	{
			for (int j = 0; j < alphabetArray.length; j++)	{
				if (tempPassArray[i] == alphabetArray[j])	{
					normalCharCount++;
				}
			}				
		}
		
		if (password.length() - normalCharCount == 0 || password.length() - normalCharCount > 1)	{
			// Password doesn't contain any special char or password contains more than 1 special char
			failSpecial++;
			return "";
		}
		
		return password;
		
	}

}
