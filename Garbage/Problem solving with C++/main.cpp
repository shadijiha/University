#include <iostream>
#include "ArrayList.h"

#define boolean bool
#define var auto
#define final const

struct vec3 {
	float x = 0.0f, y = 0.0f, z = 0.0f;

	void randomize() {
		x = rand() % 100;
		y = rand() % 100;
		z = rand() % 100;
	}

	void print() {
		printf("{x: %f, y: %f, z: %f}\t", x, y, z);
	}
};

int main() {

	std::vector<double> vec{1, 2, 3, 4, 5};
	auto* array = new ArrayList<vec3>();

	for (int i = 0; i < 100; i++)
		array->add(vec3());

	array->stream()
			.modify([](vec3& e) { e.randomize(); })
			.forEach([](vec3 e) { e.print(); });

	return 0;
}
