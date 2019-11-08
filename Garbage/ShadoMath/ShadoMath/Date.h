#ifndef DATE_H
#define DATE_H

#ifdef _MSC_VER
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <chrono>
#include <ctime>
#include <string>

class Date {

private:
	int seconds, minutes, hours, day, month, year, weekday, dayOfYear, daylightSavings;
	uint64_t value;

public:

	Date(uint64_t val = NULL);

	uint64_t getTime();

	std::string formateTime(uint64_t value);

	void printDate();

	void refresh();

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

#endif // Date.h