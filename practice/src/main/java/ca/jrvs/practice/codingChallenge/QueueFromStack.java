package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/Implement-Queue-using-Stacks-02fcf40065094feb98e82c5ff3c727dd
 */
public class QueueFromStack<E> {

  private final Stack<E> s1 = new Stack<>();
  private final Stack<E> s2 = new Stack<>();

  /**
   * Time complexity: O(n) Pushes and pops all items from a queue twice, which is 2n O(1)
   * operations, or O(n) time.
   */
  public void push(E object) {
    while (!s1.empty()) {
      s2.push(s1.pop());
    }
    s1.add(object);
    while (!s2.empty()) {
      s1.push(s2.pop());
    }
  }

  /**
   * Time complexity: O(1) Pops a single item in constant time
   */
  public E pop() {
    return s1.pop();
  }

  /**
   * Time complexity: O(1) Peeks at a single item in constant time
   */
  public E peek() {
    return s1.peek();
  }

  /**
   * Time complexity: O(1) Checking the size of the stack in constant time (counter variable in
   * Stack<E> implementation)
   */
  public boolean empty() {
    return s1.empty();
  }
}
