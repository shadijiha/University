/**
 * @author Shadi Jiha
 * @Date April 22nd, 2020
 */

package FX_W20PKG;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import CargoAirport.CargoAirport;
import CommerialAirport.CommercialAirport;

public class Infrastructure {

	public static void main(String[] args) {
		
		/*******************************************************
		 * ********************* TASK 3 ************************
		 * ************************************************** */
		
		// ******************** TASK 3A *************************
		Structure[] strArr = new Structure[6 * 6];
		System.out.println("************ Start of task 3A: ************ \n");
		
		for (int i = 0; i < strArr.length; i += 6)	{
			strArr[i] = new Overpass(random(1950, 2010), random(5e6, 10e6), random(300, 600), "Metal", random(500, 900), random(20, 30), random(15000, 40000));
			strArr[i + 1] = new HighRise(random(1960, 2020), random(10e6, 20e6), random(200, 300), "Concret", random(80, 120));
			strArr[i + 2] = new House(random(1980, 2020), random(110_000, 200_000), random(80, 120), "Wood", random(5, 10), random(3, 7) + 0.5, random(1, 3));
			strArr[i + 3] = new CondoBuilding(random(1980, 2020), random(550_000, 2_000_000), random(150, 300), "Concret", random(50, 120), random(10, 24));
			strArr[i + 4] = new CommercialAirport(random(1950, 2000), random(100_000_000, 200_000_000), random(2, 4), random(100_000, 999_999), random(5, 10));
			strArr[i + 5] = new CargoAirport(random(1950, 2000), random(100_000_000, 200_000_000), random(2, 4), random(100_000, 999_999), random(100_000_001, 300e6));
		}
		
		System.out.println("************ End of task 3A ************ ");
		System.out.println("===================================");
		
		// ******************** TASK 3B *************************
		System.out.println("************ Start of task 3B ************ \n");
	
		// This was already done in A
		
		System.out.println("************ End of task 3B ************ ");
		
		// ******************** TASK 3C *************************
		System.out.println("===================================");
		
		System.out.println("************ Start of task 3C ************ \n");
		
		int in = findTallestHighRise(strArr);
		if (in!=-  1) {
			System.out.println("Tallest HighRise was found at index: " + in + " Here is the info of that object");
			System.out.println(strArr[in]);
		} else	{
			System.out.println("No HighRise objects were found in the array!");
		}			
		
		System.out.println("************ End of task 3C ************ ");
		
		// ******************** TASK 3D *************************
		System.out.println("===================================");
		
		System.out.println("************ Start of task 3D ************ \n");
	
		showBuildingInfo(strArr);
		
		System.out.println("************ End of task 3D ************ ");
		
		// ******************** TASK 3E *************************
		System.out.println("===================================");
				
		System.out.println("************ Start of task 3E ************ \n");
			
		displayAllObjects(strArr);
				
		System.out.println("\n************ End of task 3E ************ ");
		System.out.println("===================================");
		
		// ******************** TASK 3F *************************
		System.out.println("************ Start of task 3F ************ \n");
			
		Structure[] copy = copyStructures(strArr);
		displayAllObjects(copy);
				
		System.out.println("\n************ End of task 3F ************ ");
		System.out.println("===================================");
		
		/*******************************************************
		 * ********************* TASK 4 ************************
		 * ************************************************** */
		
		// ******************** TASK 4A *************************
		System.out.println("************ Start of task 4A ************ \n");
		
		ArrayList<Structure> strArrLst = new ArrayList<Structure>(30);
		
		System.out.println("************ End of task 4A ************ ");
		System.out.println("===================================");
		
		// ******************** TASK 4B *************************
		System.out.println("************ Start of task 4B ************ \n");
		
		strArrLst.addAll(Arrays.asList(strArr));	
		
		System.out.println("************ End of task 4B ************ ");
		System.out.println("===================================");
		
		// ******************** TASK 4C *************************
		System.out.println("************ Start of task 4C ************ \n");
		
		for (int i = 0; i < 5; i++)	{
			strArrLst.remove(random(0, strArrLst.size() - 1));
		}
		
		System.out.println("************ End of task 4C ************ ");
		System.out.println("===================================");
		
		// ******************** TASK 4D *************************
		System.out.println("************ Start of task 4D ************ \n");
		
		for (Structure e : strArr)	{
			if (strArrLst.contains(e))	{
				System.out.printf("The following Object is still in the array at index " + strArrLst.indexOf(e) + ": " + e + "\n");
			} else	{
				System.out.printf("The following Object was REMOVED! " + e + "\n");
			}
		}
		
		System.out.println("************ End of task 4D ************ ");
		System.out.println("===================================");
		
		/*******************************************************
		 * ********************* TASK 5 ************************
		 * ************************************************** */
		// ******************** TASK 5A and B and C *************************
		System.out.println("************ Start of task 5 ************ \n");
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("AirportCodes.txt"));
			
			findExistingAirportCodes(writer, strArr);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			writer.close();
		}
		
		// ******************** TASK 5D and E and F *************************
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("AirportCodes.txt"));	
			
			displayAirportCodes(reader);			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the Buffer
			try {
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println("************ End of task 5 ************ ");
		System.out.println("===================================");		
		/*******************************************************
		 * ********************* TASK 6 ************************
		 * ************************************************** */
		System.out.println("************ Start of task 6 ************ \n");
		System.out.println("---> Adding stuff to StructureList:");
		StructureList list1 = new StructureList();
		list1.addToStart(strArr[0]);
		list1.addToStart(strArr[1]);
		list1.addToEnd(strArr[2]);
		
		list1.showListContents();
		
		System.out.println("\n---> Cloning the StructureList:");
		StructureList list2 = (StructureList) list1.clone();
		list2.showListContents();
		
		
		System.out.println("\n---> Appending to the StructureList:");
		list1.append(list2);
		list1.showListContents();
		
		System.out.println("************ End of task 6 ************");
		System.out.println("===================================");	
	}

	/**
	 * Finds the heighest HighRise Object in a strucure array
	 * @param array The strucutre array to search
	 * @return Returns The index of that building OR -1 if no HighRise Objects are present
	 */
	public static int findTallestHighRise(Structure[] array)	{
		
		double heighestNum = 0.0;
		int index = -1;
		
		for (int i = 0; i < array.length; i++)	{
			if (array[i] instanceof HighRise)	{
				HighRise temp = (HighRise)array[i];
				if (temp.getHeight() > heighestNum)	{
					heighestNum = temp.getHeight();
					index = i;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Prints only the instance of Building Class in an array of structures to the console
	 * @param array The structure array
	 */
	public static void showBuildingInfo(Structure[] array)	{	
		for (Structure e : array)	{
			if (e instanceof Building)	{
				System.out.println(e);
			}
		}		
	}
	
	/**
	 * Displays All the content of a structure array to the Console backwards
	 * @param array The structure array
	 */
	public static void displayAllObjects(Structure[] array)	{
		displayAllObjects(array, array.length - 1);
	}
	
	private static void displayAllObjects(Structure[] array, int iteration)	{
		if (iteration < 0)	{
			return;
		}
		System.out.println("Index " + iteration + ": " + array[iteration]);
		displayAllObjects(array, iteration - 1);
	}
	
	/**
	 * Copies all the content of a Structure array
	 * @param array The array to copy
	 * @return Returns a deep copy of all the objects in the structure array
	 */
	public static Structure[] copyStructures(Structure[] array)	{
		Structure[] copy = new Structure[array.length];
		
		for (int i = 0; i < array.length; i++)
			copy[i] = array[i].clone();
		
		return copy;
	}
	
	/**
	 * Finds all the airport Objects and writes their codes to a file
	 * @param writer The FileWriter
	 * @param array The array to search
	 */
	public static void findExistingAirportCodes(PrintWriter writer, Structure[] array)	{
		for (Structure e : array)	{
			if (e instanceof Airport)	{
				writer.println(((Airport) e).getCode());
			}
		}
	}
	
	/**
	 * Displays the content of a text file to the screen
	 * @param reader The reader
	 * @throws IOException
	 */
	public static void displayAirportCodes(BufferedReader reader) throws IOException	{
		
		String content = "";
		int current;
		
		while ((current = reader.read()) != -1) {
			content += (char) current;
		}
		
		System.out.println("Reading the content of AirportCodes.txt: ");
		System.out.println(content);		
	}
	
	/**
	 * Generates a random number from a specific range
	 * @param min The minimum Number
	 * @param max The maximum number
	 * @return Returns a random number 
	 */
	public static double random(double min, double max)	{
		return Math.random() * (max - min) + min;
	}
	
	/**
	 * Generates a random number from a specific range
	 * @param min The minimum Number
	 * @param max The maximum number
	 * @return Returns a random number 
	 */	
	public static int random(int min, int max)	{
		return (int) (Math.random() * (max - min) + min);
	}	
}
