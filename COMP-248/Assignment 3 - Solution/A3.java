import java.util.Random;

/**
 Program to select your a random password.  
 See A3 Handout for full description
 Author: M. Mannan.
 E-mail Address: m.mannan@concordia.ca
 Last Changed: October 2, 2019.
*/

public class A3
{
	
	public static void main(String[] args)
    {
		System.out.println("\nWelcome to the password generator game!\n");
		
        
		// http://www.gutenberg.org/cache/epub/19033/pg19033.txt
		// The source of words for password generation
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
		
		
		final int TOTAL = 10000; // total passwords to be generated
		final int WORDLEN = 16;  // formatting the password
		final int COUNTLEN = 3;  // formatting the password
		
		int ntotal = 0;
		int nnewline, nlen1, nequal, nlen, nupper, nlower, nspecial;
		
		nnewline = nlen1 = nequal = nlen = nupper = nlower = nspecial = 0;
		String arrWords[] = new String[3];
		
		String pwd = "";
		while (true)
		{
			pwd = "";
			for (int i = 0; i < 3;)
			{
				Random r = new Random();
		    	int page = r.nextInt(book.length);
		    	int para = r.nextInt(book[page].length);
		    	int line = r.nextInt(book[page][para].length);
		    	
		    	String arrLine[] = book[page][para][line].split(" ");
		    	int word = r.nextInt(arrLine.length);
		    	
		    	// no single character word
		    	if (arrLine[word].length() == 1)
		    	{
		    		nlen1++;
		    		continue;
		    	}
		    	
		    	//no words with new line
		    	if (arrLine[word].contains("\n"))
		    	{
		    		nnewline++;
		    		continue;
		    	}
		    	
		    	pwd += arrLine[word];
		    	arrWords[i++] = new String(arrLine[word]);
			}
		
			//System.out.println("Password candidate = " + pwd);
			
			// Don't use HashSet
			// Repeated word checks
			if ( (arrWords[0].compareTo(arrWords[1]) == 0) || 
					(arrWords[1].compareTo(arrWords[2]) == 0) || 
					(arrWords[0].compareTo(arrWords[2]) == 0))
			{
				nequal++;
				continue;
			}
			
			// Length check
			boolean blen = (pwd.length() >=8) && (pwd.length() < 16);
			if (!blen) {nlen++; continue;}
			
			boolean bupper = checkUpper(pwd);
			if (!bupper) {nupper++; continue;}
			
			boolean blower = checkLower(pwd);
			if (!blower) {nlower++; continue;}
			
			// any character other than alphabets
			boolean bspecial = checkSpecial(pwd);
			if (!bspecial) {nspecial++; continue;}
			
			System.out.println("Password = " + 	String.format("%-" + WORDLEN + "." + WORDLEN + "s", pwd) 
					+ " Newline = " + 			String.format("%-" + COUNTLEN + "." + COUNTLEN + "s", nnewline)  
					+ " Single = " + 			String.format("%-" + COUNTLEN + "." + COUNTLEN + "s", nlen1)
					+ " Equal = " + 			String.format("%-" + COUNTLEN + "." + COUNTLEN + "s", nequal)
					+ " Length = " + 			String.format("%-" + COUNTLEN + "." + COUNTLEN + "s", nlen)
					+ " Upper = " + 			String.format("%-" + COUNTLEN + "." + COUNTLEN + "s", nupper)
					+ " Lower = " + 			String.format("%-" + COUNTLEN + "." + COUNTLEN + "s", nlower)
					+ " Special = " + 			String.format("%-" + COUNTLEN + "." + COUNTLEN + "s", nspecial));
			
			
			// check termination conditions
			if ((ntotal++ == TOTAL) || nlower > 0) 
				break;
			
			// reset stat variables
			nnewline = nlen1 = nequal = nlen = nupper = nlower = nspecial = 0;
		} 
		
		System.out.println("\nTotal passwords generated: " + (ntotal-1));
    	System.out.println("\nThanks for using the password generator game. Good bye.");
    	
    }
	
	// method for checking any upper case letters
	private static boolean checkUpper(String str) {
	    for(int i=0;i < str.length();i++)
	    {
	    	char c = str.charAt(i);
	    	if (c >= 'A' && c <= 'Z')
	            return true;
	    }
	    return false;
	}
	
	// method for checking any lower case letters
	private static boolean checkLower(String str) {
	    for(int i=0;i < str.length();i++)
	    {
	    	char c = str.charAt(i);
	    	if (c >= 'a' && c <= 'z')
	            return true;
	    }
	    return false;
	}

	// method for checking non-alpha characters
	// only one special character is allowed
	private static boolean checkSpecial(String str) {
		int count = 0;
	    for(int i=0;i < str.length();i++)
	    {
	    	char c = str.charAt(i);
	    	if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) 
	            count++;
	    }
	    
	    return count == 1;
	}
}



