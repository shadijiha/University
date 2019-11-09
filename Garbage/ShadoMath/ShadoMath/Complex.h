#ifndef COMPLEX_H
#define COMPLEX_H

#include <string>
#include <vector>
#include <iostream>

class Complex {

private:
	float a;
	float b;
	float phi;
	float r;
	const double PI = 3.1416;

public:

	Complex(float _a, float _b);

	Complex();

	~Complex();

	void init(float _a, float _b);

	void initPolar(float _r, float _phi);

	bool equals(Complex& other);

	std::string toString();

	void print();

	void printPolar();

	Complex add(Complex& numB) const;

	Complex substract(Complex& numB) const;

	Complex multiply(Complex& numB) const;

	Complex divide(Complex& numB) const;

	float realPart();

	float imaginaryPart();

	float module();

	float angle();

	Complex conjugat() const;

	float mag();

	Complex power(int exposant) const;

	std::vector<Complex> root(int exposant);

	void printSqrt(int exposant);

	Complex operator+(Complex numB);

	Complex operator-(Complex numB);

	Complex operator*(Complex numB);

	Complex operator/(Complex numB);

	void operator=(Complex numB);

	bool operator==(Complex other);

	bool operator!=(Complex other);
};

std::ostream& operator<<(std::ostream& os, Complex num);

#endif