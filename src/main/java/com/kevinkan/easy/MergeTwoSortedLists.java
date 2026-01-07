package com.kevinkan.easy;

import com.kevinkan.utility.ListNode;

/**
* You are given the heads of two sorted linked lists list1 and list2.
* 
* Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
* 
* Return the head of the merged linked list.
* 
* Constraints:
* The number of nodes in both lists is in the range [0, 50].
* -100 <= Node.val <= 100
* Both list1 and list2 are sorted in non-decreasing order.
* 
*/
public class MergeTwoSortedLists {

    /* 
    * Recursive approach 
    * Time Complexity: O(n + m) where n and m are the lengths of the two lists.
    * Space Complexity: O(n + m) for the recursion stack in the worst case.
    * Risk: Stack Overflow for very large lists.
    */
    public ListNode mergeTwoListsRecursion(ListNode list1, ListNode list2) {
        // Base Case
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Compare the Values: the next of the smaller one will be the merge of the larger and the remaining of smaller 
        if (list1.val < list2.val) {
            list1.next = mergeTwoListsRecursion(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursion(list1, list2.next);
            return list2;
        }
    }

    /*
    * Iterative approach
    * Time Complexity: O(n + m) where n and m are the lengths of the two lists.
    * Space Complexity: O(1) as we are only using a few pointers.
    */
    public ListNode mergeTwoListsIteration(ListNode list1, ListNode list2) {
        ListNode current = new ListNode();
        ListNode head = current;
        // Add the smaller value of the two lists to the next of current tracker, and move the taken list forward.
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            // Don't frget to move the current marker forward too, so that on the next iteration, we can update next next.
            current = current.next;
        }
        
        // Whichever list is not empty will be appended to the end of the list. 
        current.next = (list1 != null)? list1 : list2;

        // Return the next of the saved empty head.
        return head.next;
    }
}
