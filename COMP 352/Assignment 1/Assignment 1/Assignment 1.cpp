// Assignment 1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <string>
#include <vector>

std::string question1(const std::string& str) {

	std::string result = "";
	std::string copy = str + "0";	// Add garbage char at the end
	
	const char* array = copy.c_str();

	char current = array[0];
	int count = 1;

	for (int i = 0; array[i] != '\0'; i++) {
		if (array[i] == current) {
			count++;
			continue;
		}
		
		std::string num = count > 1 ? std::to_string(count) : "";
		result += current + num;
		current = array[i];
		count = 1;
	}

	return result;
}

struct Question2_Return_Values {
	// Consecetive with the smallest value
	// Index
	int first_smallest_number;
	int second_smallest_number;

	// Consecetive with the largest value
	int first_largest_number;
	int second_largest_number;

	friend std::ostream& operator << (std::ostream& os, const Question2_Return_Values& t) {
		os << "The two conductive indices with smallest difference between their values are: index " << t.first_smallest_number << " and index " << t.second_smallest_number << std::endl;
		os << "The two conductive indices with largest difference between their values are: index " << t.first_largest_number << " and index " << t.second_largest_number << std::endl;
		return os;
	}
};

Question2_Return_Values question2(std::vector<int> array) {

	Question2_Return_Values result;
	
	unsigned int smallestDiff = INT_MAX;
	unsigned int largestDiff = 0;

	for (int i = 0; i < array.size() - 1; i++) {

		if (abs(array[i] - array[i + 1]) < smallestDiff) {
			smallestDiff = abs(array[i] - array[i + 1]);

			result.first_smallest_number = array[i];
			result.second_smallest_number = array[i + 1];
		}

		if (abs(array[i] - array[i + 1]) > largestDiff) {
			largestDiff = abs(array[i] - array[i + 1]);

			result.first_largest_number = array[i];
			result.second_largest_number = array[i + 1];
		}
		
	}

	return result;
}

int main()
{
	using namespace std;

	cout << "Question 1:" << endl;
    cout << "gggN@@@@@KKeeeejjdsmmu ---> " << question1("gggN@@@@@KKeeeejjdsmmu");
	cout << "\n============================\n" << endl;

	cout << "Question 2:" << endl;
	cout << question2({ 20, 52,400, 3, 30, 70, 72, 47, 28, 38, 41, 53, 20 }) << endl;
	
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
