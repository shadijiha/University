//
// Created by shadi on 2019-12-27.
//

#ifndef UNTITLED_FLOAT_H
#define UNTITLED_FLOAT_H

#include "Integer.h"
#include "Double.h"

class Float : public Object {
private:
	float value = 0;
	bool isnull = false;
	const float EPSILON = 0.001f;
public:
	Float(float initialValue);

	Float() : Float(0.0f) {}

	int intValue() const;

	float floatValue() const;

	long longValue() const;

	float compare(float other) const;

	float compare(Float other) const;

	std::string toString() const override;

	bool equals(Float other) const;

	float valueOf() const { return this->value; }

	// Static members
	static int decode(const char* str);

	static Float toFloat(float val);

	static float parseFloat(const char* str);

	static float max(Float& first, Float& second);

	static float min(Float& first, Float& second);

	static float max(float first, float second);

	static float min(float first, float second);

	// Casting operators
	explicit operator float() {
		return this->value;
	}

	explicit operator Double() {
		return Double((double) this->value);
	}

	Float operator+(Float& other) const {
		return this->value + other.value;
	}

	Float operator-(Float& other) const {
		return this->value - other.value;
	}

	Float operator*(Float& other) const {
		return this->value * other.value;
	}

	Float operator/(Float& other) const {
		return this->value / other.value;
	}

	bool operator==(Float& other) const {
		return this->equals(other);
	}

	bool operator!=(Float& other) const {
		return !(this->equals(other));
	}

	bool operator>(Float& other) const {
		return this->compare(other) == 1;
	}

	bool operator<(Float& other) const {
		return this->compare(other) == -1;
	}

	bool operator>=(Float& other) const {
		return *this > other || *this == other;
	}

	bool operator<=(Float& other) const {
		return *this < other || *this == other;
	}

	friend std::ostream& operator<<(std::ostream& os, const Float& f);
};


#endif //UNTITLED_FLOAT_H
