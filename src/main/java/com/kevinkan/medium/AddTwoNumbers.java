package com.kevinkan.medium;

import com.kevinkan.utility.ListNode;

/**
* You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
* 
* Constraints:
* The number of nodes in each linked list is in the range [1, 100].
* 0 <= Node.val <= 9
* It is guaranteed that the list represents a number that does not have leading zeros.
*/
public class AddTwoNumbers {

    /**
     * Iterative approach
     * Time Complexity: O(max(m, n)) where m and n are the lengths of the two linked lists.
     * Space Complexity: O(max(m, n)) for the resulting linked list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Create node for traversing
        ListNode current = new ListNode(0);
        // Copy the node to bring us back to the start for result
        ListNode result = current;
        
        int val1, val2, sum, carry = 0;
        // Traverse both lists
        while (l1 != null || l2 != null || carry != 0) {
            val1 = (l1 != null) ? l1.val : 0;
            val2 = (l2 != null) ? l2.val : 0;
            sum = val1 + val2 + carry;

            current.next = new ListNode(sum % 10);

            // Setup for next iteration
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            current = current.next;
            carry = sum / 10;
        }

        return result.next;
    }
}
