#pragma once

#include <string>

namespace ShadoMath {

	namespace Classes {

		class Fraction {
		private:
			int numerator;
			int denumerator;

		public:
			Fraction(int a, int b);

			Fraction(float decimal);

			int getNumerator();

			int getDumerator();

			Fraction inverse();

			void print();
		};

		class Complex {

		private:
			float a;
			float b;
			float phi;
			float r;
			const double PI = 3.1416;

		public:

			Complex(float _a, float _b);

			Complex();

			~Complex();

			void init(float _a, float _b);

			void initPolar(float _r, float _phi);

			bool equals(Complex numB);

			void print();

			void printPolar();

			Complex sum(Complex& numB) const;

			Complex substract(Complex& numB) const;

			Complex multiply(Complex& numB) const;

			Complex divide(Complex numB) const;

			float Re();

			float Im();

			Complex conjugat() const;

			float abs();

			Complex power(int exposant) const;

			std::vector<Complex> root(int exposant);

			void printSqrt(int exposant);

		};

		template <typename T, int R, int C>
		class Matrix {

		private:
			T data[R][C];
			int rows;
			int cols;

		public:

			Matrix(T dataArray[R][C]);

			Matrix();

			~Matrix();

			T getData(int _r, int _c);

			void setData(int _r, int _c, T temp);

			void randomize();

			void generateIdentity();

			void inverse();

			void print();

			Matrix add(Matrix& b);

			Matrix operator+(Matrix& b);

			void scale(float n);

			void operator*(float n);

			Matrix multiply(Matrix& n);

			Matrix operator*(Matrix& n);

			void transpose();

			bool equals(Matrix& b);

			bool operator==(Matrix& b);

			bool operator!=(Matrix& b);

			Matrix LUdecomposition();

			T determinant();

			float det();
		};

		class Vector {

		private:
			float x, y, z;

		public:

			Vector(float _x, float _y);

			Vector(float _x, float _y, float _z);

			Vector();

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


	}

	namespace Functions {

		float abs(float number);

		int abs(int number);

		float power(float base, int exposant);

		void power(float base, float exposant);

		unsigned long factorial(unsigned int num);

		double sum(double a, double b);

		bool devides(int number, int divider);

		bool isEven(int num);

		bool isNotEven(int num);

		bool isPrime(unsigned int num);

		bool coprime(int a, int b);

	}
}