package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StackFromTwoQueuesTest {

  @Test
  public void pushAndPop() {
    StackFromTwoQueues<Integer> stack = new StackFromTwoQueues<>();
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
    StackFromTwoQueues<Integer> stack = new StackFromTwoQueues<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    assertEquals(3, (int) stack.top());
    assertEquals(3, (int) stack.top());
  }

  @Test
  public void empty() {
    StackFromTwoQueues<Integer> stack = new StackFromTwoQueues<>();
    assertTrue(stack.empty());
    stack.push(1);
    assertFalse(stack.empty());
    stack.pop();
    assertTrue(stack.empty());
  }
}