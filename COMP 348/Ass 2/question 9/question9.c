#include <stdio.h>

int *findmin(int *arr, int size)
{

    int min = arr[0];
    int index = 0;
    for (int i = 0; i < size; i++)
    {
        if (arr[i] < min)
        {
            min = arr[i];
            index = i;
        }
    }

    return &arr[index];
}

int main()
{

    printf("*********** Question 9 ***********\n");

    int arr[] = {1, 4, 5, -6, -1};
    int *m = findmin(arr, 5);
    printf("%d", *m); // -1

    return 0;
}