#include <vector>
#include <string>
#include <stdio.h>
#include "Matrix.h"

/*************************
**************************
*** Shado Matrix   Lib.***
**************************
************************/
/*template <typename T, int R, int C>
Matrix::Matrix(T dataArray[R][C]) {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = dataArray[i][j];
			}
		}

	}

Matrix::Matrix() {
		randomize();
	}

Matrix::~Matrix() {
		//delete[] data;
	}

T Matrix::getData(int _r, int _c) {
		return data[_r][_c];
	}

void Matrix::setData(int _r, int _c, T temp) {
		data[_r][_c] = temp;
	}

void Matrix::randomize() {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = (T)(rand() % 10);
			}
		}

	}

void Matrix::generateIdentity() {
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

void Matrix::inverse() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = data[i][j] * -1;
			}
		}
	}

void Matrix::print() {

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

Matrix Matrix::add(Matrix& b) {

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

Matrix Matrix::operator+(Matrix& b) {
		add(b);
	}

void Matrix::scale(float n) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = data[i][j] * n;
			}
		}
	}

void Matrix::operator*(float n) {
		scale(n);
	}

Matrix Matrix::multiply(Matrix& n) {

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

Matrix Matrix::operator*(Matrix& n) {
		return	multiply(n);
	}

void Matrix::transpose() {

		Matrix<T, rows, cols> temp;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				temp.data[i][j] = data[j][i];
			}
		}

		data = temp.data;
	}

bool Matrix::equals(Matrix& b) {
		if (det() == b.det())
			return true;
		else
			return false;
	}

bool Matrix::operator==(Matrix& b) {
		return equals(b);
	}

bool Matrix::operator!=(Matrix& b) {
		return !equals(b);
	}

Matrix Matrix::LUdecomposition() {

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

T Matrix::determinant() {

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

float Matrix::det() {

		if (rows == 2 && cols == 2) {
			return data[0][0] * data[1][1] - data[1][0] * data[0][1];
		}
		else
		{
			return -1;
		}

	}

*/