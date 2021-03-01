package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Swap-two-numbers-26f454c7c5084f75a3370d9675c7c42c
 */
public class SwapNumbers {

  /**
   * Time complexity: O(1) - constant time operations
   */
  public void swapNumbersBitwise(int[] nums) {
    nums[0] = nums[0] ^ nums[1];
    nums[1] = nums[0] ^ nums[1];
    nums[0] = nums[0] ^ nums[1];
  }

  /**
   * Time complexity: O(1) - constant time operations
   */
  public void swapNumbersArithmetic(int[] nums) {
    nums[0] = nums[0] + nums[1];
    nums[1] = nums[0] - nums[1];
    nums[0] = nums[0] - nums[1];
  }
}
