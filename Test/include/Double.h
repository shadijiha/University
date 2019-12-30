//
// Created by shadi on 2019-12-27.
//

#ifndef UNTITLED_DOUBLE_H
#define UNTITLED_DOUBLE_H

#include "Integer.h"

class Double : public Object {
private:
	double value = 0;
	bool isnull = false;
	const double EPSILON = 0.0001;
public:
	Double(double initialValue);

	Double() : Double(0) {}

	int intValue() const;

	double doubleValue() const;

	long longValue() const;

	double compare(double other) const;

	double compare(const Double& other) const;

	std::string toString() const override;

	bool equals(const Double& other) const;

	double valueOf() const { return this->value; }

	// Static members
	static double decode(const char* str);

	static Double toDouble(double val);

	static double parseDouble(const char* str);

	static double max(Double& first, Double& second);

	static double min(Double& first, Double& second);

	static double max(double first, double second);

	static double min(double first, double second);

	// Casting operators
	explicit operator double() {
		return this->value;
	}

	explicit operator Integer() {
		return Integer(this->intValue());
	}

	Double operator+(Double& other) const {
		return this->value + other.value;
	}

	Double operator-(Double& other) const {
		return this->value - other.value;
	}

	Double operator*(Double& other) const {
		return this->value * other.value;
	}

	Double operator/(Double& other) const {
		return this->value / other.value;
	}

	bool operator==(Double& other) const {
		return this->equals(other);
	}

	bool operator!=(Double& other) const {
		return !(this->equals(other));
	}

	bool operator>(Double& other) const {
		return this->compare(other) == 1;
	}

	bool operator<(Double& other) const {
		return this->compare(other) == -1;
	}

	bool operator>=(Double& other) const {
		return *this > other || *this == other;
	}

	bool operator<=(Double& other) const {
		return *this < other || *this == other;
	}

	friend std::ostream& operator<<(std::ostream& os, const Double& d);
};


#endif //UNTITLED_DOUBLE_H
