// ToDoList.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#include <fstream>
#include <string>



int main()
{
    
	int option = 0;
	const unsigned int EXIT = 4;
	std::ofstream file("toDos.txt");
	file.open("toDos.txt");
	file << "?XD";

	std::cout << "Welcome to Shado to do list organiser:" << std::endl;
	std::cout << "=====================================\n" << std::endl;
	std::cout << "Select an option from 1-5: \n" << std::endl;
	std::cout << "1- View all toDOs" << std::endl;
	std::cout << "2- Add a toDo" << std::endl;
	std::cout << "3- Delete a toDo" << std::endl;
	std::cout << "4- Exit application\n" << std::endl;
	std::cout << "Choose an option: ";
	std::cin >> option;

	/*while(option != EXIT) {
	
		switch (option) {

		case 1:
			
		
		}


	}*/

	//==================================================
	file.close();

	return 0;
}

