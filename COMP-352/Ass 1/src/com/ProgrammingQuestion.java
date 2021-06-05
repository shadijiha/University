/**
 *
 */
package com;

import java.time.*;

public class ProgrammingQuestion {

	public static final String[] names = {"Linda", "Sam", "Roger", "Alfred", "Roberto", "Melissa", "Brian", "Thomas", "Leslie", "Maria"};
	public static final String[] pDOB = {"1-1-2003", "24-2-1940", "11-12-1995", "31-3-1980", "29-6-1950", "25-7-1945", "15-7-2002", "20-7-2004", "27-4-1990", "9-5-1941"};


	public static void main(String[] args) {

		Factory factory = new Factory();

		//for (int n = 10; n < 10_000; n *= 2) {

		factory.generateToFile(100, false);

		String[] names = factory.getLastGeneratedNames();
		String[] pDOB = factory.getLastGeneratedDates();
		int totalNumberOfMembers = names.length;

		System.out.println("=========== Testing rearrangeParticipants(args...) ===========");

		System.out.println("******** Before ********");
		for (int i = 0; i < names.length; i++)
			System.out.printf("%s, %d\n", names[i], age(pDOB[i]));

		long rearrangeParticipantsStart = System.currentTimeMillis();

		int numberOfSeniors = rearrangeParticipants(names, pDOB, names.length);            // <------- Calling rearrangeParticipants

		long rearrangeParticipantsEnd = System.currentTimeMillis();
		StatsCollector.record("rearrangeParticipants", totalNumberOfMembers, rearrangeParticipantsEnd - rearrangeParticipantsStart);

		System.out.println("******** After ********");
		for (int i = 0; i < names.length; i++)
			System.out.printf("%s, %d\n", names[i], age(pDOB[i]));

		System.out.println("There are " + numberOfSeniors + " seniors\n");

		System.out.println("=========== Testing displaySeniorsIncreasingOrder(args...) ===========");
		long displaySeniorsStart = System.currentTimeMillis();

		displaySeniorsIncreasingOrder(names, pDOB, numberOfSeniors);

		long displaySeniorsEnd = System.currentTimeMillis();
		StatsCollector.record("displaySeniorsIncreasingOrder", totalNumberOfMembers, displaySeniorsEnd - displaySeniorsStart);

		System.out.println("=========== Testing displayNonSeniorsInreasingOrder(args...) ===========");
		long displayNonSeniorsStart = System.currentTimeMillis();

		displayNonSeniorsInreasingOrder(names, pDOB, totalNumberOfMembers - numberOfSeniors, totalNumberOfMembers);

		long displayNonSeniorsEnd = System.currentTimeMillis();
		StatsCollector.record("displayNonSeniorsInreasingOrder", totalNumberOfMembers, displayNonSeniorsEnd - displayNonSeniorsStart);


		System.out.println("=========== Testing displayIncreasingOrder(args...) ===========");
		long displayStart = System.currentTimeMillis();

		displayIncreasingOrder(names, pDOB, numberOfSeniors, totalNumberOfMembers);

		long displayEnd = System.currentTimeMillis();
		StatsCollector.record("displayIncreasingOrder", totalNumberOfMembers, displayEnd - displayStart);
		//}

	}

	public static int rearrangeParticipants(String[] names, String[] pDOB, int n) {
		return rearrangeParticipants(names, pDOB, n, 0);
	}

	public static int rearrangeParticipants(String[] names, String[] pDOB, int n, int currentIndex) {

		if (currentIndex == n) {

			// Count number of seniors
			int numberOfSeniors = 0;
			for (int i = 0; i < n; i++) {
				if (age(pDOB[i]) >= 65) {
					numberOfSeniors++;
				}
			}

			// Then the arrays are ordered in decreasing order, in this case
			// The seniors are arranged properly but the non seniors needs to be rearranged
			for (int i = numberOfSeniors; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (age(pDOB[i]) > age(pDOB[j])) {
						swap(names, i, j);
						swap(pDOB, i, j);
					}
				}
			}

			return numberOfSeniors;
		}

		int age = age(pDOB[currentIndex]);

		int highestAge = age;
		int highestAgeIndex = currentIndex;

		for (int i = currentIndex; i < n; i++) {
			int temp = age(pDOB[i]);
			if (temp > highestAge) {
				highestAge = temp;
				highestAgeIndex = i;
			}
		}

		swap(names, highestAgeIndex, currentIndex);
		swap(pDOB, highestAgeIndex, currentIndex);

		currentIndex++;
		return rearrangeParticipants(names, pDOB, n, currentIndex);
	}

	public static void displaySeniorsIncreasingOrder(String[] pName, String[] pDOB, int nSenior) {
		displaySeniorsIncreasingOrder(pName, pDOB, nSenior, 0);
	}

	public static void displaySeniorsIncreasingOrder(String[] pName, String[] pDOB, int nSenior, int displayed) {
		if (displayed == nSenior) {
			return;
		} else {
			int index = nSenior - displayed - 1;
			System.out.printf("%s, %d using R\n", pName[index], age(pDOB[index]));
			displayed++;
			displaySeniorsIncreasingOrder(pName, pDOB, nSenior, displayed);
		}
	}

	public static void displayNonSeniorsInreasingOrder(String[] pName, String[] pDOB, int nNoneSenior, int total) {
		displayNonSeniorsInreasingOrder(pName, pDOB, nNoneSenior, total, 0);
	}

	public static void displayNonSeniorsInreasingOrder(String[] pName, String[] pDOB, int nNoneSenior, int total, int displayed) {
		if (displayed == nNoneSenior) {
			return;
		} else {
			int index = total - nNoneSenior + displayed;
			System.out.printf("%s, %d using R\n", pName[index], age(pDOB[index]));
			displayed++;
			displayNonSeniorsInreasingOrder(pName, pDOB, nNoneSenior, total, displayed);
		}
	}

	public static void displayIncreasingOrder(String[] pName, String[] pDOB, int senior, int total) {

		// Copy arrays
		String[] nameCopy = new String[pName.length];
		String[] pDOBCopy = new String[pDOB.length];

		// Copy both array
		for (int i = 0; i < pName.length; i++) {
			nameCopy[i] = pName[i];
			pDOBCopy[i] = pDOB[i];
		}

		// First sort the array
		for (int a = 0; a <= nameCopy.length - 1; a++) {
			for (int b = 0; b <= nameCopy.length - 2; b++) {
				if (age(pDOBCopy[b + 1]) < age(pDOBCopy[b])) {
					swap(nameCopy, b + 1, b);
					swap(pDOBCopy, b, b + 1);
				}
			}
		}

		for (int i = 0; i < nameCopy.length; i++) {
			System.out.printf("%s, %d\n", nameCopy[i], age(pDOBCopy[i]));
		}
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
