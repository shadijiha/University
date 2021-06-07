#include "selectionsort.h"

static int* findmin(int arr[], int size) {
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

void selectionsort(int array[], int elements) {
    for (int i = 0; i < elements; i++) {
        // Get the minimum fro the unsorted sublist.
        // The key here is the +i and -i which determine the bounds of the
        // unsorted sublist
        int* minPtr = findmin(array + i, elements - i);

        // Get the index of the minimum number in the Array from its pointer
        int index = minPtr - (array + i);

        int temp = array[i];
        array[i] = array[i + index];
        array[i + index] = temp;
    }
}