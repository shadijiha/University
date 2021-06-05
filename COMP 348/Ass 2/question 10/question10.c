#include <stdio.h>
#include "selectionsort.h"
#include "selectionsort.h" // included twice

int arr[] = {1, 4, 5, 6, -1};

int main()
{
    printf("*********** Question 10 ***********\n");

    int i;
    selectionsort(arr, 5);

    for (i = 0; i < 5; i++)
        printf("%d ", arr[i]);

    printf("\n\n");

    return 0;
}