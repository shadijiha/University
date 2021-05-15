/**
 *
 */
package com;

import java.text.*;
import java.util.*;

public class ProgrammingQuestion {

	public static final String[] names = {"Linda", "Sam", "Roger", "Alfred", "Roberto", "Melissa", "Brian", "Thomas", "Leslie", "Maria"};
	public static final String[] pDOB = {"1-1-2003", "24-2-1940", "11-12-1995", "31-3-1980", "29-6-1950", "25-7-1945", "15-7-2002", "20-7-2004", "27-4-1990", "9-5-1941"};


	public static void main(String[] args) {


	}

	public static int rearrangeParticipants(String[] names, String[] pDOB, int n) {
		return rearrangeParticipants(names, pDOB, n, 0);
	}

	public static int rearrangeParticipants(String[] names, String[] pDOB, int n, int currentIndex) {

		return 0;
	}

	public static void displaySeniorsIncreasingOrder(String[] pName, String[] pDOB, int nSenior) {
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
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
		try {
			Date dob = formatter.parse(dateOfBirth);

			//return new Period(dob.getTime(), new Date().getTime()).getYears();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}
}
