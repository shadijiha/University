/**
 *
 */
package com;

import java.time.LocalDate;
import java.time.Period;

public class ProgrammingQuestion {

	public static final String[] names = {"Linda", "Sam", "Roger", "Alfred", "Roberto", "Melissa", "Brian", "Thomas", "Leslie", "Maria"};
	public static final String[] pDOB = {"1-1-2003", "24-2-1940", "11-12-1995", "31-3-1980", "29-6-1950", "25-7-1945", "15-7-2002", "20-7-2004", "27-4-1990", "9-5-1941"};


	public static void main(String[] args) {
		System.out.println("=========== Testing rearrangeParticipants(args...) ===========");

		System.out.println("******** Before ********");
		for (int i = 0; i < names.length; i++)
			System.out.printf("%s, %d\n", names[i], age(pDOB[i]));

		int numberOfSeniors = rearrangeParticipants(names, pDOB, names.length);

		System.out.println("******** After ********");
		for (int i = 0; i < names.length; i++)
			System.out.printf("%s, %d\n", names[i], age(pDOB[i]));

		System.out.println("There are " + numberOfSeniors + " seniors\n");

		System.out.println("=========== Testing displaySeniorsIncreasingOrder(args...) ===========");
		displaySeniorsIncreasingOrder(names, pDOB, numberOfSeniors);

	}

	public static int rearrangeParticipants(String[] names, String[] pDOB, int n) {
		return rearrangeParticipants(names, pDOB, n, 0, 0);
	}

	public static int rearrangeParticipants(String[] names, String[] pDOB, int n, int currentIndex, int numberOfSeniors) {

		if (currentIndex == n)
			return numberOfSeniors;

		if (age(pDOB[currentIndex]) >= 65) {
			swap(names, currentIndex, numberOfSeniors);
			swap(pDOB, currentIndex, numberOfSeniors);
			numberOfSeniors++;
		}

		currentIndex++;

		return rearrangeParticipants(names, pDOB, n, currentIndex, numberOfSeniors);
	}

	public static void displaySeniorsIncreasingOrder(String[] pName, String[] pDOB, int nSenior) {
		displaySeniorsIncreasingOrder(pName, pDOB, nSenior, 0, 0);
	}

	public static void displaySeniorsIncreasingOrder(String[] pName, String[] pDOB, int nSenior, int toDisplay, int currentIndex) {
		for (int i = 0; i < 4; i++) {
			if (currentIndex == nSenior || toDisplay == nSenior) {
				System.out.printf("%s, %s using R\n", pName[toDisplay], pDOB[toDisplay]);
				return;
			} else {
				if (age(pDOB[currentIndex]) < age(pDOB[toDisplay]))
					toDisplay = currentIndex;

				currentIndex++;
				displaySeniorsIncreasingOrder(pName, pDOB, nSenior, i, currentIndex);
			}
		}


	}

	public static void displayNonSeniorsInreasingOrder(String[] pName, String[] pDOB, int nNoneSenior, int total) {

	}

	public static void displayIncreasingOrder(String[] pName, String[] pDOB, int senior, int total) {

	}

	public static void swap(Object[] array, int index, int dist) {
		Object temp = array[index];
		array[index] = array[dist];
		array[dist] = temp;
	}

	public static int age(String dateOfBirth) {
		String tokens[] = dateOfBirth.split("-");
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		try {
			LocalDate dob = LocalDate.of(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[0]));
			return Period.between(dob, LocalDate.now()).getYears();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
}
