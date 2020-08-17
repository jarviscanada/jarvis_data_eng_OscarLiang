package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/Implement-Stack-using-Queue-47db24a04e9946059560be94067964c4
 * @param <E>
 */
public class StackFromTwoQueues<E> {

  private Queue<E> q1 = new LinkedList<>();
  private Queue<E> q2 = new LinkedList<>();

  private void swapQueues() {
    Queue<E> temp = q1;
    q1 = q2;
    q2 = temp;
  }

  /**
   * Time complexity: O(n)
   * Push requires popping all objects from one queue and pushing them to the other, which is n O(1)
   * operations, or O(n).
   */
  public void push(E object) {
    q1.add(object);
    while (!q2.isEmpty()) {
      q1.add(q2.remove());
    }
    swapQueues();
  }

  /**
   * Time complexity: O(1)
   * Popping from the head of the queue, which is an O(1) operation
   */
  public E pop() {
    return q2.remove();
  }

  /**
   * Time complexity: O(1)
   * Peeking at head of queue is constant time.
   */
  public E top() {
    return q2.peek();
  }

  /**
   * Time complexity: O(1)
   * Checking for size == 0 is constant time.
   */
  public boolean empty() {
    return q2.isEmpty();
  }
}
