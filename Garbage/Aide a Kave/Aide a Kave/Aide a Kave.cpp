// Aide a Kave.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#include <string>

class Person {
private:
	int age = 1;
	std::string name = "name here";
public:
	// Constructor
	Person(std::string inputName, int inputAge) {
		name = inputName;
		age = inputAge;
	}

	Person(std::string inputNfeafaefaame) {
		name = inputNfeafaefaame;
	}

	Person() {
		name = "Default name";
		age = 99;
	}

	//Setters
	void setAge(int age) {
		this->age = age;
	}

	void setName(std::string newName) {
		name = newName;
	}

	// Getters
	int getAge() {
		return age;
	}

	std::string getName() {
		return name;
	}
};

class Student : public Person {
private:
	std::string degree = "doctor";
public:
	void setDegree(std::string degree) {
		this->degree = degree;
	}
};

int main()
{
	Person kave;
	//kave.setAge(30);


	return 0;
}

