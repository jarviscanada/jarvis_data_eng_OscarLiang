package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-356af6834af94e4196e0e990ea6f8c7f
 */
public class OddEven {

  /**
   * Time complexity: O(1) Justification: Arithmetic operation
   */
  public String oddEvenMod(int i) {
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Time complexity: O(1) Justification: Function uses a single bit operation
   */
  public String oddEvenBit(int i) {
    return (i & 1) == 0 ? "even" : "odd";
  }
}
