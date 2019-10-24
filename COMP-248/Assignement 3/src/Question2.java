
// -------------------------------------------------------
// Assignment #3
// Written by: Shadi Jiha #40131284
// For COMP 248 Section (your section) – Fall 2019
//
// This program prints and stores CPU models and prices
// --------------------------------------------------------

import java.util.Scanner;

public class Question2 {

	public static void main(String[] args) {
		
		CPU cpu1 = new CPU();
		CPU cpu2 = new CPU(10, "i9", 449.00, 3.1, "Q2'19", true);
		
		print("Welcome to the simple class example!\n");
		
		print("CPU 1: " + cpu1.hehexd());
		print("CPU 2: " + cpu2.hehexd());
		
		print("CPU 1 Series:" + cpu1.getSeries());
		print("CPU 1 Suggested price: " + cpu1.getPrice() + " USD");
		print("CPU 1 Suggested price: (after mutator call): " + cpu1.priceNow("Q3'10") + " USD");
		print("Are CPU 1 and CPU 2 equal? " + (cpu1.equals(cpu2) ? "YES" : "NO"));
		print("Is CPU 1 of hight generation than CPU 2? " + (cpu1.isHigherGeneration(cpu2) ? "YES" : "NO"));
		print("CPU 1 Price at Q3'19: " + cpu1.priceNow("Q3'19"));
		print("CPU 2 Price at Q3'19: " + cpu2.priceNow("Q3'19"));
		
		print("\nThank you for testing the simple class example!");
		
	}
	
	public static void print(String x)	{
		System.out.println(x);
	}
	
	public static void print(double x)	{
		System.out.println(x);
	}


}
