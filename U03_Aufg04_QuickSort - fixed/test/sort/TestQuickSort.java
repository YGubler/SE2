package sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import sort.QuickSort;

public class TestQuickSort {
	private static final int MaxTestLen = 32 * 1024;
	
	@Test
	public void testEmpty() {
		checkSorted(new Integer[] { });
	}
	
	@Test
	public void testSingle() {
		checkSorted(new Integer[] { 1 });
	}
	
	@Test
	public void testMultiple() {
		checkSorted(new Integer[] { 1, 2, 2, 1 });
	}
	
	@Test
	public void testOddUnsorted() {
		checkSorted(new Integer[] { 1, 3, 2 });
	}
	
	@Test
	public void testEvenUnsorted() {
		checkSorted(new Integer[] { 1, 3, 2, 0 });
	}
	
	@Test
	public void testLong() {
		Random rand = new Random(4711);
		Long[] array = new Long[MaxTestLen];
		for (int i = 0; i < MaxTestLen; i++) {
			array[i] = rand.nextLong();
		}
		checkSorted(array);
	}
	
	@Test
	public void testDouble() {
		Random rand = new Random(4711);
		Double[] array = new Double[MaxTestLen];
		for (int i = 0; i < MaxTestLen; i++) {
			array[i] = rand.nextDouble();
		}
		checkSorted(array);
	}
	
	private <T extends Comparable<T>> void checkSorted(T[] array) {
		QuickSort.sortArray(array);
		T[] clone = Arrays.copyOf(array, array.length);
		Arrays.sort(clone);
		Assert.assertArrayEquals(array, clone);
	}
}
