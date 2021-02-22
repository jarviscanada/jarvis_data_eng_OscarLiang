package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDuplicatesTest {

  ArrayDuplicates arrayDuplicates = new ArrayDuplicates();

  @Test
  public void removeDuplicates() {
    int[] nums = new int[0];
    assertEquals(0, arrayDuplicates.removeDuplicates(nums));

    nums = new int[] {0, 1, 2, 3, 3, 4, 5, 6, 7, 7, 7, 8, 9, 9};
    assertEquals(10, arrayDuplicates.removeDuplicates(nums));
    for (int i = 0; i < 10; i++) {
      assertEquals(i, nums[i]);
    }

    nums = new int[] {0, 1, 2, 3, 4, 5, 6, 7 ,8, 9};
    assertEquals(10, arrayDuplicates.removeDuplicates(nums));
    for (int i = 0; i < 10; i++) {
      assertEquals(i, nums[i]);
    }
  }
}