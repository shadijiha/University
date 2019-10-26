// ShadoMath.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#include <fstream>
#include <string>
#include "Shado.h"
#include "Math.h"

int sum(int a, int b) {
	return a + b;
}

float sum(float a, float b) {
	return a + b;
}

int main()
{
	using namespace ShadoMath::Classes;
	
	int x = 10;

	Vector test(10, 5);
	Vector test2(15, 2);

	test.setX(12);

	test.add(test2);

	sum(1, 1);

}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
