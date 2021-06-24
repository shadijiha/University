#include <stdio.h>
#include "selectionsort.h"
#include "selectionsort.h"  // included twice

int arr[] = {4, 5, 3, 2, 1};

int main() {
    printf("*********** Question 10 ***********\n");

    int i;
    selectionsort(arr, 5);

    for (i = 0; i < 5; i++)
        printf("%d ", arr[i]);

    printf("\n\n");

    return 0;
}