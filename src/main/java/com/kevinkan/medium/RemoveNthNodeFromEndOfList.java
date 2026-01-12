package com.kevinkan.medium;

import com.kevinkan.utility.ListNode;

/**
* Given the head of a linked list, remove the nth node from the end of the list and return its head.
* 
* Constraints:
* The number of nodes in the list is sz.
* 1 <= sz <= 30
* 0 <= Node.val <= 100
* 1 <= n <= sz
*/
public class RemoveNthNodeFromEndOfList {

    /**
     * Two Pointer approach
     * Time Complexity: O(sz) where sz is the number of nodes in the linked list.
     * Space Complexity: O(1) since we are using only constant extra space.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Edge Case of sz >= 1 confirmed by Constraints
        ListNode saveHead = new ListNode(0, head);
        ListNode front = head;
        ListNode back = saveHead;
        
        // Move front pointer forward by n nodes
        for (int i = 0; i < n; i++) {
            front = front.next;
        }
        // Move front and back pointers forward until the front reaches the end
        while (front != null) {
            front = front.next;
            back = back.next;
        }
        // Remove the back pointer
        back.next = back.next.next;

        // Return the saved head
        return saveHead.next;
    }

}
