// Assignment 1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>

#define N 10

class DoubleStackNode {
public:

	DoubleStackNode(int value, DoubleStackNode* next)
		: m_value(value), m_next(next)
	{}

	DoubleStackNode()
		: DoubleStackNode(NULL, nullptr)
	{}

	bool hasNext() const				{ return m_next != nullptr; }
	DoubleStackNode* const  next() const { return m_next; };

	int value() const { return m_value; }

	void setNext(DoubleStackNode* next) {
		m_next = next;
	}

	void setValue(int newValue) {
		m_value = newValue;
	}	
	
private:
	int m_value;
	DoubleStackNode* m_next;
};

class DoubleStack {

public:
	DoubleStack() {
		DoubleStack::s_array = new DoubleStackNode[N];
	}
	

private:
	static DoubleStackNode* s_array;
};

int main(int argc, const char** argv)
{

	
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
