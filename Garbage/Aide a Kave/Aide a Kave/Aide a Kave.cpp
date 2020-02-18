// Aide a Kave.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#include <string>
#include <functional>
#include <memory>
#include "ArrayList.h"

class Form {
private:
	int x, y;
public:
	Form(int x, int y) {
		this->x = x, this->y = y;
	}

	void print() {
		std::cout << "(x: " << x << ", y: " << y << ")\n";
	}

	void setX(int x) {
		this->x = x;
	}

	void setY(int y) {
		this->y = y;
	}
};

namespace Shado {

	template<typename T>
	class Vector {
	private:
		T** data;
		int length;
		int max_capacity;
		const int CAPACITY_DELTA = 10;
	public:
		Vector(int capacity) {
			data = new T * [capacity];
			length = 0;
			max_capacity = capacity;
		}

		Vector() :Vector(CAPACITY_DELTA) {}

		Vector(const Vector& other) {
			data = other.data;
			length = other.length;
			max_capacity = other.max_capacity;
		}

		~Vector() {
			delete[] data;
			length = 0;
			max_capacity = CAPACITY_DELTA;
		}

		void incrementCapacity() {
			// Create new Array that holdes the data
			Form** newArray = new Form * [max_capacity + CAPACITY_DELTA];

			// Transfer all data
			for (int i = 0; i < max_capacity; i++) {
				newArray[i] = data[i];
			}

			delete[] data;
			data = newArray;
			max_capacity = max_capacity + CAPACITY_DELTA;
		}

		void addAt(int index, T* form) {
			// Cannot have more forms vector is full.
			if (length - 1 >= max_capacity) {
				// Add capacity
				incrementCapacity();

				// Recall this function
				this->addAt(index, form);
			} else
			{
				// Increment length
				length++;

				// Shift all data by +1
				for (int i = size() - 1; i >= index; i--) {
					data[i] = data[i - 1];
				}

				// Add the data
				data[index] = form;
			}
		}

		void add(T* form) {
			// Cannot have more forms vector is full.
			if (length >= max_capacity) {
				// Add capacity
				incrementCapacity();

				// Add the data
				data[length] = form;
				length++;
			} else
			{
				data[length] = form;
				length++;
			}
		}

		T* remove(int index) {
			try
			{
				// Delete the data @ the index
				T* temp = data[index];
				data[index] = nullptr;

				// Update length
				length--;

				// Shift everything back 1 step
				for (int i = index; i < length; i++) {
					data[i] = data[i + 1];
				}

				return temp;

			} catch (const std::exception & e)
			{
				std::cout << e.what() << std::endl;
			}

		}

		T* pop() {
			return remove(size() - 1);
		}

		// Consumers
		void forEach(std::function<void(T*)> func) {
			for (int i = 0; i < this->size(); i++) {
				func(data[i]);
			}
		}

		// Getters
		T* get(int index) const {
			try {
				return data[index];
			} catch (const std::exception & e) {
				std::cout << "Error: Vector index out of bound" << std::endl;
			}
		}

		int size() const {
			return length;
		}

		int maxCapacity() const {
			return max_capacity;
		}

		// For iterators
		T** begin() const {
			return data;
		}

		T** end() const {
			return data + length;
		}

		/*Stream<T**> stream() {
			return Stream<T**>(data);
		}*/

		// Operators overloading
		T* operator[] (int index) const {
			return this->get(index);
		}
	};
}

int main()
{

	std::wstring test = L"ههههه ألو";

	std::wcout << test << std::endl;

	return 0;
}
