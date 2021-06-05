#include <math.h>
#include <stdio.h>
#include <stdlib.h>

#include "selectionsort.h"
#include "selectionsort.h"  // included twice

static int* findmax(int arr[], int elements) {
    int max = arr[0];
    int index = 0;
    for (int i = 0; i < elements; i++) {
        if (arr[i] > max) {
            max = arr[i];
            index = i;
        }
    }

    return &arr[index];
}

void displayArray(const char* label, int* array, int elements) {
    printf("\n%s: [ ", label);
    for (int i = 0; i < elements; i++)
        printf("%d ", array[i]);
    printf("]\n");
}

double average(int* array, int elements) {
    double sum = 0.0;
    for (int i = 0; i < elements; i++)
        sum += array[i];

    return sum / (double)elements;
}

double standardDeviation(int* array, int elements) {
    double temp = 0.0;
    double avg = average(array, elements);
    for (int i = 0; i < elements; i++) {
        temp += (array[i] - avg) * (array[i] - avg);
    }

    return sqrt(temp / elements);
}

int main() {
    int n = 0;
    printf("\nEnter the number of elements (n) > ");
    scanf("%d", &n);

    printf("\n");

    // Dynamicly allocated array
    int* array = (int*)malloc(n * sizeof(int));

    for (int i = 0; i < n; i++) {
        printf("Enter element %d > ", i);
        scanf("%d", &array[i]);
    }

    // Display original array
    displayArray("Originial array: ", array, n);

    // Array in ascending order
    selectionsort(array, n, NULL);
    displayArray("Ascending order: ", array, n);

    // Array in descending order
    selectionsort(array, n, &findmax);
    displayArray("Descending order: ", array, n);

    // Display min and max
    printf("\tMin: %d, Max: %d\n, Average: %f, Std deviation: %f",
           *findmin(array, n), *findmax(array, n), average(array, n),
           standardDeviation(array, n));

    // Free memory
    free(array);

    printf("\n\n");

    return 0;
}