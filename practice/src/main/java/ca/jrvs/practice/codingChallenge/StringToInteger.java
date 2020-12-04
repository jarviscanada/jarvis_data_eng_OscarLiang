package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/String-to-Integer-atoi-5ce4e0bc47c8420c8be3f052b816a54a
 */

public class StringToInteger {

  /**
   * Time Complexity: O(n) parseInt uses a similar method of parsing as the manual method below
   */
  public int stringToIntegerBuiltIn(String s) {
    int n;
    try {
      // It should be noted that it is not possible to emulate the behaviour that the online judge
      // uses with this method because parseInt can't be changed.
      n = Integer.parseInt(s.trim().split(" ")[0]);
    } catch (NumberFormatException e) {
      n = 0;
    }
    return n;
  }

  /**
   * Time Complexity: O(n) perform O(1) operations on each digit of the string.
   */
  public int stringToIntegerManual(String s) {
    String firstString = s.trim().split(" ")[0];
    if (firstString.length() == 0) {
      return 0;
    }

    // Check first character for + or -
    int index = 0;
    char currentChar = firstString.charAt(index);
    boolean negative = false;
    if (currentChar == '+') {
      index++;
    } else if (currentChar == '-') {
      negative = true;
      index++;
    } else if (!isDigit(currentChar)) {
      return 0;
    }

    int value = 0;
    while (index < firstString.length() && isDigit(firstString.charAt(index))) {
      currentChar = firstString.charAt(index);
      try {
        value = Math.multiplyExact(value, 10);
        value = Math.addExact(value, currentChar - '0');
      } catch (ArithmeticException e) {
        if (negative) {
          value = Integer.MIN_VALUE;
        } else {
          value = Integer.MAX_VALUE;
        }
        break;
      }
      index++;
    }
    if (negative) {
      value = value * -1;
    }

    return value;
  }

  private boolean isDigit(char c) {
    int charDiff = c - '0';
    return (charDiff < 10) && (charDiff >= 0);
  }

}
