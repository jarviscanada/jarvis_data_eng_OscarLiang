package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MiddleNodeTest {

  @Test
  public void middleNode() {
    MiddleNode mn = new MiddleNode();
    int[] array = {1, 2, 3, 4, 5};
    mn.generateList(array);
    assertEquals(3, mn.middleNode(mn.head).val);
    array = new int[]{1, 2, 3, 4, 5, 6};
    mn.generateList(array);
    assertEquals(4, mn.middleNode(mn.head).val);
  }
}