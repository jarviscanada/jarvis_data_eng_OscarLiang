package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Merge-Sorted-Array-79fde1b75d8e4817bd84726a5f605a3c
 */
public class MergeSortedArray {

  /**
   * Time complexity: O(m + n) - constant time loop repeated m + n times Space compexity: O(1) - a
   * few constant variables used
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int mIndex = m - 1;
    int nIndex = n - 1;

    for (int i = m + n - 1; i >= 0; i--) {
      if (nIndex < 0 || (mIndex >= 0 && nums1[mIndex] > nums2[nIndex])) {
        nums1[i] = nums1[mIndex];
        mIndex--;
      } else {
        nums1[i] = nums2[nIndex];
        nIndex--;
      }
    }
  }

}
