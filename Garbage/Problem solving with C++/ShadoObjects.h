//
// Created by shadi on 2019-12-28.
//

#ifndef SHADOOBJECTS_H
#define SHADOOBJECTS_H

#include <string>
#include <iostream>

class Object {
private:
	bool isnull = false;
public:
	Object() = default;

	virtual const Object* hashCode() const {
		return this;
	}

	std::string getClass() const {
		return typeid(*this).name();
	}

	virtual std::string toString() const {
		return this->getClass() + "@" + std::to_string((int) this->hashCode());
	}

	bool equals(Object o) const {
		return this == &o;
	}

	virtual bool isNULL() const final { return this->isnull; }

	virtual void toNULL() final {
		this->isnull = true;
	}

	friend std::ostream& operator<<(std::ostream os, const Object& o);
};

template<typename T>
class Nullable : public Object {
private:
	T data = 0;
	bool corrupted = false;
public:

	explicit Nullable(T value) {
		this->data = value;
	}

	Nullable() {
		this->data = 0;
	}

	void markNULL() {
		this->corrupted = true;
	}

	bool isNULL() {
		return this->corrupted;
	}

	T value() {
		return this->data;
	}

	std::string toString() {
		if (this->isNULL())
			return "null";
		else
			return std::to_string(this->data);
	}
};

class Integer : public Object {
private:
	int value = 0;
	bool isnull = false;
public:
	Integer(int initialValue) {
		this->value = initialValue;
	}

	Integer() : Integer(0) {}

	int intValue() const {
		return this->value;
	}

	double doubleValue() const {
		return (double) this->value;
	}

	long longValue() const {
		return (long) this->value;
	}

	char charValue() const {
		return (char) this->value;
	}

	int compare(Integer other) const {
		return this->compare(other.value);
	}

	int compare(int other) const {
		if (this->value == other)
			return 0;
		else if (this->value < other)
			return -1;
		else // if (this->value > other)
			return 1;
	}

	std::string toString() const {
		return std::to_string(this->value);
	}

	bool equals(Integer other) const {
		return this->value == other.value;
	}

	int valueOf() const { return this->value; }

	// Static members
	static int decode(const char* str) {
		return std::stoi(str);
	}

	static Integer toInteger(int val) {
		return {val};
	}

	static int parseInt(const char* str) {
		return std::stoi(str);
	}

	static int max(Integer& first, Integer& second) {
		return first.value > second.value ? (int) first : (int) second;
	}

	static int min(Integer& first, Integer& second) {
		return first.value < second.value ? (int) first : (int) second;
	}

	static int max(int first, int second) {
		return first > second ? first : second;
	}

	static int min(int first, int second) {
		return first < second ? first : second;
	}

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

class Double : public Object {
private:
	double value = 0;
	bool isnull = false;
	const double EPSILON = 0.0001;
public:
	Double(double initialValue) {
		this->value = initialValue;
	}

	Double() : Double(0) {}

	int intValue() const {
		return (int) this->value;
	}

	double doubleValue() const {
		return this->value;
	}

	long longValue() const {
		return (long) this->value;
	}

	double compare(double other) const {
		if (this->value - other < EPSILON)
			return 0;
		else if (this->value < other)
			return -1;
		else // if (this->value > other)
			return 1;
	}

	double compare(const Double& other) const {
		return this->compare(other.value);
	}

	std::string toString() const {
		return std::to_string(this->value);
	}

	bool equals(const Double& other) const {
		return (this->value - other.value) < EPSILON;
	}

	// Static members
	static double decode(const char* str) {
		return std::stod(str);
	}

	static Double toDouble(double val) {
		return {val};
	}

	static double parseDouble(const char* str) {
		return std::stod(str);
	}

	static double max(Double& first, Double& second) {
		return first.value > second.value ? (double) first : (double) second;
	}

	static double min(Double& first, Double& second) {
		return first.value < second.value ? (double) first : (double) second;
	}

	static double max(double first, double second) {
		return first > second ? first : second;
	}

	static double min(double first, double second) {
		return first < second ? first : second;
	}

	double valueOf() const { return this->value; }

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

class Float : public Object {
private:
	float value = 0;
	bool isnull = false;
	const float EPSILON = 0.001f;
public:
	Float(float initialValue) {
		this->value = initialValue;
	}

	Float() : Float(0.0f) {}

	int intValue() const {
		return (int) this->value;
	}

	float floatValue() const {
		return this->value;
	}

	long longValue() const {
		return (long) this->value;
	}

	float compare(float other) const {
		if (this->value - other < EPSILON)
			return 0;
		else if (this->value < other)
			return -1;
		else // if (this->value > other)
			return 1;
	}

	float compare(Float other) const {
		return this->compare(other.value);
	}

	std::string toString() const override {
		return std::to_string(this->value);
	}

	bool equals(Float other) const {
		return (this->value - other.value) < EPSILON;
	}

	float valueOf() const { return this->value; }

	// Static members
	static int decode(const char* str) {
		return std::stof(str);
	}

	static Float toFloat(float val) {
		return {val};
	}

	static float parseFloat(const char* str) {
		return std::stof(str);
	}

	static float max(Float& first, Float& second) {
		return first.value > second.value ? (float) first : (float) second;
	}

	static float min(Float& first, Float& second) {
		return first.value < second.value ? (float) first : (float) second;
	}

	static float max(float first, float second) {
		return first > second ? first : second;
	}

	static float min(float first, float second) {
		return first < second ? first : second;
	}

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

std::ostream& operator<<(std::ostream os, const Object& o) {
	os << o.toString();
	return os;
}

std::ostream& operator<<(std::ostream& os, const Integer& i) {
	os << i.valueOf();
	return os;
}

std::ostream& operator<<(std::ostream& os, const Double& d) {
	os << d.valueOf();
	return os;
}

std::ostream& operator<<(std::ostream& os, const Float& f) {
	os << f.valueOf();
	return os;
}

#endif //SHADOOBJECTS_H
