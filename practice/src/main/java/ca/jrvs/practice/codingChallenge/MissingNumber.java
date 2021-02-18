package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Missing-Number-586e97554a264e47a9080714be4782f1
 */
public class MissingNumber {

  /**
   * Time complexity: O(n) - Iterate through all numbers in array and operate on each in constant
   * time.
   * <p>
   * Space complexity: O(1) - Only one additional variable needed.
   */
  public int missingNumberSum(int[] nums) {
    int missingNumber = 0;
    for (int i = 0; i < nums.length; i++) {
      missingNumber += i + 1;
      missingNumber -= nums[i];
    }
    return missingNumber;
  }

  /**
   * Time complexity: O(n) - iterate through array in linear time, then check n numbers in set.
   * <p>
   * Space complexity: O(n) - use a hash table to store all numbers in array.
   */
  public int missingNumberSet(int[] nums) {
    Set<Integer> numberSet = new HashSet<>();
    for (int num : nums) {
      numberSet.add(num);
    }
    for (int i = 0; ; i++) {
      if (!numberSet.contains(i)) {
        return i;
      }
    }
  }

  /**
   * Time complexity: O(n) - iterate through array in linear time.
   * <p>
   * Space complexity: O(1) - one additional variable used.
   */
  public int missingNumberGauss(int[] nums) {
    int missingNumber = gaussFormula(nums.length);
    for (int num : nums) {
      missingNumber -= num;
    }
    return missingNumber;
  }

  private int gaussFormula(int num) {
    return num * (num + 1) / 2;
  }

}
