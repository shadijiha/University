#pragma once

#include <string>
#include <stdio.h>
#include <vector>
#include <iostream>

namespace ShadoMath {

	namespace Classes {

		class Fraction {
		private:
			int numerator = 1;
			int denumerator = 1;

		public:
			Fraction(int a, int b) {
				numerator = a;
				denumerator = b;
			}

			Fraction(float decimal) {

				int tries = 0;
				float num = decimal;

				while (num - (int)num != 0) {
					num *= 10;
					tries++;
				}

				numerator = (int)num;

				for (int i = 0; i < tries; i++) {
					denumerator *= 10;
				}
			}

			int getNumerator() {
				return numerator;
			}

			int getDumerator() {
				return denumerator;
			}

			Fraction inverse() {
				return Fraction(denumerator, numerator);
			}

			void print() {
				printf("%d / %d", numerator, denumerator);
			}
		};

		class Complex {

		private:
			float a = 0.0f;
			float b = 0.0f;
			float phi = 0.0f;
			float r = 0.0f;
			const double PI = 3.1416;

		public:

			Complex(float _a, float _b) {
				a = _a;
				b = _b;
				phi = atan(b / a);
				r = sqrt(a * a + b * b);
			}

			Complex() {
				a = 0.0f;
				b = 0.0f;
			}

			~Complex() {
				/*delete &a;
				delete &b;
				delete &phi;
				delete &r;*/
			}

			void init(float _a, float _b) {
				a = _a;
				b = _b;
			}

			void initPolar(float _r, float _phi) {
				r = _r;
				phi = _phi;
				a = r * cos(phi);
				b = r * sin(phi);
			}

			bool equals(Complex numB) {
				if (a == numB.a && b == numB.b) {
					return true;
				}
				else
				{
					return false;
				}
			}

			void print() {
				printf("%1.2f + %1.2fi", a, b);
			}

			void printPolar() {
				printf("%1.2f * ( cos(%1.2f) + i sin(%1.2f) )", r, phi, phi);
			}

			Complex sum(Complex& numB) const {
				return Complex(a + numB.a, b + numB.b);
			}

			Complex substract(Complex& numB) const {
				return Complex(a - numB.a, b - numB.b);
			}

			Complex multiply(Complex& numB) const {
				return Complex(a * numB.a - b * numB.b, b * numB.a + a * numB.b);
			}

			Complex divide(Complex numB) const {

				float firstTerm = (a * numB.a + b * numB.b) / (numB.a * numB.a + numB.b * numB.b);
				float secondTerm = (b * numB.a - a * numB.b) / (numB.a * numB.a + numB.b * numB.b);

				return Complex(firstTerm, secondTerm);
			}

			float Re() {
				return a;
			}

			float Im() {
				return b;
			}

			Complex conjugat() const {
				return Complex(a, -1.0f * b);
			}

			float abs() {
				return sqrt(a * a + b * b);
			}

			Complex power(int exposant) const {

				Complex* temp = new Complex();
				temp->initPolar(pow(r, exposant), exposant * phi);

				return *temp;
			}

			std::vector<Complex> root(int exposant) {

				/*std::vector<Complex> result(exposant);
				auto it = result.begin();


				float tempR = pow(r, 1 / exposant);

				for (int i = 0; i < exposant; i++) {
					float tempPhi = (phi + 2 * PI * i) / exposant;
					Complex temp();
					temp.initPolar(tempR, tempPhi);

					result.insert(it, temp);
				}

				return result;*/
			}

			void printSqrt(int exposant) {

				float tempR = pow(r, 1.0 / exposant);

				for (int i = 0; i < exposant; i++) {

					float tempPhi = (phi + 2 * PI * i) / exposant;

					Complex temp(0, 0);
					temp.initPolar(tempR, tempPhi);
					temp.print();
					printf("\n");
				}
			}

		};

		template <typename T, int R, int C>
		class Matrix {

		private:
			T data[R][C];
			int rows = R;
			int cols = C;

		public:

			Matrix(T dataArray[R][C]) {

				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						data[i][j] = dataArray[i][j];
					}
				}

			}

			Matrix() {
				randomize();
			}

			~Matrix() {
				//delete[] data;
			}

			T getData(int _r, int _c) {
				return data[_r][_c];
			}

			void setData(int _r, int _c, T temp) {
				data[_r][_c] = temp;
			}

			void randomize() {

				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						data[i][j] = (T)(rand() % 10);
					}
				}

			}

			void generateIdentity() {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						if (i == j) {
							data[i][j] = 1;
						}
						else
						{
							data[i][j] = 0;
						}
					}
				}
			}

			void inverse() {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						data[i][j] = data[i][j] * -1;
					}
				}
			}

			void print() {

				printf("\n");
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {

						if (j == 0)
							printf("|");

						std::cout << data[i][j];

						if (j == cols - 1)
							printf("|");
						else
							std::cout << ", ";

					}
					printf("\n");
				}
			}

			Matrix add(Matrix& b) {

				if (rows != b.rows || cols != b.cols) {
					return;
				}
				else
				{
					Matrix<T, rows, cols> result;

					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < cols; j++) {
							result.data[i][j] = data[i][j] + b.data[i][j];
						}
					}

					return result;
				}
			}

			Matrix operator+(Matrix& b) {
				add(b);
			}

			void scale(float n) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						data[i][j] = data[i][j] * n;
					}
				}
			}

			void operator*(float n) {
				scale(n);
			}

			Matrix multiply(Matrix& n) {

				// Detect error
				if (this.cols != n.rows) {
					printf("Error! Connot proform Matrix multiplication operation on matrices because Matrix A colums is not equal to Matrix B rows.");
					return;
				}

				Matrix<T, rows, n.cols> temp;

				for (int i = 0; i < temp.rows; i++) {
					for (int j = 0; j < temp.cols; j++) {

						T sum = 0;

						for (int k = 0; k < cols; k++) {
							sum += data[i][k] * n.data[k][j];
						}

						temp.data[i][j] = sum;
					}
				}

				return temp;
			}

			Matrix operator*(Matrix& n) {
				return	multiply(n);
			}

			void transpose() {

				Matrix<T, rows, cols> temp;

				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						temp.data[i][j] = data[j][i];
					}
				}

				data = temp.data;
			}

			bool equals(Matrix& b) {
				if (det() == b.det())
					return true;
				else
					return false;
			}

			bool operator==(Matrix& b) {
				return equals(b);
			}

			bool operator!=(Matrix& b) {
				return !equals(b);
			}

			Matrix LUdecomposition() {

				Matrix<T, rows, cols> U;
				Matrix<T, rows, cols> L;

				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {

						int sum = 0;

						for (int k = 0; k <= i - 1; k++) {
							sum = sum + L.data[j][k] * U.data[k][i];
						}

						if (i == j) {
							U.data[i][j] = 1;
						}
						if (i <= j) {
							L.data[j][i] = data[j][i] - sum;
						}
						else {
							U.data[j][i] = (1 / L.data[j][j]) * (data[j][i] - sum);
						}

					}
				}

				Matrix result[2] = { U, L };

				return result;
			}

			T determinant() {

				// Detect Error
				if (rows != cols) {
					printf("Error! This matrix has diffrent row and colums count. Thus, the determinant cannot be calculated.");
					return -1;
				}

				Matrix decomposition[2] = LUdecomposition();
				Matrix temp = decomposition[0];
				T mult = 1;

				for (int i = 0; i < temp.rows; i++) {
					for (int j = 0; j < temp.cols; j++) {
						if (i == j) {
							mult *= temp.data[i][j];
						}
					}
				}

				return mult;
			}

			float det() {

				if (rows == 2 && cols == 2) {
					return data[0][0] * data[1][1] - data[1][0] * data[0][1];
				}
				else
				{
					return -1;
				}

			}
		};

		class Vector {

		private:
			float x, y, z = 0.0f;

		public:

			Vector(float _x, float _y) {
				x = _x;
				y = _y;
				z = 0;
			}

			Vector(float _x, float _y, float _z) {
				x = _x;
				y = _y;
				z = _z;
			}

			Vector() {
				x = 0.0f;
				y = 0.0f;
				z = 0.0f;
			}

			void set(float _x, float _y, float _z) {
				x = _x;
				y = _y;
				z = _z;
			}

			void set(float _x, float _y) {
				x = _x;
				y = _y;
			}

			void print() {
				printf("%f i + %f j + %f z", x, y, z);
			}

			float getX() {
				return x;
			}

			float getY() {
				return y;
			}

			float getZ() {
				return z;
			}

			float mag() {
				return sqrt(x * x + y * y + z * z);
			}

			void add(Vector& b) {
				x = x + b.x;
				y = y + b.y;
				z = z + b.z;
			}

			void inverse() {
				x = x * -1.0f;
				y = y * -1.0f;
				z = z * -1.0f;
			}

			void scale(float n) {
				x = x * n;
				y = y * n;
				z = z * n;
			}

			float dotProduct(Vector& b) const {
				return x * b.x + y * b.y + z * b.z;
			}

			Vector crossProduct(Vector& b) {

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

			}

		};

	}

	namespace Functions {

		float abs(float number) {
			if (number < 0.0f) {
				return -1.0f * number;
			}
			else {
				return number;
			}
		}

		int abs(int number) {
			return (int)abs((float)number);
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

			Classes::Fraction exp = Classes::Fraction(exposant);

			exp.print();

		}

		unsigned long factorial(unsigned int num) {

			unsigned long sum = 1;

			for (int i = 1; i <= num; i++) {
				sum *= i;
			}

			return sum;
		}

		double sum(double a, double b) {
			return a + b;
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

		bool isPrime(unsigned int num) {

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

	}
}