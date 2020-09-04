package ca.jrvs.practice.codingChallenge;

public class MiddleNode {

  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  /**
   * Time complexity: O(n)
   * Traversal through linked list once.
   */
  public ListNode middleNode(ListNode head) {
    int nodesTraversed = 1;
    ListNode end = head;
    ListNode middle = head;
    while (end != null) {
      if (nodesTraversed % 2 == 0) {
        middle = middle.next;
      }
      nodesTraversed++;
      end = end.next;
    }
    return middle;
  }
}
