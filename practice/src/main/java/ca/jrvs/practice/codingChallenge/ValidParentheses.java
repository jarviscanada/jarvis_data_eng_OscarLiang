package ca.jrvs.practice.codingChallenge;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/Valid-Parentheses-2b9da96fed0947abab6f4df62ae55a08
 */
public class ValidParentheses {

  /**
   * Time complexity: O(n)
   * Iterates through the entire string and performs O(1) operations on each character.
   */
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    Map<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('{', '}');
    map.put('[', ']');

    char[] chars = s.toCharArray();
    for (char current : chars) {
      if (map.containsKey(current)) {
        stack.push(current);
      } else {
        if (stack.empty()) {
          return false;
        } else {
          char match = stack.pop();
          if (map.get(match) != current) {
            return false;
          }
        }
      }
    }
    return stack.empty(); // Non-empty stack at this point means extra left parenthesis
  }

}
