/***
 * Driver class
 */

package com.main;

import java.util.Iterator;

public abstract class Main {
	public static void main(String... args)	{

	}

	public static float equallyLikelyEventsProb(float n_A, float N)	{
		return n_A / N;
	}

	public static int countingRulePermutations(int n, int r)	{
		return factorial(n) / factorial(n - r);
	}

	public static int countingRuleCombinations(int n, int r)	{
		return factorial(n) / (factorial(r) * factorial(n - r));
	}

	// Utility
	public static <T> void print(T... args)	{
		for(var temp : args)	{
			System.out.print(temp);
		}
		System.out.println();
	}
	public static int factorial(int num)	{
		if (num <= 1)
			return 1;
		return num * factorial(num - 1);
	}

}
