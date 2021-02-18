package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindDuplicateNumTest {

  FindDuplicateNum fdn = new FindDuplicateNum();

  @Test
  public void findDuplicateNumSort() {
    int[] nums = new int[]{3, 1, 6, 7, 2, 4, 3, 5};
    assertEquals(3, fdn.findDuplicateNumSort(nums));
    nums = new int[]{1, 7, 2, 5, 9, 6, 6, 4, 8, 3};
    assertEquals(6, fdn.findDuplicateNumSort(nums));
  }

  @Test
  public void findDuplicateNumSet() {
    int[] nums = new int[]{3, 1, 6, 7, 2, 4, 3, 5};
    assertEquals(3, fdn.findDuplicateNumSet(nums));
    nums = new int[]{1, 7, 2, 5, 9, 6, 6, 4, 8, 3};
    assertEquals(6, fdn.findDuplicateNumSet(nums));
  }
}