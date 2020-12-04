package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Rotate-String-357ad975b99a452bb02cf5320f69bc68
 */
public class RotateString {

  /**
   * Time complexity: O(n^2) - String.contains() has a time complexity of O(nm), where n and m are
   * the lengths of the two strings. Since the strings are length 2n and n, the time complexity is
   * O(n^2)
   * Space complexity: O(n) - Build a string of size 2n
   */
  public boolean rotateString(String a, String b) {
    return a.length() == b.length() && (a + a).contains(b);
  }
}
