import java.util.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {

		/*WUGraph graph = new WUGraph();
		Vertex[] v = new Vertex[10];
		for (int i = 0; i < v.length; i++) {
			v[i] = new Vertex(i + "");
			graph.addVertex(v[i]);
		}

		v[0].addUndirectedEdge(v[6]);
		v[0].addUndirectedEdge(v[9]);
		v[1].addUndirectedEdge(v[2]);
		v[1].addUndirectedEdge(v[7]);
		v[2].addUndirectedEdge(v[7]);
		v[2].addUndirectedEdge(v[8]);
		v[2].addUndirectedEdge(v[4]);
		v[2].addUndirectedEdge(v[3]);
		v[2].addUndirectedEdge(v[5]);
		v[3].addUndirectedEdge(v[5]);
		v[4].addUndirectedEdge(v[5]);
		v[4].addUndirectedEdge(v[6]);
		v[5].addUndirectedEdge(v[6]);
		v[6].addUndirectedEdge(v[8]);
		v[6].addUndirectedEdge(v[9]);
		v[7].addUndirectedEdge(v[8]);
		v[8].addUndirectedEdge(v[9]);

		graph.BFS(v[0], (e) -> {
			System.out.print(e + " ");
		});*/

		int[] arr = {12, 47, 74, 19, 89, 4, 63, 26, 53, 8, 93, 71, 15, 87, 50, 17, 82};
		quickSort(arr, 0, arr.length - 1);
	}

	/* This function takes last element as pivot, places
	   the pivot element at its correct position in sorted
	   array, and places all smaller (smaller than pivot)
	   to left of pivot and all greater elements to right
	   of pivot */
	static int partition(int[] arr, int left, int right) {
		int i = left, j = right;

		int tmp;

		int pivot = arr[(left + right) / 2];
		System.out.printf("Pivot: %d, left: %d, right: %d\n", pivot, arr[left], arr[right]);
		System.out.println(Arrays.toString(arr));
		while (i <= j) {
			while (arr[i] < pivot)
				i++;

			while (arr[j] > pivot)
				j--;

			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		return i;
	}

	/* The main function that implements QuickSort
			  arr[] --> Array to be sorted,
			  low --> Starting index,
			  high --> Ending index
	 */
	static void quickSort(int[] arr, int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}
}
