#ifndef SHADOVECTOR_H
#define SHADOVECTOR_H

class Vector {

private:
	float x, y, z;

public:

	Vector(float _x, float _y);

	Vector(float _x, float _y, float _z);

	Vector();

	void random2D();

	void random3D();

	void set(float _x, float _y, float _z);

	void set(float _x, float _y);

	void print();

	float getX();

	float getY();

	float getZ();

	float mag();

	void add(Vector& b);

	void inverse();

	void scale(float n);

	float dotProduct(Vector& b) const;

	Vector crossProduct(Vector& b);

};

#endif