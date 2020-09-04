package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

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

  public boolean twoSumSorted(int[] nums, int target) {
    Map<Integer, Integer> originalIndices = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      originalIndices.put(nums[i], i);
    }
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
