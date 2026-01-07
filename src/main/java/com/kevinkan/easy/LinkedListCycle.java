package com.kevinkan.easy;

import com.kevinkan.utility.ListNode;

/**
* Given head, the head of a linked list, determine if the linked list has a cycle in it.
* There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
* Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
* Return true if there is a cycle in the linked list. Otherwise, return false.
* 
* Constraints:
* The number of the nodes in the list is in the range [0, 10^4].
* -10^5 <= Node.val <= 10^5
* pos is -1 or a valid index in the linked-list.
* 
*/
public class LinkedListCycle {
    /*
    * Floyd's Tortoise and Hare Algorithm
    * Time Complexity: O(n) where n is the number of nodes in the linked list.
    * Space Complexity: O(1) as we are only using two pointers.
    */
    public boolean hasCycle(ListNode head) {
        // Edge Case
        if (head == null) return false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        
        // If the fast.next hits the end, there's no cyle.
        return false;
    }
}
