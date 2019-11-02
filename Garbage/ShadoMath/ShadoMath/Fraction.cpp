#include <string>
#include <stdio.h>
#include <vector>
#include <iostream>
#include "Fraction.h"

/*************************
**************************
*** Shado Fraction Lib.***
**************************
************************/

Fraction::Fraction(int a, int b) {
	numerator = a;
	denumerator = b;
}

Fraction::Fraction(float decimal) {

	int tries = 0;
	float num = decimal;

	while (num - (int)num != 0) {
		num *= 10;
		tries++;
	}

	numerator = (int)num;

	for (int i = 0; i < tries; i++) {
		denumerator *= 10;
	}
}

int Fraction::getNumerator() {
	return numerator;
}

int Fraction::getDumerator() {
	return denumerator;
}

Fraction Fraction::inverse() {
	return Fraction(denumerator, numerator);
}

void Fraction::simplify() {

	for (int i = 10; i > 0; i--) {
		if (numerator % i == 0 && denumerator % i == 0) {
			numerator = numerator / i;
			denumerator = denumerator / i;
		}
	}


}

std::string Fraction::toString() {
	return std::to_string(numerator) + " / " + std::to_string(denumerator);
}

void Fraction::print() {
	std::cout << this->toString();
}

Fraction Fraction::add(Fraction& fracB) {

	// Find common denumerator
	Fraction commonA = Fraction(numerator * fracB.denumerator, denumerator * fracB.denumerator);
	Fraction commonB = Fraction(fracB.numerator * denumerator, fracB.denumerator * denumerator);

	Fraction result = Fraction(commonA.numerator + commonB.numerator, commonA.denumerator);
	result.simplify();

	return result;
}

Fraction Fraction::substract(Fraction& fracB) {
	// Find common denumerator
	Fraction commonA = Fraction(numerator * fracB.denumerator, denumerator * fracB.denumerator);
	Fraction commonB = Fraction(fracB.numerator * denumerator, fracB.denumerator * denumerator);

	Fraction result = Fraction(commonA.numerator - commonB.numerator, commonA.denumerator);
	result.simplify();

	return result;
}

Fraction Fraction::multiply(float number) {
	return Fraction((numerator * number) / denumerator);
}

Fraction Fraction::multiply(Fraction& fracB) {
	return Fraction(numerator * fracB.numerator, denumerator * fracB.denumerator);
}

Fraction Fraction::devide(Fraction& fracB) {
	Fraction copy = fracB.inverse();

	return (*this).multiply(copy);
}

Fraction Fraction::devide(float number) {
	return Fraction((numerator / number) / denumerator);
}

bool Fraction::equals(Fraction& fracB) {
	return (numerator / denumerator) == (fracB.numerator / fracB.denumerator);
}

// Overload operators for Fraction
Fraction Fraction::operator+(Fraction fracB) {
	return (*this).add(fracB);
}

Fraction Fraction::operator-(Fraction fracB) {
	return (*this).substract(fracB);
}

Fraction Fraction::operator*(Fraction fracB) {
	return (*this).multiply(fracB);
}

Fraction Fraction::operator/(Fraction fracB) {
	return (*this).devide(fracB);
}


std::ostream& operator<<(std::ostream& os, Fraction frac)
{
	os << frac.toString();
	return os;
}
