// ShadoMath.cpp : This file contains the 'main' function. Program execution begins and ends there.
//
#ifdef _MSC_VER
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <iostream>
#include "Fraction.h"
#include "Complex.h"
#include "ShadoVector.h"
#include "Matrix.h"
#include "Shado.h"
#include "Math.h"
#include "Date.h"

uint64_t infiniteSum(int x, unsigned int ITERATION = 10) {

	uint64_t sum = 0;
	for (int n = 0; n < ITERATION; n++) {
		sum += pow(x, n);
	}

	return sum;
}

int main()
{
	// TODO ---> Fraction operators > and < not working properly
	std::cout << infiniteSum(2, 20) << std::endl;

	//string x = test[0].toString();
	//test[0].printPolar();
	//std::cout << x << std::endl;
	
	Complex xd = Complex(2, 0);
	xd.printPolar();

	return 0;
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
