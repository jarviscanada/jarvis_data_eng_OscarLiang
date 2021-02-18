package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Print-letter-with-number-713db1975d4f493caa6c83ff37eccfd0
 */
public class PrintLetterAndNumber {

  /**
   * Time complexity: O(n) - operate on each character of the string with constant time Space
   * complexity: O(n) - create another string proportional to size of string
   */
  public String printLetterAndNumber(String s) {
    StringBuilder stringBuilder = new StringBuilder();
    char[] chars = s.toCharArray();
    for (char c : chars) {
      stringBuilder.append(c);
      // Since the string contains only upper and lowercase letters, we can assume that if c - 'a'
      // is negative, it is an uppercase letter.
      if (c - 'a' < 0) {
        stringBuilder.append(c - 'A' + 27);
      } else {
        stringBuilder.append(c - 'a' + 1);
      }
    }
    return stringBuilder.toString();
  }

}
