package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MergeSortedArrayTest {

  MergeSortedArray mergeSortedArray = new MergeSortedArray();

  @Test
  public void merge() {
    int[] array1 = new int[]{1, 2, 3, 0, 0, 0};
    int[] array2 = new int[]{4, 5, 6};
    mergeSortedArray.merge(array1, 3, array2, 3);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, array1);
    array1 = new int[]{4, 5, 6, 0, 0, 0};
    array2 = new int[]{1, 2, 3};
    mergeSortedArray.merge(array1, 3, array2, 3);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, array1);
    array1 = new int[]{1, 4, 6, 0, 0, 0};
    array2 = new int[]{2, 3, 5};
    mergeSortedArray.merge(array1, 3, array2, 3);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, array1);
  }
}