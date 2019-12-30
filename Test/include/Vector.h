//
// Created by shadi on 2019-12-27.
//

#ifndef UNTITLED_VECTOR_H
#define UNTITLED_VECTOR_H

#include "Matrix.h"
#include "Object.h"

class Vector : public Object {
public:
	double x, y, z;

	// Constructors
	Vector(double _x, double _y, double _z);

	Vector(double _x, double _y);

	explicit Vector(int scale);

	Vector(const Vector& vectorToCopy);

	Vector();

	void random2D(int scale);

	void random2D() { this->random2D(10); }

	void random3D(int scale);

	void random3D() { this->random3D(10); }

	// Math operations
	void inverse();

	void scale(double scale);

	double mag() const;

	void normalize();

	Vector add(const Vector& other) const;

	Vector subtract(const Vector& other) const;

	double dotProduct(const Vector& b) const;

	Vector crossProduct(const Vector& b) const;

	Matrix multiply(Matrix& matrix) const;

	Vector multiply(double scale) const;

	Vector project(const Vector& other) const;

	// Printers
	std::string toString() const;

	void print() const;

	// Convert and cast operators
	Matrix toMatrix() const;

	explicit operator Matrix() const {
		return this->toMatrix();
	}

};


#endif //UNTITLED_VECTOR_H
