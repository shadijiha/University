#include <string>
#include <stdio.h>
#include <vector>
#include <iostream>
#include "Shado.h"
#include "Complex.h"
#include "Fraction.h"
#include <stdlib.h>     /* srand, rand */

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

		using namespace std;

		float a;   					// Base
		float n;     				// Exponent
		int num;	 				// Numerator of the exponent
		int den = 1; 				// Denominator of the exponent
	
		cout << "Base: ";
		cin >> a;
		cout << "Exponent: ";
		cin >> n;
	
		//----------------------------------------------------------------
	
		while (n != (int) n) {		// Convert n from decimal to fraction:
			n = n*10;				// Multiplying numerator and denominator x10 
			den = den*10;			// until both become whole integers
		}
		num = n;					// Now: a^n --> a^(num/den)

		//----------------------------------------------------------------

		int t_x = num;
		int t_y = den;
		int temp;
	
		while (t_y != 0){
			temp = t_x%t_y;
			t_x = t_y;
			t_y = temp;
		}
	
		num = num/t_x;
		den = den/t_x;				// Simplifying the (num/den) expression to lowest terms

		//----------------------------------------------------------------

								//  Solve x = a^(num/den)
								//  Rising both sides to the (den) power: x^(den) = a^(num)
								//  Passing all the terms to one side of the equation:
								//  x^(den) - a^(num) = 0
								//  Finding the root with Newton's method
	
		float x;				// Next x - Newton's method
		float x0 = 1;			// Current x - Newton's method, initial value set to 1
		float tol = 1;			// Tolerance - Newton's method
		float atonum = a;		// Variable for computing a^(num)
		float xtoden;			// Variable for computing x0^(den)
	
		for (int i = 1; i < num; i++) {
			atonum = atonum * a;
		}

		cout << endl <<"  Solving:  x^" << den << " - " << atonum << " = 0" << endl << endl;
	
		while (tol > 0.001){										//
			xtoden = x0;											//
			for (int i=1; i<den; i++) xtoden = xtoden*x0;			//
			x = x0 - (xtoden-atonum) / ((den)*(xtoden/x0));			//
			tol = ((x-x0)/x0)*100;									//  Newton's Method Iterations
			if (tol < 0) tol = tol*(-1);							//
			cout << "x0 = " << x0 << endl;							//
			cout << "x = " << x << endl;							//
			cout << "tol = " << tol << endl << endl;				//
			x0 = x;													//
		}
	
		cout << "Result = " << x;   				//  Displaying the result

	}

	std::vector<Complex> root(float number, int exponant) {
		if (number < 0) {
			Complex temp = Complex(number, 0);
			std::vector<Complex> result = temp.root(exponant);

			return result;
		}
		else
		{
			std::vector<Complex> result(1);
			result[0] = Complex(pow(number, 1 / exponant), 0);

			return result;
		}
	}

	unsigned long factorial(int num) {

		unsigned long sum = 1;

		for (int i = 1; i <= num; i++) {
			sum *= i;
		}

		return sum;
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

	bool compareFloat(float a, float b, float epsilon) {
		return fabs(a - b) <= epsilon;
	}

	int random(int min, int max) {

		if (min >= max) {
			int temp = max;
			max = min;
			min = temp;
		}
		return rand() % (max - min) + min;
	}
}

