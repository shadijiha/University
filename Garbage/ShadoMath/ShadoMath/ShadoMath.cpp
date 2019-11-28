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
#include <functional>
#include "ShadoFile.h"

#define extends :
#define of :

int fibonacci(int n) {
	if (n == 0)
		return 0;
	if (n == 1)
		return 1;
	return fibonacci(n - 1) + fibonacci(n - 2);
}

int lucas(int n) {
	if (n == 0)
		return 2;
	if (n == 1)
		return 1;
	return lucas(n - 1) + lucas(n - 2);
}

int main()
{
	typedef std::string string;
	//===================================
	int n = 0;
	int action = 0;
	int choice = 1;

	while (action == 0) {
		std::cout << "Do you want fibonacci or Lucas (1 or 0): ";
		std::cin >> choice;

		if (choice == 1) {
			std::cout << "Enter the nth fibonacci number you want: ";
			std::cin >> n;
			std::cout << "\n You want the " << n << "th number of fibonacci series which is " << fibonacci(n);
			std::cout << "\n f_" << n << " = " << fibonacci(n) << std::endl;
			std::cout << "\n\n Do you want to exit (1 or 0)? ";
			std::cin >> action;
			std::cout << "\n\n";
		}
		else
		{
			std::cout << "Enter the nth Lucas number you want: ";
			std::cin >> n;
			std::cout << "\n You want the " << n << "th number of Lucas series which is " << lucas(n);
			std::cout << "\n l_" << n << " = " << lucas(n) << std::endl;
			std::cout << "\n\n Do you want to exit (1 or 0)? ";
			std::cin >> action;
			std::cout << "\n\n";
		}
	}

	//===================================

	std::cout << "\n\n\n" << std::endl;
	std::cin.get();

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
