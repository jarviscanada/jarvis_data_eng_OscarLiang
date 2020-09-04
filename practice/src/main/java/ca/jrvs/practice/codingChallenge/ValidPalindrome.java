package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {

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

  public boolean isPalindromeRecursion(String s) {

//    return s.charAt(0) == s.charAt(alphanume)
  }
}
