package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Find-the-Duplicate-Number-a7aab277e7c14dae84e981bef816a12d
 */
public class FindDuplicateNum {

  /**
   * Time complexity: O(n log n) Sorting takes O(n log n) time, then iterate through array once in
   * O(n) time. Space complexity: O(1) no extra space used.
   */
  public int findDuplicateNumSort(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      // Since the numbers are consecutive and will always begin with 1, we can take advantage of
      // this by comparing the value to the index; if the index matches the number, it must be the
      // duplicate.
      if (i == nums[i]) {
        return nums[i];
      }
    }
    // There should always be a duplicate, so this should never be reached.
    return 0;
  }

  /**
   * Time complexity: O(n) perform O(n) hash set operations in O(1) time.
   */
  public int findDuplicateNumSet(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i : nums) {
      if (set.contains(i)) {
        return i;
      }
      set.add(i);
    }
    // There should always be a duplicate, so this should never be reached.
    return 0;
  }

}
