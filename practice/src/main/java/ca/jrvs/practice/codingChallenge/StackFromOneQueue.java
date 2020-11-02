package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/Implement-Stack-using-Queue-47db24a04e9946059560be94067964c4
 */
public class StackFromOneQueue<E> {

  private final Queue<E> queue = new LinkedList<>();

  /**
   * Time complexity: O(n)
   * Pushing to the queue requires popping and re-pushing all previous elements back into the queue,
   * which is n O(1) operations, or O(n) time.
   */
  public void push(E object) {
    int size = queue.size();
    queue.add(object);
    for (int i = 0; i < size; i++) {
      queue.add(queue.remove());
    }
  }

  /**
   * Time complexity: O(1)
   * Popping from the head of the queue is an O(1) operation.
   */
  public E pop() {
    return queue.remove();
  }

  /**
   * Time complexity: O(1)
   * Peeking at head of queue is constant time.
   */
  public E top() {
    return queue.peek();
  }

  /**
   * Time complexity: O(1)
   * Checking for size == 0 is constant time.
   */
  public boolean empty() {
    return queue.isEmpty();
  }
}
