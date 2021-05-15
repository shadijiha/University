package com;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {


		//PrintWriter writer = new PrintWriter(new FileOutputStream("out.csv"));

		for (int toGenerate = 10; toGenerate <= 100; toGenerate += 10) {

			List<Integer> generated = new ArrayList<>();
			for (int i = 0; i < toGenerate; i++) {
				generated.add(i);
			}

			long start = System.nanoTime();
			long result = arraySpecialSum(generated.toArray(new Integer[0]), generated.size());
			long end = System.nanoTime();

			long result2 = betterAlgorithm(generated.toArray(new Integer[0]), generated.size());

			System.out.printf("Original: %d, improved: %d, Equal? %s\n", result, result2, result == result2 ? "True" : "False");

			//writer.printf("%d,%d\n", toGenerate, end - start);
			//writer.flush();

		}

		//writer.close();
	}

	public static long arraySpecialSum(Integer[] A, int n) {

		int currentMax = A[0];
		// O(n)
		for (int i = 1; i < n; i++) {
			if (A[i] > currentMax)
				currentMax = A[i];
		}

		// O(n)
		int CurrentMaxOccurence = 0;
		for (int i = 0; i < n; i++) {
			if (A[i] == currentMax)
				CurrentMaxOccurence++;
		}

		long specialSum = 0;

		// O(n^2)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= CurrentMaxOccurence; j++) {
				specialSum = specialSum + A[i];
			}
		}

		return specialSum;
	}

	public static long betterAlgorithm(Integer[] A, int n) {

		int currentMax = A[0];

		// O(n)
		for (int i = 1; i < n; i++) {
			if (A[i] > currentMax)
				currentMax = A[i];
		}

		// O(n)
		int CurrentMaxOccurence = 0;
		long specialSum = 0;

		for (int i = 0; i < n; i++) {
			if (A[i] == currentMax) {
				specialSum = n * (specialSum + A[i]);
				CurrentMaxOccurence++;
			}
		}

		return specialSum;
	}
}
