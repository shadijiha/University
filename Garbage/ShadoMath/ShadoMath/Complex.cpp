#include "Complex.h"
#include <string>
#include <stdio.h>
#include <Math.h>
#include "Shado.h"


/*************************
**************************
*** Shado Complex  Lib.***
**************************
************************/

Complex::Complex(float _a, float _b) {
	a = _a;
	b = _b;
	phi = atan(b / a);
	r = sqrt(a * a + b * b);
}

Complex::Complex() {
	a = 0.0f;
	b = 0.0f;
	r = 0.0f;
	phi = 0.0f;
}

Complex::~Complex() {
}

void Complex::init(float _a, float _b) {
	a = _a;
	b = _b;
}

void Complex::initPolar(float _r, float _phi) {
	r = _r;
	phi = _phi;
	a = r * cos(phi);
	b = r * sin(phi);
}

bool Complex::equals(Complex& other) {
	if (a == other.a && b == other.b) {
		return true;
	}
	else
	{
		return false;
	}
}

std::string Complex::toString() {
	return std::to_string(this->a) + " + " + std::to_string(this->b) + " i";
}

void Complex::print() {
	std::cout << this->toString() << std::endl;
}

void Complex::printPolar() {
	printf("%1.5f * ( cos(%1.5f) + i sin(%1.5f) )", r, phi, phi);
}

Complex Complex::add(Complex& numB) const {
	return Complex(a + numB.a, b + numB.b);
}

Complex Complex::substract(Complex& numB) const {
	return Complex(a - numB.a, b - numB.b);
}

Complex Complex::multiply(Complex& numB) const {
	return Complex(a * numB.a - b * numB.b, b * numB.a + a * numB.b);
}

Complex Complex::divide(Complex& numB) const {

	float firstTerm = (a * numB.a + b * numB.b) / (numB.a * numB.a + numB.b * numB.b);
	float secondTerm = (b * numB.a - a * numB.b) / (numB.a * numB.a + numB.b * numB.b);

	return Complex(firstTerm, secondTerm);
}

float Complex::realPart() {
	return a;
}

float Complex::imaginaryPart() {
	return b;
}

float Complex::module() {
	return sqrt(a * a + b * b);
}

float Complex::angle() {
	return phi;
}

Complex Complex::conjugat() const {
	return Complex(a, -1.0f * b);
}

float Complex::mag() {
	return sqrt(a * a + b * b);
}

Complex Complex::power(int exposant) const {

	Complex* temp = new Complex();
	temp->initPolar(pow(r, exposant), exposant * phi);

	return *temp;
}

std::vector<Complex> Complex::root(int exposant) {

		std::vector<Complex> result (exposant);

		float tempR = pow(this->r, 1 / exposant);

		for (int i = 0; i < result.size(); i++) {
			float tempPhi = (phi + 2 * PI * i) / exposant;
			Complex temp = Complex();
			temp.initPolar(tempR, tempPhi);

			result[i] = temp;
		}

		return result;
}

void Complex::printSqrt(int exposant) {

	float tempR = pow(r, 1.0 / exposant);

	for (int i = 0; i < exposant; i++) {

		float tempPhi = (phi + 2 * PI * i) / exposant;

		Complex temp(0, 0);
		temp.initPolar(tempR, tempPhi);
		temp.print();
		printf("\n");
	}
}

Complex Complex::operator+(Complex other) {
	return this->add(other);
}

Complex Complex::operator-(Complex other) {
	return this->substract(other);
}

Complex Complex::operator*(Complex other) {
	return this->multiply(other);
}

Complex Complex::operator/(Complex other) {
	return this->divide(other);
}

void Complex::operator=(Complex other) {
	this->a = other.a;
	this->b = other.b;
}

bool Complex::operator==(Complex other) {
	return this->equals(other);
}

bool Complex::operator!=(Complex other) {
	return !(this->equals(other));
}

std::ostream& operator<<(std::ostream& os, Complex num)
{
	os << num.toString();
	return os;
}


