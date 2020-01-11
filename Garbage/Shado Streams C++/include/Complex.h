//
// Created by shadi on 2019-12-27.
//

#ifndef UNTITLED_COMPLEX_H
#define UNTITLED_COMPLEX_H

#include <vector>
#include <string>
#include "Vector.h"
#include "Object.h"

class Complex : public Object {
private:
	double a, b, r, phi;

public:
	Complex(double _a, double _b);

	Complex(const Complex& complexToCopy);

	Complex();

	void fromPolar(double magnitude, double angle);

	// Math Operations
	double mag() const;

	Complex add(const Complex& other) const;

	Complex subtract(const Complex& other) const;

	Complex multiply(const Complex& other) const;

	Complex divide(const Complex& other) const;

	Complex power(int exponent) const;

	std::vector<Complex> root(int exponent) const;

	Complex conjugate() const;

	// Setters
	void setA(double _a);

	void setB(double _b);

	void setR(double _r);

	void setAngle(double angle);

	// Getters
	double getA() { return this->a; }

	double getB() { return this->b; }

	double getR() { return this->r; }

	double getPhi() { return this->phi; }

	double getAngle() { return this->phi; }

	std::string toString() const;

	std::string toStringPolar() const;

	// Convert and casting operators
	Vector toVector();

	explicit operator Vector() { return this->toVector(); }

	Complex operator+(const Complex& other) const { return this->add(other); }

	Complex operator-(const Complex& other) const { return this->subtract(other); }

	Complex operator*(const Complex& other) const { return this->multiply(other); }

	Complex operator/(const Complex& other) const { return this->divide(other); }

	bool operator==(const Complex& other) const { return this->a == other.a && this->b == other.b; }

	bool operator!=(const Complex& other) const { return !(*this == other); }

	bool operator>(const Complex& other) const { return this->mag() > other.mag(); }

	bool operator<(const Complex& other) const { return this->mag() < other.mag(); }

	bool operator>=(const Complex& other) const { return *this > other || *this == other; }

	bool operator<=(const Complex& other) const { return *this < other || *this == other; }
};


#endif //UNTITLED_COMPLEX_H
