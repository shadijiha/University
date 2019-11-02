#include <stdio.h>
#include <Math.h>
#include <stdlib.h>
#include "ShadoVector.h"

/*************************
**************************
*** Shado Vector   Lib.***
**************************
************************/

Vector::Vector(float _x, float _y) {
	x = _x;
	y = _y;
	z = 0;
}

Vector::Vector(float _x, float _y, float _z) {
	x = _x;
	y = _y;
	z = _z;
}

Vector::Vector() {
	x = 0.0f;
	y = 0.0f;
	z = 0.0f;
}

void Vector::random2D() {
	x = (float)(rand() % 10);
	y = (float)(rand() % 10);
}

void Vector::random3D() {
	random2D();
	z = (float)(rand() % 10);
}

void Vector::set(float _x, float _y, float _z) {
	x = _x;
	y = _y;
	z = _z;
}

void Vector::set(float _x, float _y) {
	x = _x;
	y = _y;
}

void Vector::print() {
	printf("%f i + %f j + %f z", x, y, z);
}

float Vector::getX() {
	return x;
}

float Vector::getY() {
	return y;
}

float Vector::getZ() {
	return z;
}

float Vector::mag() {
	return sqrt(x * x + y * y + z * z);
}

void Vector::add(Vector& b) {
	x = x + b.x;
	y = y + b.y;
	z = z + b.z;
}

void Vector::inverse() {
	x = x * -1.0f;
	y = y * -1.0f;
	z = z * -1.0f;
}

void Vector::scale(float n) {
	x = x * n;
	y = y * n;
	z = z * n;
}

float Vector::dotProduct(Vector& b) const {
	return x * b.x + y * b.y + z * b.z;
}

/*Vector Vector::crossProduct(Vector& b) {

		Matrix<float, 2, 2> i;
		i.setData(0, 0, y);
		i.setData(0, 1, z);
		i.setData(1, 0, b.y);
		i.setData(1, 1, b.z);

		Matrix<float, 2, 2> j;
		j.setData(0, 0, x);
		j.setData(0, 1, z);
		j.setData(1, 0, b.x);
		j.setData(1, 1, b.z);

		Matrix<float, 2, 2> k;
		k.setData(0, 0, x);
		k.setData(0, 1, y);
		k.setData(1, 0, b.x);
		k.setData(1, 1, b.y);

		return Vector(i.det(), -1.0f * j.det(), j.det());

	}*/

