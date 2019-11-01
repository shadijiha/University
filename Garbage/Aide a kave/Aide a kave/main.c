#include <stdio.h>
#include <stdlib.h> // For exit() function

#define _CRT_SECURE_NO_WARNINGS

int main()
{
	char c[1000];
	FILE* fptr;
	if ((fptr = fopen_s(&fptr, "test.txt", "r")) == NULL)
	{
		printf("Error! opening file");
		// Program exits if file pointer returns NULL.
		exit(1);
	}
	// reads text until newline 
	fscanf_s(fptr, "%[^\n]", c);
	printf("Data from the file:\n%s", c);
	fclose(fptr);

	return 0;
}