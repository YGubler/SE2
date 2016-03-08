package sort;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import sort.QuickSort;

public class TestQuickSort {
	@Test
	public void testSimple() {
		Integer[] array = new Integer[] { 1, 3, 4, 2, 5, 0 };
		new QuickSort<Integer>().sortArray(array);
		Integer[] clone = Arrays.copyOf(array, array.length);
		Arrays.sort(clone);
		Assert.assertArrayEquals(array, clone);
	}
}
