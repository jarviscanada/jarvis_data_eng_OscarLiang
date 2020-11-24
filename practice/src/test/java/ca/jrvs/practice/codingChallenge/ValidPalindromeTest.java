package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidPalindromeTest {

  @Test
  public void isPalindromePointers() {
    ValidPalindrome vp = new ValidPalindrome();
    assertTrue(vp.isPalindromePointers("racecar"));
    assertTrue(vp.isPalindromePointers("A man, a plan, a canal, Panama."));
    assertTrue(vp.isPalindromePointers("palindrome emordnilap"));
    assertFalse(vp.isPalindromePointers("palindrome"));
  }

  @Test
  public void isPalindromeRecursion() {
    ValidPalindrome vp = new ValidPalindrome();
    assertTrue(vp.isPalindromeRecursion("racecar"));
    assertTrue(vp.isPalindromeRecursion("A man, a plan, a canal, Panama."));
    assertTrue(vp.isPalindromeRecursion("palindrome emordnilap"));
    assertFalse(vp.isPalindromeRecursion("palindrome"));
  }
}