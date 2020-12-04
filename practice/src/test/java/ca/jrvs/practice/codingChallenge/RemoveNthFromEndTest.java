package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.dataStructures.ListNode;
import org.junit.Test;

public class RemoveNthFromEndTest {

  RemoveNthFromEnd remover = new RemoveNthFromEnd();

  @Test
  public void removeNthFromEnd() {
    ListNode head = ListNode.generateList(new int[] {1, 2, 3, 4, 5, 6, 7, 8});
    head = remover.removeNthFromEnd(head, 3);
    ListNode target = head;
    for (int i = 0; i < 5; i++) {
      assertEquals(i + 1, target.val);
      target = target.next;
    }
    assertEquals(7, target.val);
    head = remover.removeNthFromEnd(head, 7);
    assertEquals(2, head.val);
  }
}