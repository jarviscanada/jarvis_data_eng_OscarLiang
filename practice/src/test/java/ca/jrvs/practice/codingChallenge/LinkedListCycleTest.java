package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.practice.codingChallenge.dataStructures.ListNode;
import org.junit.Test;

public class LinkedListCycleTest {

  LinkedListCycle linkedListCycle = new LinkedListCycle();
  ListNode head;

  @Test
  public void hasCycle() {
    assertFalse(linkedListCycle.hasCycle(null));

    head = ListNode.generateList(new int[]{0, 1, 2, 3, 4, 5});
    addCycleToList(head, 3);
    assertTrue(linkedListCycle.hasCycle(head));

    head = ListNode.generateList(new int[]{1, 1, 1, 1, 1, 1});
    assertFalse(linkedListCycle.hasCycle(head));

    addCycleToList(head, 0);
    assertTrue(linkedListCycle.hasCycle(head));
  }

  private void addCycleToList(ListNode head, int pos) {
    ListNode cyclePos = head;
    ListNode lastNode = head;
    for (int i = 0; i < pos; i++) {
      cyclePos = cyclePos.next;
    }

    while (lastNode.next != null) {
      lastNode = lastNode.next;
    }

    lastNode.next = cyclePos;
  }
}