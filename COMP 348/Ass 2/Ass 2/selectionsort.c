#include "selectionsort.h";

// *********** Question 9 ***********
static int* findmin(int* arr, int size) {

	int min = arr[0];
	int index = 0;
	for (int i = 0; i < size; i++) {
		if (arr[i] < min) {
			min = arr[i];
			index = i;
		}
	}

	return &arr[index];
}

void selectionsort(int* array, int elements) {

	int minIndex = 0;

	for(int i = 0; i < elements - 1; i++) {
		minIndex = i;

		for(int j = i + 1; j < elements; j++) {
			if (array[j] < array[minIndex])
				minIndex = j;
		}

		int temp = array[minIndex];
		array[minIndex] = array[i];
		array[i] = temp;		
	}
	
}