//
// Created by shadi on 2019-12-27.
//

#ifndef UNTITLED_INTEGER_H
#define UNTITLED_INTEGER_H

#include <string>
#include <iostream>
#include "Object.h"

class Integer : public Object {
private:
	int value = 0;
	bool isnull = false;
public:
	Integer(int initialValue);

	Integer() : Integer(0) {}

	int intValue() const;

	double doubleValue() const;

	long longValue() const;

	char charValue() const;

	int compare(int other) const;

	int compare(Integer other) const;

	std::string toString() const override;

	bool equals(Integer other) const;

	int valueOf() const { return this->value; }

	// Static members
	static int decode(const char* str);

	static Integer toInteger(int val);

	static int parseInt(const char* str);

	static int max(Integer& first, Integer& second);

	static int min(Integer& first, Integer& second);

	static int max(int first, int second);

	static int min(int first, int second);

	// Casting operators
	explicit operator int() {
		return this->value;
	}

	Integer operator+(Integer& other) const {
		return this->value + other.value;
	}

	Integer operator-(Integer& other) const {
		return this->value - other.value;
	}

	Integer operator*(Integer& other) const {
		return this->value * other.value;
	}

	Integer operator/(Integer& other) const {
		return this->value / other.value;
	}

	bool operator==(Integer& other) const {
		return this->equals(other);
	}

	bool operator!=(Integer& other) const {
		return !(this->equals(other));
	}

	bool operator>(Integer& other) const {
		return this->compare(other) == 1;
	}

	bool operator<(Integer& other) const {
		return this->compare(other) == -1;
	}

	bool operator>=(Integer& other) const {
		return *this > other || *this == other;
	}

	bool operator<=(Integer& other) const {
		return *this < other || *this == other;
	}

	friend std::ostream& operator<<(std::ostream& os, const Integer& i);
};


#endif //UNTITLED_INTEGER_H
