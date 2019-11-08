/**
* C++ file for Shado Date class
*
*/

#include "Date.h"
#include <iostream>

Date::Date(uint64_t val) {
	this->value = val;
}

uint64_t Date::getTime() {
	if (this->value == NULL) {
		auto getChrono = std::chrono::system_clock::now();
		std::time_t getTimeT = std::chrono::system_clock::to_time_t(getChrono);

		uint64_t timeNow = getTimeT;

		this->refresh();

		return timeNow;
	}
	else
	{
		return this->value;
	}
}

std::string Date::formateTime(uint64_t value) {
	std::time_t display = value;
	return std::ctime(&display);
}

void Date::printDate() {
	std::cout << this->formateTime(this->getTime()) << std::endl;
}

void Date::refresh() {
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