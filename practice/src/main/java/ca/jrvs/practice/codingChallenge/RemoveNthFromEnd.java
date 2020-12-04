package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.dataStructures.ListNode;

/**
 * ticket: https://www.notion.so/Nth-Node-From-End-of-LinkedList-011bb8c9c54947529257403de663cc37
 */
public class RemoveNthFromEnd {

  /**
   * Time complexity: O(n) - iterate through the entire linked list once.
   * Space complexity: O(1) - two ListNode pointers
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode end = head;
    ListNode remove = head;
    for (int i = 0; i < n; i++) {
      end = end.next;
    }
    // Corner case where the node to remove turns out to be the head
    if (end == null) {
      return head.next;
    }

    // After this while loop, end will be at the final node and remove will be at the node whose
    // next is the node to remove.
    while (end.next != null) {
      end = end.next;
      remove = remove.next;
    }

    remove.next = remove.next.next;
    return head;
  }
}
