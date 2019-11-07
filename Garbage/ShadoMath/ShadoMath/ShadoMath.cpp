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
#include <stdlib.h>     /* srand, rand */

class Date {

private:
	int seconds, minutes, hours, day, month, year, weekday, dayOfYear, daylightSavings;
	
public:

	Date() {}
	
	uint64_t getTime() {
		auto getChrono = std::chrono::system_clock::now();
		std::time_t getTimeT = std::chrono::system_clock::to_time_t(getChrono);

		uint64_t timeNow = getTimeT;

		return timeNow;
	}

	std::string formateTime(uint64_t value) {
		std::time_t display = value;
		return std::ctime(&display);
	}

	void printDate() {
		std::cout << this->formateTime(this->getTime()) << std::endl;
	}

	void refresh() {
		time_t t = time(NULL);
		tm* timePtr = localtime(&t);

		seconds = timePtr->tm_sec;
		minutes = timePtr->tm_min;
		hours = timePtr->tm_hour;
		day = timePtr->tm_mday;
		month = timePtr->tm_mon;
		year = timePtr->tm_year;
		weekday = timePtr->tm_wday;
		dayOfYear = timePtr->tm_yday;
		daylightSavings = timePtr->tm_isdst;
	}

	int getSeconds()			{ return this->seconds;			}
	int getMinutes()			{ return this->minutes;			}
	int getHours()				{ return this->hours;			}
	int getDay()				{ return this->day;				}
	int getMonth()				{ return this->month;			}
	int getYear()				{ return this->year;			}
	int getWeekday()			{ return this->weekday;			}
	int getDayOfYear()			{ return this->dayOfYear;		}
	int getDaylightSavings()	{ return this->daylightSavings; }
};

int random(int min, int max) {
	
	if (min >= max) {
		int temp = max;
		max = min;
		min = temp;	
	}
	return rand() % (max - min) + min;
}

int main()
{
	// TODO ---> Fraction operators > and < not working properly

	for (int i = 0; i < 20; i++) {
		std::cout << random(10, 20) << std::endl;
	}

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
