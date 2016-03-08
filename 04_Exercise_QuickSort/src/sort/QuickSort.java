package sort;

public class QuickSort<T extends Comparable<T>> {
	public void sortArray(T[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(T[] array, int left, int right) {
		int i = left, j = right;
		T m = array[(left + right) / 2];
		do {
			while (array[i].compareTo(m) < 0) {
				i++;
			}
			while (array[j].compareTo(m) > 0) {
				j--;
			}
			if (i <= j) {
				T t = array[i];
				array[i] = array[j];
				array[j] = t;
				i++;
			}
		} while (i <= j);
		if (j > left) {
			quickSort(array, left, j);
		}
		if (i < right) {
			quickSort(array, i, right);
		}
	}
}
