package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueueFromStackTest {

  @Test
  public void pushAndPop() {
    QueueFromStack<Integer> queue = new QueueFromStack<>();
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
    QueueFromStack<Integer> queue = new QueueFromStack<>();
    queue.push(1);
    queue.push(2);
    queue.push(3);
    queue.push(4);
    assertEquals(1, (int) queue.peek());
    assertEquals(1, (int) queue.peek());
  }

  @Test
  public void empty() {
    QueueFromStack<Integer> queue = new QueueFromStack<>();
    assertTrue(queue.empty());
    queue.push(1);
    assertFalse(queue.empty());
    queue.pop();
    assertTrue(queue.empty());
  }
}