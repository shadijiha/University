// ShadoMath.cpp : This file contains the 'main' function. Program execution begins and ends there.
//
#ifdef _MSC_VER
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <iostream>
#include "Fraction.h"
#include "Complex.h"
#include "ShadoVector.h"
#include "Matrix.h"
#include "Shado.h"
#include "Math.h"
#include "Date.h"
#include <functional>

class Rectangle {
protected:
	int w, h;

public:
	Rectangle() {
		this->w = 10;
		this->h = 20;
	}

	Rectangle(int width, int height) {
		this->w = width;
		this->h = height;
	}

	int getArea() { return this->w * this->h; }

	void printArea() {
		std::cout << this->w << " x " << this->h << " = " << this->getArea() << std::endl;
	}
};

class Square : Rectangle {
public:
	Square() {
		this->w = 15, this->h = 15;
	}

	Square(int dimension) {
		this->w = dimension, this->h = dimension;
	}

	void printArea() {
		std::cout << "Area of square: " << this->w << " x " << this->h << " = " << this->getArea() << std::endl;
	}
};

typedef std::string string;
int main()
{
	// TODO ---> Fraction operators > and < not working 
	Rectangle* rect = new Rectangle();
	Square* sqrt = new Square();
	string xd = "?XD";

	rect->printArea();
	sqrt->printArea();

	std::cout << "\n\n\n" << std::endl;

	return 0;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
