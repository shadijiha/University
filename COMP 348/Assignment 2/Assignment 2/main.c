#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* QUESTION 1 */
void CallFun(int* p_number) {
	*p_number = 17;
}

/* QUESTION 2 */
void DisplayArray(int* array, const uint16_t count) {

	printf("[");

	// Obviously there's a solution much simpler than this, but the question requires
	// Full pointer arthmatic
	for (int* pointer = array; pointer < array + count; pointer++) {
		printf("%d, ", *pointer);
	}

	printf("]\n");	
}

void SelectionSort(int* array, const uint16_t count) {

	for (int i = 0; i < count - 1; i++) {

		// Find the minimum number
		int min_index = i;
		for (int j = i + 1; j < count; j++)
			if (array[j] < array[min_index])
				min_index = j;

		// Swap element found with the first element
		int temp = array[min_index];
		array[min_index] = array[i];
		array[i] = temp;	
	}
}

/* QUESTION 3 */
typedef struct Book	{
	char* title;
	float price;
} Book;

void Display(Book array[], const uint16_t count) {

	for (int i = 0; i < count; i++)
		printf("Book %s has a price of %.2f$\n", array[i].title, array[i].price);	
}

float AverageBookPrice(Book array[], const uint16_t count) {

	float sum = 0.0f;
	for (int i = 0; i < count; i++)
		sum += array[i].price;

	return sum / (float)count;
}

Book* Add(Book array[], const uint16_t count) {

	// Prompt the user for the info of the book
	char title[20];
	float price;

	printf("Add %dth book to the book array:\n", count + 1);
	printf("Enter the title of book %d > ", count + 1);
	scanf("%s", title);

	printf("Enter the price of %s > ", title);
	scanf("%f", &price);

	Book temp = { "" , price };

	// Copy the string title of the book
	size_t title_length = strlen(title);
	temp.title = (char*)malloc(sizeof(char) * title_length + 1);

	// Copy the temp Title to the Object title
	for (int j = 0; j < title_length; j++)
		temp.title[j] = title[j];

	temp.title[title_length] = '\0';


	/* ********* EXPAND THE ARRAY ********** */
	size_t new_size = count + 1;
	Book* new_array = (Book*)malloc(sizeof(Book) * new_size);

	// copy the old array
	for (int i = 0; i < count; i++)
		new_array[i] = array[i];

	new_array[new_size - 1] = temp;

	// Free old heap allocated array
	free(array);

	return new_array;
}

int main(int carg, const char** vargs) {

	/* Code for question 1 */
	printf("************ QUESTION 1 ************\n");
	
	int a = 3;
	CallFun(&a);

	printf("a = %d\n", a);

	/* Code for question 2 */
	printf("\n************ QUESTION 2 ************\n");

	int arr[] = {2, 5, 1, 13, 10};
	DisplayArray(arr, 5);
	SelectionSort(arr, 5);
	DisplayArray(arr, 5);

	/* Code for question 3 */
	printf("\n************ QUESTION 3 ************\n");

	const int book_count = 0;
	printf("Enter the number of books to create (n) > ");
	scanf("%d", &book_count);

	// Must be heap allocated since we are creating it using a variable 
	Book* book_array = (Book*)malloc(sizeof(Book) * book_count);

	for (int i = 0; i < book_count; i++) {
		char title[20];
		float price;
		
		printf("Book %d of %d:\n", i + 1, book_count);
		printf("Enter the title of book %d > ", i + 1);
		scanf("%s", title);

		printf("Enter the price of %s > ", title);
		scanf("%f", &price);

		Book temp = { "" , price};

		// Copy the string title of the book
		size_t title_length = strlen(title);
		temp.title = (char*)malloc(sizeof(char) * title_length + 1);

		// Copy the temp Title to the Object title
		for (int j = 0; j < title_length; j++)
			temp.title[j] = title[j];

		temp.title[title_length] = '\0';
		
		book_array[i] = temp;
	}

	printf("\n\n");
	Display(book_array, book_count);

	printf("\nThe average cost of the Books is %.2f$\n\n", AverageBookPrice(book_array, book_count));

	// Add function
	book_array = Add(book_array, book_count);
	Display(book_array, book_count + 1);
	
	// Free memory for heap allocated array
	free(book_array);

	return 0;
}