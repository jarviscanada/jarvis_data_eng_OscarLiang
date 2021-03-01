package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Contains-Duplicate-a6a1aea7139e4b7b900784b2ff0f784b
 */
public class ContainsDuplicates {

  /**
   * Time complexity: O(n^2) - double for loop iterating through array in O(n^2) time. Space
   * complexity: O(1) - constant sized variables
   */
  public boolean containsDuplicates(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int numToCheck = nums[i];
      for (int j = i + 1; j < nums.length; j++) {
        if (numToCheck == nums[j]) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Time complexity: O(n) - iterate through array in linear time. Space complexity: O(n) - store
   * single copy of values in hash table
   */
  public boolean containsDuplicatesSet(int[] nums) {
    Set<Integer> uniqueValues = new HashSet<>();
    for (int num : nums) {
      if (uniqueValues.contains(num)) {
        return true;
      } else {
        uniqueValues.add(num);
      }
    }
    return false;
  }
}
