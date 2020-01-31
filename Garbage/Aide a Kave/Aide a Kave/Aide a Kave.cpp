// Aide a Kave.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#include <string>
#include <functional>

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
};

template<typename T>
class ShadoVector {
private:
	T** data;
	int length;
	int max_capacity;
	const int CAPACITY_DELTA = 10;
public:
	ShadoVector(int capacity) {
		data = new T * [capacity];
		length = 0;
		max_capacity = capacity;
	}

	ShadoVector() :ShadoVector(CAPACITY_DELTA) {}

	ShadoVector(const ShadoVector& other) {
		data = other.data;
		length = other.length;
		max_capacity = other.max_capacity;
	}

	~ShadoVector() {
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
			std::cout << e.what() << std::endl;
		}
	}

	int size() const {
		return length;
	}

	int maxCapacity() const {
		return max_capacity;
	}

	// Operators overloading
	T* operator[] (int index) const {
		return this->get(index);
	}
};

int main()
{
	// Testing
	ShadoVector<Form> vec;
	ShadoVector<int> vec2;

	for (int i = 0; i < 5; i++) {
		vec.add(new Form(i, i));
	}

	vec.add(new Form(200, 300));

	for (int i = 0; i < vec.size(); i++) {
		std::cout << "i: " << i << " --> ";
		vec[i]->print();
	}

	return 0;
}
