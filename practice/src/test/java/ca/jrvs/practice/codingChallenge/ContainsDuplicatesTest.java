package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ContainsDuplicatesTest {

  ContainsDuplicates containsDuplicates = new ContainsDuplicates();

  @Test
  public void containsDuplicates() {
    int[] nums = new int[]{1, 2, 3, 4, 5, 6};
    assertFalse(containsDuplicates.containsDuplicates(nums));
    nums = new int[]{1, 2, 3, 4, 1};
    assertTrue(containsDuplicates.containsDuplicates(nums));
  }

  @Test
  public void containsDuplicatesSet() {
    int[] nums = new int[]{1, 2, 3, 4, 5, 6};
    assertFalse(containsDuplicates.containsDuplicatesSet(nums));
    nums = new int[]{1, 2, 3, 4, 1};
    assertTrue(containsDuplicates.containsDuplicatesSet(nums));
  }
}