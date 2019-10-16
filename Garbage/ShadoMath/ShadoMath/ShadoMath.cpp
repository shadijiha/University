// ShadoMath.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#include <fstream>
#include <string>
#include "Shado.h"
#include "Math.h"

int main()
{

	/*
		sqrt(n), n >= 0

		x^2 = n

		x^2 - n = 0

		1) Quadratic formula
		2) Bisection Method  <-----

		REPLACE 2 with any other root power	
	*/

	using namespace ShadoMath::Functions;
	using namespace ShadoMath::Classes;

	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			if (j * j * j * j + i * i * i * i <= 100) {
				printf("x = %d, y = %d -> x^4 + y^4 = %d \n", j, i, j * j * j * j + i * i * i * i);
			}			
		}
	}

	std::cout << power(2, 4);

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
