package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  /**
   * Time complexity: O(n^2)
   * Brute force method uses a nested for loop to iterate through all elements in O(n^2) time.
   */
  public int[] twoSumBruteForce(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i; j < nums.length; j++) {
        if (nums[i] + nums[j] == target && i != j) {
          return new int[] {i, j};
        }
      }
    }
    return null;
  }

  /**
   * Time complexity: O(n + nlogn)
   * Assume that the Java sorting method takes O(nlogn) time. Then, after the array has been sorted,
   * Iterate through the loop from both ends only once in O(n) time.
   */
  public boolean twoSumSorted(int[] nums, int target) {
    Arrays.sort(nums);
    int leftIndex = 0;
    int rightIndex = nums.length - 1;
    while (rightIndex > leftIndex) {
      int result = nums[leftIndex] + nums[rightIndex];
      if (result > target) {
        rightIndex--;
      }
      else if (result < target) {
        leftIndex++;
      }
      else {
        return true;
      }
    }
    return false;
  }

  /**
   * Time complexity: O(n)
   * Space complexity: O(n)
   * Store the array in a map, which is O(N) space. Iterate through the array twice in O(n) time
   * each.
   */
  public int[] twoSumLinear(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
      int subtracted = target - nums[i];
      if (map.containsKey(subtracted) && map.get(subtracted) != i) {
        return new int[] {i, map.get(subtracted)};
      }
    }
    return null;
  }
}
