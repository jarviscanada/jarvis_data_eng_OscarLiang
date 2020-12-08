package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Valid-Palindrome-e65c688b91ac42f2b3a4d81714656c38
 */
public class ValidPalindrome {

  /**
   * Time complexity: O(n) Iterate through the string from both sides in O(n) time.
   */
  public boolean isPalindromePointers(String s) {
    String alphanumeric = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    int leftIndex = 0;
    int rightIndex = alphanumeric.length() - 1;
    while (leftIndex < rightIndex) {
      if (alphanumeric.charAt(leftIndex) != alphanumeric.charAt(rightIndex)) {
        return false;
      }
      leftIndex++;
      rightIndex--;
    }
    return true;
  }

  /**
   * Time complexity: O(n) Space complexity: O(n) Call a constant time function n/2 times for O(n)
   * time and space complexity.
   */
  public boolean isPalindromeRecursion(String s) {
    String alphanumeric = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    return isPalindromeRecursionHelper(alphanumeric);
  }

  // Separate the function that performs the actual recursion to reduce unnecessary calls to
  // format the string into alphanumeric lowercase
  private boolean isPalindromeRecursionHelper(String s) {
    if (s.length() <= 1) {
      return true;
    }
    return (s.charAt(0) == s.charAt(s.length() - 1)) &&
        isPalindromeRecursionHelper(s.substring(1, s.length() - 1));
  }
}
