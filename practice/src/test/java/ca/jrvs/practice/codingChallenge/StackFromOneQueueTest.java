package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class StackFromOneQueueTest {

  @Test
  public void pushAndPop() {
    StackFromOneQueue<Integer> stack = new StackFromOneQueue<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    assertEquals(4, (int) stack.pop());
    assertEquals(3, (int) stack.pop());
    assertEquals(2, (int) stack.pop());
    assertEquals(1, (int) stack.pop());
  }

  @Test
  public void top() {
    StackFromOneQueue<Integer> stack = new StackFromOneQueue<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    assertEquals(3, (int) stack.top());
    assertEquals(3, (int) stack.top());
  }

  @Test
  public void empty() {
    StackFromOneQueue<Integer> stack = new StackFromOneQueue<>();
    assertTrue(stack.empty());
    stack.push(1);
    assertFalse(stack.empty());
    stack.pop();
    assertTrue(stack.empty());
  }
}