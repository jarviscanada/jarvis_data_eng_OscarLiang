package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * ticket: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-d18d5827a97d42879e241e009beeaaee
 */
public class Fibonacci {

  /**
   * Time complexity: O(2^n) Space complexity: O(n) Binary recursion tree of height n, which has
   * O(2^n) nodes, each of which have O(1) runtime. Maximum number of recursive functions running is
   * the maximum depth of the tree, so space complexity is O(n).
   */
  public int fibonacciRecursion(int n) {
    if (n == 1 || n == 0) {
      return n;
    }
    return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
  }

  /**
   * Time complexity: O(n) Space complexity: O(n) Need to find all terms of Fibonacci sequence up to
   * nth term, each of which takes O(1) time. Array to store all terms of Fibonacci sequence up to
   * nth term.
   */
  public int fibonacciDynamic(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    List<Integer> fibList = new ArrayList<>();
    fibList.add(1);
    fibList.add(1);
    for (int i = 2; i < n; i++) {
      fibList.add(fibList.get(i - 1) + fibList.get(i - 2));
    }
    return fibList.get(n - 1);
  }

}
