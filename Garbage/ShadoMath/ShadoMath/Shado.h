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

	void power(float base, float exposant);

	float root(float number, int exponant);

	Complex rootNegative(float number, int exponant);

	unsigned long factorial(int num);

	double add(double a, double b);

	bool devides(int number, int divider);

	bool isEven(int num);

	bool isNotEven(int num);

	bool isPrime(int num);

	bool coprime(int a, int b);

	void printZeros(float a, float b, float c);

	void print(std::string str);

}

#endif
