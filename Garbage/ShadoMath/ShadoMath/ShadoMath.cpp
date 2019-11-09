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
#include <windows.h>

class Logger {

public:
	static const int logLevelError = 0;
	static const int logLevelWarnning = 1;
	static const int logLevelInfo = 2;

private:
	int logLevel = logLevelInfo;
	bool DEBUG_MODE = true;
	bool ALLOW_COLOR = false;
	HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

public:
	Logger() {}
	Logger(int level) {
		logLevel = level;
	}

	void log(std::string message)
	{
		if (DEBUG_MODE && logLevel <= logLevelInfo) {
			SetConsoleTextAttribute(hConsole, 10);
			std::cout << "[INFO]	";
			SetConsoleTextAttribute(hConsole, 15);
			std::cout << message << std::endl;
		}
	}

	void warn(std::string message)
	{
		if (DEBUG_MODE && logLevel <= logLevelWarnning) {
			SetConsoleTextAttribute(hConsole, 14);
			std::cout << "[WARNNING]	";
			SetConsoleTextAttribute(hConsole, 15);
			std::cout << message << std::endl;
		}
	}

	void error(std::string message)
	{
		if (DEBUG_MODE && logLevel <= logLevelError) {
			SetConsoleTextAttribute(hConsole, 12);
			std::cout << "[ERROR]	";
			SetConsoleTextAttribute(hConsole, 15);
			std::cout << message << std::endl;
		}
	}

	void toggleDebugMode()
	{
		DEBUG_MODE = !DEBUG_MODE;
	}
};

int main()
{
	// TODO ---> Fraction operators > and < not working properly

	Logger* DEBUGGER = new Logger();

	DEBUGGER->error("hehexd");

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
