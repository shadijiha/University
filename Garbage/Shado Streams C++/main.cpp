#include <iostream>
#include <functional>
#include <thread>
#include <vector>

template<typename T>
class ArrayList;

template<typename T>
class Stream {
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

	Stream ignore(int number) {
		ArrayList<T>* temp = new ArrayList<T>();
		for (int i = number; i < dataLocation->size(); i++) {
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

	Stream modify(std::function<void(T&)> operation) {
		for (auto& element : *dataLocation) {
			operation(element);
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
class Iterator {
private:
	std::vector<T>* array;
	int iteration = 0;
public:
	explicit Iterator(std::vector<T>* vector, int index) {
		array = vector;
		iteration = index;
	}

	explicit Iterator(std::vector<T>* vector) : Iterator(vector, 0) {}

	bool hasNext() {
		// Checks if there are next elements in the iteration
		return (array->size() - 1) > iteration;
	}

	T next() {
		// Gives the next element of the iteration
		if (this->hasNext()) {
			iteration++;
			return (*array)[iteration];
		}
		return (*array)[iteration];
	}

	T remove() {
		// Removes the last element from the iteration and returns it
		int index = array->size() - 1;
		T val = (*array)[index];
		array.erase(array.begin() + index, array.begin() + index + 1);

		return val;
	}

	int forEachRemaining(std::function<void(T)> func) {
		//Performs the given action for each remaining element until all elements have been processed or the action throws an exception. Actions are performed in the order of iteration, if that order is specified. Exceptions thrown by the action are relayed to the caller.
		// Returns the number of elements on which the action was performed
		for (int i = iteration; i < array->size(); i++) {
			func(array->at(i));
		}
		return array->size() - iteration;
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

	Iterator<T>* iterate() {
		return new Iterator<T>(&data);
	}
};

/*bool isNumber(const char* num) {
	try {
		Integer::parseInt(num);
	} catch (std::exception& e) {
		return false;
	}
	return true;
}*/

int main() {

	// TODO: Stream.collect() method
	auto* test = new ArrayList<int>();
	for (int i = 0; i < 100; i++) {
		test->add(rand() % 100);
	}

	/*test->stream()
			.map([](auto e) { return e * 1; })
			.filter([](auto e) { return e > 5; })
			.sort([](auto a, auto b) { return a > b; })
			.forEach([](auto e) { std::cout << e << " "; });*/

	auto* it = test->iterate();
	while (it->hasNext()) {
		std::cout << it->next() << " ";
	}

	//std::cout << "\nIs number test: " << (true ? "Yes" : "no") << std::endl;

	delete test;

	return 0;
}
