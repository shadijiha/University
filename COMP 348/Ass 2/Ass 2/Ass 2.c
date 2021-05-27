// Ass 2.cpp : This file contains the 'main' function. Program execution begins and ends there.
//
#include <stdio.h>
#define QUESTION_TO_DISPLAY 10

#include "selectionsort.h"
#include "selectionsort.h" // included twice



int main()
{
	if (QUESTION_TO_DISPLAY == 9) {
		
		printf("*********** Question 9 ***********\n");
		int arr[] = { 1, 4, 5, -6, -1 };
		int* m = findmin(arr, 5);
		printf("%d", *m); // -1
		
	} else if (QUESTION_TO_DISPLAY == 10) {
		printf("*********** Question 10 ***********\n");
		
		int arr[] = { 1, 4, 5, 6, -1 };
		int i;
		selectionsort(arr, 5);
		for (i = 0; i < 5; i++) printf("%d ", arr[i]);
	} else if (QUESTION_TO_DISPLAY == 11) {
		printf("*********** Question 11 ***********\n");
		
	}

	return 0;
}

