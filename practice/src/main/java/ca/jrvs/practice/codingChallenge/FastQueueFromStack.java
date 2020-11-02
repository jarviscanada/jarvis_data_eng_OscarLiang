package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/Implement-Queue-using-Stacks-02fcf40065094feb98e82c5ff3c727dd
 */
public class FastQueueFromStack<E> {

  private Stack<E> inStack = new Stack<>();
  private Stack<E> outStack = new Stack<>();

  /**
   * Time complexity: O(1)
   * Pushing to a stack in constant time
   */
  public void push(E object) {
    inStack.push(object);
  }

  /**
   * Time complexity: O(1)
   * Note that the inStack is cleared of all elements only when outStack is empty. Each element is
   * pushed into each queue once and popped out of each queue once, giving an amortized O(1) time.
   */
  public E pop() {
    if (outStack.empty()) {
      while (!inStack.empty()) {
        outStack.push(inStack.pop());
      }
    }
    return outStack.pop();
  }

  /**
   * Time complexity: O(1)
   * Peeking at a stack in constant time. Similar to pop, outStack is pushed to only when it is
   * empty, which is an amortized cost for pop instead of peek.
   */
  public E peek() {
    if (outStack.empty()) {
      while (!inStack.empty()) {
        outStack.push(inStack.pop());
      }
    }
    return outStack.peek();
  }

  /**
   * Time complexity: O(1)
   * Checking the size of the stack in constant time (counter variable in Stack<E> implementation)
   */
  public boolean empty() {
    return inStack.empty() && outStack.empty();
  }
}
