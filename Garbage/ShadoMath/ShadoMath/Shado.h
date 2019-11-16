#ifndef SHADO_H
#define SHADO_H

#include <string>
#include <vector>
#include "Complex.h"

const double PI = 3.14159265359;
const double E = 2.71828182846;

namespace ShadoMath {
	
	float absoluteValue(float number);

	int absoluteValue(int number);

	float power(float base, int exposant);

	double power(double base, double exposant);

	double root(int number, int exposant);

	double root(double number, int exposant);

	std::vector<Complex> root(float number, int exponant);

	unsigned long factorial(int num);

	bool devides(int number, int divider);

	bool isEven(int num);

	bool isNotEven(int num);

	bool isPrime(int num);

	bool coprime(int a, int b);

	void printZeros(float a, float b, float c);

	void print(std::string str);

	bool compareFloat(float a, float b, float epsilon = 0.005f);

	int random(int min, int max);
}

#endif
