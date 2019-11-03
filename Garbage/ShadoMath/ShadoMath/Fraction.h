#ifndef FRACTION_H
#define FRACTION_H

#include <string>

class Fraction {
private:
	int numerator = 1;
	int denumerator = 1;

public:
	Fraction(int a, int b);

	Fraction(float decimal);

	int getNumerator();

	int getDumerator();

	Fraction inverse();

	void simplify();

	std::string toString();

	void print();

	Fraction add(Fraction& fracB);

	Fraction substract(Fraction& fracB);

	Fraction multiply(float number);

	Fraction multiply(Fraction& farcB);

	Fraction devide(Fraction& fracB);

	Fraction devide(float number);

	bool equals(Fraction& fracB);

	bool greaterThan(Fraction& fracB);

	Fraction operator+(Fraction fracB);

	Fraction operator-(Fraction fracB);

	Fraction operator*(Fraction fracB);

	Fraction operator/(Fraction fracB);

	bool operator==(Fraction fracB);

	bool operator!=(Fraction fracB);

	bool operator>(Fraction fracB);

	bool operator<(Fraction fracB);

	bool operator>=(Fraction fracB);

	bool operator<=(Fraction fracB);
};

std::ostream& operator<<(std::ostream& os, Fraction frac);


#endif