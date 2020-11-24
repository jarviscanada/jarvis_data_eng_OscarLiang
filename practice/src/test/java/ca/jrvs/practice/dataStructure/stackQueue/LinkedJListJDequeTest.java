package ca.jrvs.practice.dataStructure.stackQueue;

import org.junit.Assert;
import org.junit.Test;

public class LinkedJListJDequeTest {

  @Test
  public void add() {
    JQueue<String> jQueue = new LinkedJListJDeque<>();
    jQueue.add("Hello");
    jQueue.add("Goodbye");
    Assert.assertEquals("Hello", jQueue.peek());
  }

  @Test
  public void remove() {
    JQueue<String> jQueue = new LinkedJListJDeque<>();
    jQueue.add("Hello");
    jQueue.add("Goodbye");
    Assert.assertEquals("Hello", jQueue.remove());
  }

  @Test
  public void pop() {
    JStack<String> jStack = new LinkedJListJDeque<>();
    jStack.push("Hello");
    jStack.push("Goodbye");
    Assert.assertEquals("Goodbye", jStack.pop());
  }

  @Test
  public void push() {
    JStack<String> jStack = new LinkedJListJDeque();
    jStack.push("Hello");
    jStack.push("Goodbye");
    Assert.assertEquals("Goodbye", jStack.peek());
  }

  @Test
  public void peek() {
    JStack<String> jStack = new LinkedJListJDeque();
    jStack.push("Hello");
    jStack.push("Goodbye");
    Assert.assertEquals("Goodbye", jStack.peek());
    JQueue<String> jQueue = new LinkedJListJDeque();
    jQueue.add("Hello");
    jQueue.add("Goodbye");
    Assert.assertEquals("Hello", jQueue.peek());
  }
}