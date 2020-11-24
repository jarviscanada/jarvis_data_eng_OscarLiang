package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Middle-of-the-Linked-List-b3e85f4b8b6349a2912384fa18df19e3
 */
public class MiddleNode {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
      this.next = null;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode head;

  public void generateList(int[] array) {
    head = new ListNode(array[0]);
    ListNode next = head;
    for (int i = 1; i < array.length; i++) {
      next.next = new ListNode(array[i]);
      next = next.next;
    }
  }

  /**
   * Time complexity: O(n) Traversal through linked list once.
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
