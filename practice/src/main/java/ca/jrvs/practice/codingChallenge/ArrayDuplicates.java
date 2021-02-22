package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Duplicates-from-Sorted-Array-8cee7d8d927b448ea35b33fe6b63b3bb
 */
public class ArrayDuplicates {

  /**
   * Time complexity: O(n) - one pass through the array with constant time operations. Space
   * complexity: O(1) - two additional variables used.
   */
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int slowIndex = 0;
    for (int fastIndex = 1; fastIndex < nums.length; fastIndex++) {
      if (nums[fastIndex] != nums[slowIndex]) {
        slowIndex++;
        nums[slowIndex] = nums[fastIndex];
      }
    }
    return slowIndex + 1;
  }
}
