package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.dataStructures.ListNode;

/**
 * ticket: https://www.notion.so/Middle-of-the-Linked-List-b3e85f4b8b6349a2912384fa18df19e3
 */
public class MiddleNode {
  
  public ListNode head;

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
