package ca.jrvs.practice.codingChallenge.dataStructures;

/**
 * Simple linked list node to be used for coding challenges
 */
public class ListNode {

  public int val;
  public ListNode next;

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
    this.next = null;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public static ListNode generateList(int[] array) {
    ListNode head = new ListNode(array[0]);
    ListNode next = head;
    for (int i = 1; i < array.length; i++) {
      next.next = new ListNode(array[i]);
      next = next.next;
    }
    return head;
  }
}
