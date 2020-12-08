package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FastQueueFromStackTest {

  @Test
  public void pushAndPop() {
    FastQueueFromStack<Integer> queue = new FastQueueFromStack<>();
    queue.push(1);
    queue.push(2);
    assertEquals(1, (int) queue.pop());
    queue.push(3);
    queue.push(4);
    assertEquals(2, (int) queue.pop());
    assertEquals(3, (int) queue.pop());
    assertEquals(4, (int) queue.pop());
  }

  @Test
  public void peek() {
    FastQueueFromStack<Integer> queue = new FastQueueFromStack<>();
    queue.push(1);
    queue.push(2);
    queue.push(3);
    queue.push(4);
    assertEquals(1, (int) queue.peek());
    assertEquals(1, (int) queue.peek());
  }

  @Test
  public void empty() {
    FastQueueFromStack<Integer> queue = new FastQueueFromStack<>();
    assertTrue(queue.empty());
    queue.push(1);
    assertFalse(queue.empty());
    queue.pop();
    assertTrue(queue.empty());
  }
}