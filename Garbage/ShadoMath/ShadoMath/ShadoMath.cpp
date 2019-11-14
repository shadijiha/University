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
#include <thread>

#define extends :
#define of :

class Interval {
private:
	int threadId;
	unsigned int millis;
	std::function<void()> func;

public:
	Interval(std::function<void()> functionToCall, unsigned int intervalInMilliSeconds) {
		func = functionToCall;
		millis = intervalInMilliSeconds;

		// Start thread
		using namespace std::literals::chrono_literals;
		std::thread worker(func);
		std::this_thread::sleep_for(std::chrono::milliseconds(millis));
	}

};

void test() {
	std::cout << "hahaa" << std::endl;
}


int main()
{
	typedef std::string string;
	// TODO ---> Fraction operators > and < not working
	Interval myInterval = Interval(test, 1000);

	std::cout << "\n\n\n" << std::endl;

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
