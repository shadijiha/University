//
// Created by shadi on 2019-12-26.
//

#ifndef UNTITLED_MATRIX_H
#define UNTITLED_MATRIX_H

#include <vector>
#include <string>
#include <iostream>
#include <cstdlib>  // For rand()
#include "Object.h"

class Matrix : public Object {

private:
	std::vector<std::vector<double>> data;
	int rows;
	int cols;

	int getBiggestCol(std::vector<std::vector<double>> array);

	void compress();

	auto removeAllNulls(std::vector<std::vector<double>> array);

	void resize();

	int factorial(int number);

public:
	Matrix(int rowCount, int colCount);

	explicit Matrix(const std::vector<std::vector<double>>& initialData);

	Matrix(const Matrix& matrixToCopy);

	Matrix();

	void randomize(int scale);

	void randomize();

	void generateIdentity();

	// Math Operations
	Matrix add(Matrix& other);

	Matrix multiply(double number);

	Matrix multiply(Matrix& other);

	Matrix power(int exponent);

	Matrix transpose();

	Matrix subMatrix(int rowToIgnore, int colToIgnore);

	double determinant2D();

	double determinant();

	// Setters
	void setData(int row, int col, double newData);

	// Getters
	double getData(int row, int col);

	std::vector<std::vector<double>> toArray();

	int getRows();

	int getCols();

	bool is2D();

	bool isSquare();

	void print();

};


#endif //UNTITLED_MATRIX_H
