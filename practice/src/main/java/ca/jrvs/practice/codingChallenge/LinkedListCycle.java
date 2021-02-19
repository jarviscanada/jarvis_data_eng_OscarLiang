package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.dataStructures.ListNode;

public class LinkedListCycle {

  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode fastPointer = head;
    ListNode slowPointer = head;
    while (fastPointer.next != null && fastPointer.next.next != null) {
      fastPointer = fastPointer.next.next;
      slowPointer = slowPointer.next;
      if (fastPointer == slowPointer) {
        return true;
      }
    }
    return false;
  }
}
