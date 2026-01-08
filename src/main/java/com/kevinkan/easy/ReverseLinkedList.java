package com.kevinkan.easy;

import com.kevinkan.utility.ListNode;

/**
* Given the head of a singly linked list, reverse the list, and return the reversed list.
* 
* Constraints:
* The number of nodes in the list is the range [0, 5000].
* -5000 <= Node.val <= 5000
* 
*/
public class ReverseLinkedList {
    /*
    * Iterative Approach
    * Time Complexity: O(n) where n is the number of nodes in the linked list.
    * Space Complexity: O(1) as we are only using constant space.
    */
    public ListNode reverseList(ListNode head) {
        // Base Case
        if (head == null || head.next == null) return head;
        
        ListNode reversedHead = null;
        ListNode saveRemainder = null;

        while (head != null) {
            saveRemainder = head.next;
            head.next = reversedHead;
            reversedHead = head;
            head = saveRemainder;
        }
        
        return reversedHead;
    }

    /*
    * Recursive Approach
    * Time Complexity: O(n) where n is the number of nodes in the linked list.
    * Space Complexity: O(n) due to the recursion stack.
    * Risk: Stack Overflow
    */
    public ListNode reverseListRecursive(ListNode head) {
        return reverseListRecursiveHelper(head, null);
    }

    private ListNode reverseListRecursiveHelper(ListNode head, ListNode reversedHead) {
        // Base Case - if the head is empty, then there's nothing left to reverse. Return what has been reversed.
        if (head == null) return reversedHead;

        // Save off head.next because we're about to reset it. (ie. (3 -> 4 -> 5))
        ListNode temp = head.next;
        // Point head.next to what has already been reversed (ie. 2 -> (1 -> null))
        head.next = reversedHead;
        // Run it again, reversing the remaining items from original list, and passing the reversed List. 
        return reverseListRecursiveHelper(temp, head);
    }

}
