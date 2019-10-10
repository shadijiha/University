// ConsoleApplication1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <Math.h>
#include <string>
#include <stdio.h>

class Complex {

private:
	float a = 0;
	float b = 0;

public:

	Complex(float _a, float _b) {
		a = _a;
		b = _b;
	}

	Complex() {
		a = 0.0f;
		b = 0.0f;
	}

	void init(float _a, float _b) {
		a = _a;
		b = _b;
	}

	bool equals(Complex* numB) {
		if (a == numB->a && b == numB->b) {
			return true;
		}
		else
		{
			return false;
		}
	}

	void display() {
		printf("%1.2f + %1.2fi", a, b);
	}

	Complex* sum(Complex* numB) {
		return new Complex(a + numB->a, b + numB->b);
	}

	Complex* substract(Complex* numB) {
		return new Complex(a - numB->a, b - numB->b);
	}

	Complex* multiply(Complex* numB) {
		return new Complex(a * numB->a - b * numB->b, b * numB->a + a * numB->b);
	}

	Complex* divide(Complex* numB) {

		float firstTerm = (a * numB->a + b * numB->b) / (numB->a * numB->a + numB->b * numB->b);
		float secondTerm = (b * numB->a - a * numB->b) / (numB->a * numB->a + numB->b * numB->b);

		return new Complex(firstTerm, secondTerm);
	}

	int Re() {
		return a;
	}

	int Im() {
		return b;
	}

	Complex* conjugat() {
		return new Complex(a, -b);
	}

};

int main()
{

	Complex* z1 = new Complex(2.0, -3.0);
	Complex* z2 = new Complex(-3.0, 4.0);
	Complex* z3 = new Complex();

	Complex* result = z1->divide(z2);

	result->display();


	printf("\n\n\n\n");


	return 0;
}

