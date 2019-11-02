#include <string>
#include <stdio.h>
#include <vector>
#include <iostream>
#include "Shado.h"
#include "Complex.h"
#include "Fraction.h"

namespace ShadoMath {

	// FUNCTIONS
	float absoluteValue(float number) {
		if (number < 0.0f) {
			return -1.0f * number;
		}
		else {
			return number;
		}
	}

	int absoluteValue(int number) {
		return (int)absoluteValue((float)number);
	}

	float power(float base, int exposant) {

		// if exposant is negative return the inverse of base^abs(exposant)
		if (exposant < 0) {
			return 1 / power(base, -1 * exposant);
		}

		// if the exposant is a positive integer
		float sum = 1.0f;

		for (int i = 0; i < exposant; i++) {
			sum *= base;
		}

		return sum;
	}

	void power(float base, float exposant) {

		Fraction exp = Fraction(exposant);

		exp.print();

	}

	float root(float number, int exponant) {
		if (number < 0) {
			return -1;
		}
		else
		{
			return pow(number, 1 / exponant);
		}
	}

	Complex rootNegative(float number, int exponant) {

		Complex temp = Complex(0, pow(absoluteValue(number), 1 / exponant));
		return temp;

	}

	unsigned long factorial(int num) {

		unsigned long sum = 1;

		for (int i = 1; i <= num; i++) {
			sum *= i;
		}

		return sum;
	}

	double add(double a, double b) {
		return a + b;
	}

	bool devides(int number, int divider) {
		return number % divider == 0;
	}

	bool isEven(int num) {
		return devides(num, 2);
	}

	bool isNotEven(int num) {
		return !isEven(num);
	}

	bool isPrime(int num) {

		for (int i = 2; i < num; i++) {
			if (devides(num, i)) {
				return false;
			}
		}

		return true;

	}

	bool coprime(int a, int b) {

		unsigned const int PRECISION = 1000;

		for (int i = 2; i < PRECISION; i++) {
			if (a % i == 0 && b % i == 0) {
				return false;
			}
		}

		return true;
	}

	void printZeros(float a, float b, float c) {

		float delta = (b * b) - (4 * a * c);
		float x1 = 0.0f;
		float x2 = 0.0f;

		if (delta < 0) {
			x1 = (-b + sqrt(absoluteValue(delta))) / (2 * a);
			x2 = (-b - sqrt(absoluteValue(delta))) / (2 * a);

			printf("x1 = (%f + %f i) / %f ,", -b, absoluteValue(delta), 2.0 * a);
			printf("x2 = (%f - %f i) / %f ", -b, absoluteValue(delta), 2.0 * a);
		}
		else
		{
			x1 = (-b + sqrt(delta)) / (2 * a);
			x2 = (-b - sqrt(delta)) / (2 * a);

			printf("x1 = %f, ", x1);
			printf("x2 = %f", x2);

		}

	}

	void print(std::string str) {
		std::cout << str << std::endl;
	}


}

