package ca.jrvs.practice.codingChallenge;

import java.util.regex.Pattern;

/**
 * ticket: https://www.notion.so/Check-if-a-String-contains-only-digits-7cd0859dd0c74e2eba3bfc10d61d4866
 */
public class CheckStringDigits {

  /**
   * Time complexity: O(n) check value of character n times for O(n) complexity.
   */
  public boolean checkStringDigitsAscii(String s) {
    int length = s.length();
    for (int i = 0; i < length; i++) {
      int charDifference = s.charAt(i) - '0';
      if (charDifference >= 10 || charDifference < 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Time complexity: O(n) converts a string to an integer in O(n) time
   */
  public boolean checkStringDigitsValueOf(String s) {
    if (s.length() == 0) {
      return true;
    }
    boolean result = true;
    try {
      Integer.valueOf(s);
    } catch (NumberFormatException e) {
      result = false;
    }
    return result;
  }

  /**
   * Time complexity: O(n) Perform regular expression matching in O(n) time.
   */
  public boolean checkStringDigitsRegex(String s) {
    return Pattern.compile("^\\d*$").matcher(s).matches();
  }

}
