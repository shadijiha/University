#include <iostream>
#include "include/ShadoMath.h"
#include <functional>
#include <memory>

#define extends :
#define var auto

template<typename T>
class ArrayList;

template<typename T>
class Stream : public Object {
private:
	std::vector<T>* dataLocation;
public:
	explicit Stream(std::vector<T>* data) {
		dataLocation = data;
	}

	Stream map(std::function<T(T)> func) {
		for (int i = 0; i < dataLocation->size(); i++) {
			(*dataLocation)[i] = func((*dataLocation)[i]);
		}
		return Stream<T>(dataLocation);
	}

	Stream filter(std::function<bool(T)> func) {
		// Loop through the whole std::vector and erase elements that don't match the condition
		for (auto it = dataLocation->begin(); it != dataLocation->end();) {
			if (!func(*it)) {
				it = dataLocation->erase(it);
			} else {
				++it;
			}
		}
		return Stream<T>(dataLocation);
	}

	Stream limit(int number) {
		// Careful MEMORY LEAK
		// Create an new ArrayList and return its stream (To not modify the initial ArrayList)
		ArrayList<T>* temp = new ArrayList<T>();
		for (int i = 0; i < number; i++) {
			temp->add((*dataLocation)[i]);
		}
		return Stream<T>(temp->stream());
	}

	Stream sort(std::function<bool(T, T)> func) {
		for (int i = 0; i < dataLocation->size(); i++) {
			for (int j = 0; j < dataLocation->size(); j++) {
				// If condition is met swap location of i and j
				if (func((*dataLocation)[i], (*dataLocation)[j])) {
					auto temp = (*dataLocation)[i];
					(*dataLocation)[i] = (*dataLocation)[j];
					(*dataLocation)[j] = temp;
				}
			}
		}
		return Stream<T>(dataLocation);
	}

	// Terminal operations
	void forEach(std::function<void(T)> func) {
		for (auto element : *dataLocation) {
			func(element);
		}
	}

	bool anyMatch(std::function<bool(T)> func) {
		for (const auto element : *dataLocation) {
			if (func(element))
				return true;
		}
	}

	bool allMatch(std::function<bool(T)> func) {
		int matchCount = 0;
		for (const auto& element : *dataLocation) {
			if (func(element))
				matchCount++;
		}

		return matchCount == dataLocation->size();
	}

	int firstMatch(std::function<bool(T)> func) {
		for (int i = 0; i < dataLocation->size(); i++) {
			if (func((*dataLocation)[i]))
				return i;
		}
	}

	bool noneMatch(std::function<bool(T)> func) {
		int matchCount = 0;
		for (const auto& element : *dataLocation) {
			if (func(element))
				matchCount++;
		}

		return matchCount == 0;
	}

	T min() {
		T least = (*dataLocation)[0];
		for (const auto& element : *dataLocation) {
			if (element < least) {
				least = element;
			}
		}
		return least;
	}

	T max() {
		T least = (*dataLocation)[0];
		for (const auto& element : *dataLocation) {
			if (element > least) {
				least = element;
			}
		}
		return least;
	}

	std::vector<T> toArray() {
		return std::vector<T>(*dataLocation);
	}

	ArrayList<T>* collect() {
		return new ArrayList<T>(this->toArray());
	}
};

template<typename T>
class ArrayList {
private:
	std::vector<T> data;
	int length = 0;
public:
	ArrayList() = default;

	explicit ArrayList(std::vector<T> initData) {
		this->data = initData;
		this->length = initData.size();
	}

	explicit ArrayList(ArrayList<T> const& arrayList) {
		// Copy the std::vector with its copy constructor
		this->data(arrayList.data);
		this->length = arrayList.length;
	}

	void add(T value) {
		this->data.push_back(value);
		this->length++;
	}

	void remove(int index) {
		this->data.erase(this->data.begin() + index, this->data.begin() + index + 1);
		this->length--;
	}

	T get(int index) { return this->data[index]; }

	int size() {
		return this->data.size();
	}

	Stream<T> stream() {
		return Stream<T>(&this->data);
	}
};

int main() {

	// TODO: Stream.collect() method
	auto* test = new ArrayList<int>();
	for (int i = 0; i < 100; i++) {
		test->add(rand() % 100);
	}

	test->stream()
			.map([](auto e) { return e * 1; })
			.sort([](auto a, auto b) { return a < b; })
			.forEach([](auto e) { std::cout << e << " "; });

	std::cout << "\nSize: " << test->size() << std::endl;

	delete test;

	return 0;
}
