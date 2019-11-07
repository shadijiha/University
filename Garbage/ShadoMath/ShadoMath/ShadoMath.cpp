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
#include <chrono>
#include <ctime>  

class Date {
	
public:

	Date() {}
	
	long long getTime() {
		auto getChrono = std::chrono::system_clock::now();
		std::time_t getTimeT = std::chrono::system_clock::to_time_t(getChrono);

		long long timeNow = getTimeT;

		return timeNow;
	}

	std::string formateTime(long long value) {
		std::time_t display = value;
		return std::ctime(&display);
	}

};


int main()
{
	// TODO ---> Fraction operators > and < not working properly

	Complex* x = new Complex(2, 10);
	Complex* y = new Complex(1, 5);

	Complex result = *x + *y;

	std::cout << new Date->formateTime(new Date->getTime()) << std::endl;

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
