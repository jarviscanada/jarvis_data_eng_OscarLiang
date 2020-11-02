package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoSumTest {

  @Test
  public void twoSumBruteForce() {
    TwoSum twoSum = new TwoSum();
    int[] test1 = {3, 2, 4};
    int[] result1 = twoSum.twoSumBruteForce(test1, 6);
    assertArrayEquals(new int[] {1, 2}, result1);
    int[] test2 = {3, 5, 7, 3};
    int[] result2 = twoSum.twoSumBruteForce(test2, 6);
    assertArrayEquals(new int[] {0, 3}, result2);
    int[] test3 = {7, 2, 9, 4, 1};
    int[] result3 = twoSum.twoSumBruteForce(test3, 7);
    assertNull(result3);
  }

  @Test
  public void twoSumSorted() {
    TwoSum twoSum = new TwoSum();
    int[] test1 = {3, 2, 4};
    assertTrue(twoSum.twoSumSorted(test1, 6));
    int[] test2 = {3, 5, 7, 3};
    assertTrue(twoSum.twoSumSorted(test2, 6));
    int[] test3 = {7, 2, 9, 4, 1};
    assertFalse(twoSum.twoSumSorted(test3, 7));
  }

  @Test
  public void twoSumLinear() {
    TwoSum twoSum = new TwoSum();
    int[] test1 = {3, 2, 4};
    int[] result1 = twoSum.twoSumLinear(test1, 6);
    assertArrayEquals(new int[] {1, 2}, result1);
    int[] test2 = {3, 5, 7, 3};
    int[] result2 = twoSum.twoSumLinear(test2, 6);
    assertArrayEquals(new int[] {0, 3}, result2);
    int[] test3 = {7, 2, 9, 4, 1};
    int[] result3 = twoSum.twoSumLinear(test3, 7);
    assertNull(result3);
  }
}