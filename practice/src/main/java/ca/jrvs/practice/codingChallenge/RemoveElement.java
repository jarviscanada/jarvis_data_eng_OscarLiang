package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Remove-Element-278fd9f0d18c4b86a9023a88c572ea48
 */
public class RemoveElement {

  /**
   * Time complexity: O(n) - iterates through entire array and operates on elements in constant time
   * Space complexity: O(1) - only uses some variables
   */
  public int removeElement(int[] nums, int val) {
    int left = 0;
    int right = nums.length - 1;
    int counter = 0; // Counts number of removed elements
    while (left <= right) {
      while (nums[left] == val && left <= right) {
        nums[left] = nums[right];
        right--;
        counter++;
      }
      left++;
    }
    return nums.length - counter;
  }

}
