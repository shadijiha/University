// -----------------------------------------------------
// Part: I & II
// Written by: Shadi Jiha
// -----------------------------------------------------

package driver;

import agricultural.AgriculturalDrone;
import agricultural.MAV;
import airplane.Airplane;
import airplane.Flyable;
import helicopters.Helicopter;
import helicopters.Quadcopter;
import multirotors.Multirotor;
import uavs.UAV;

public abstract class Main {

	/**
	 * Copies an array of Airplane Objects
	 *
	 * @param objects The array to copy
	 * @return Returns a copy of the passed array
	 */
	public static Flyable[] copyFlyingObjects(Flyable[] objects) {
		Flyable[] result = new Flyable[objects.length];

		for (int i = 0; i < objects.length; i++) {
			result[i] = objects[i].copy();
		}

		return result;
	}

	/**
	 * @param args CMD arguments
	 */
	public static void main(String[] args) {

		Flyable[] array = new Flyable[20];

		for (int i = 0; i < array.length; i++) {
			int t = i % 7;
			switch (t) {
				case 0:
					array[i] = new Airplane("Boeing", i * 10000, i * 10);
					break;
				case 1:
					array[i] = new Helicopter("Airbus", i * 1000, i * 5, i, 2010 + i, i + 5);
					break;
				case 2:
					array[i] = new Quadcopter("Airbus", i * 1000, i * 5, i, 2000 + i, i + 5, i * 100);
					break;
				case 3:
					array[i] = new Multirotor("Airbus", i * 1000, i * 5, i, 2000 + i, i + 5, i + 3);
					break;
				case 4:
					array[i] = new UAV(i, i * 1000);
					break;
				case 5:
					array[i] = new AgriculturalDrone(i * 1000, i * 10000, "Bombardier", i * 100);
					break;
				case 6:
					array[i] = new MAV(i, i * 100, "AE10342328", i * 15);
					break;
			}
		}

		// Copy the array
		Object[] copy = copyFlyingObjects(array);

		// Display Original array
		System.out.println("Original Array:");
		System.out.println("-----------------");
		for (Object o : array) {
			System.out.println(o);
		}

		System.out.println("\n==========================\n");

		// Display copied array
		System.out.println("Copied Array:");
		System.out.println("-----------------");
		for (Object o : copy) {
			System.out.println(o);
		}

		// Display whenever they are equal or not
		System.out.println("\n\nEquality test: \n");
		for (int i = 0; i < array.length; i++) {
			System.out.printf("Element #%d of Original is%s equal to element #%d of copy.\n", i, array[i].equals(copy[i]) ? "" : " not", i);
		}

		System.out.print("\n==========================\n");
		System.out.print("All elements are equal because I override the Flyable.copy() function in each class." +
				"\nSo the returned Copy invokes the copy constructor which returns an exact copy of the calling object.\n\n");

		ShadoList<Integer> test = new ShadoList<Integer>();
		test.add(5);
		test.add(3);
		test.add(15);
		test.add(3153);
	}
}