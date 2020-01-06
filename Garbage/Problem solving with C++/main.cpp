#include <iostream>
#include "ArrayList.h"
#include <array>

#define boolean bool
#define var auto
#define final const
#define extends :
#define implements :
#define foreach for
#define in :
#define of :

int main() {

	std::vector<double> vec{1, 2, 3, 4, 5};

	/*foreach (var element in vec) {
		std::cout << element << " ";
	}*/

	ArrayList<double>(vec).stream()
			.map([](auto e) { return e * e; })
			.map([](auto e) { return e * e; })
			.filter([](auto e) { return e > 50; })
			.forEach([](auto e) { std::cout << e << " "; });

	std::array<double, 5> array = {1, 5, 8, 9, 7};
	//std::vector<double> test();


	return 0;
}
