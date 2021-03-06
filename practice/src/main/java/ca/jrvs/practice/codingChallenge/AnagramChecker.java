package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket:   https://www.notion.so/Valid-Anagram-f01cb022d62a42f9815d99f289fe3f71
 */
public class AnagramChecker {

  /**
   * Time complexity: O(nlogn) O(nlogn) sort plus a traversal through the String in O(n) Space
   * complexity: O(n) Needs additional space to store char arrays of Strings
   */
  public boolean isAnagramSort(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    char[] sArray = s.toCharArray();
    char[] tArray = t.toCharArray();
    Arrays.sort(sArray);
    Arrays.sort(tArray);
    for (int i = 0; i < s.length(); i++) {
      if (sArray[i] != tArray[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Time complexity: O(n) O(n) traversal through the Strings plus O(1) traversal through
   * letterCount array. Space complexity: O(1) Only stores a 26 element int array
   */
  public boolean isAnagramLinear(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] letterCount = new int[26];
    for (int i = 0; i < s.length(); i++) {
      letterCount[s.charAt(i) - 'a']++;
      letterCount[t.charAt(i) - 'a']--;
    }
    for (int i : letterCount) {
      if (i != 0) {
        return false;
      }
    }
    return true;
  }
}
