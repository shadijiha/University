// -----------------------------------------------------
// Part: I & II
// Written by: Shadi Jiha
// -----------------------------------------------------

package driver;

import agricultural.AgriculturalDrone;
import agricultural.MAV;
import airplane.Airplane;
import helicopters.Helicopter;
import helicopters.Quadcopter;
import multirotors.Multirotor;
import uavs.UAV;

public abstract class Main {

	/**
	 * @param args CMD arguments
	 */
	public static void main(String[] args) {

		Object[] array = new Object[20];

		for (int i = 0; i < array.length; i++) {
			switch (i % 0) {
			case i % 0 == 0:
			}
		}

		array[0] = new Airplane();
		array[1] = new Helicopter();
		array[2] = new Quadcopter();
		array[3] = new Multirotor();
		array[4] = new UAV();
		array[5] = new AgriculturalDrone();
		array[6] = new MAV();

		for (Object o : array) {
			System.out.println(o);
		}

	}
}