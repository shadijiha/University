/**
 *
 */

package driver;

public class StackWithMax {

	private int[] data;
	int pointer;
	int size;
	int max;        // <---- This variable is important

	/* Unnecessary Implementation details hidden */

	public void push(int number) {
		data[pointer++] = number;
		size++;

		if (number > max)        // <---- This part is really important for max()
			max = number;
	}

	public void pop() {
		int to_remove = data[pointer];
		data[pointer--] = 0;
		size--;

		// If the element to remove is also the max
		// Then we have to recalculate the max
		int current_max = Integer.MIN_VALUE;
		if (to_remove == max) {

			for (int temp : data) {
				if (temp > current_max)
					current_max = temp;
			}

			max = current_max;
		}

	}

	public int max() {
		return max;
	}
}
