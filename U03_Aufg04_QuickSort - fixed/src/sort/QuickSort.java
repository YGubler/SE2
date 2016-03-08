package sort;

public class QuickSort {
	public static <T extends Comparable<T>> void sortArray(T[] array) {
		if (array.length > 0) {
			quickSort(array, 0, array.length - 1);
		}
	}
	
	private static <T extends Comparable<T>> void quickSort(T[] array, int l, int r) {
		int i = l, j = r;
    T m = array[(l + r) / 2];
    do {
      while (array[i].compareTo(m) < 0) { i++; }
      while (array[j].compareTo(m) > 0) { j--; }
      if (i <= j) {
        T t = array[i]; array[i] = array[j]; array[j] = t;
        i++; j--;
      }
    } while (i <= j);
    if (j > l) { quickSort(array, l, j); }
    if (i < r) { quickSort(array, i, r); }
	}
}
