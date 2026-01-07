package com.kevinkan.utility;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for ListNode utility class.
 */
public class ListNodeTest {
    
    @Test
    public void testLinkedListCreationFromArray() {
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = ListNode.createLinkedListFromArray(values);
        ListNode current = head;
        for (int value : values) {
            assert current.val == value : "Expected " + value + " but got " + current.val;
            current = current.next;
        }
        assertNull(current, "Expected end of list but found more nodes.");
    }

    @Test
    public void testLinkedListCycleCreation() {
        int[] values = {3, 2, 0, -4};
        int pos = 1; // cycle should start at index 1 (zero-based)
        ListNode head = ListNode.createCycleList(values, pos);
        assertNotNull(head);

        // Detect cycle using Floyd's algorithm
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { hasCycle = true; break; }
        }
        assertTrue(hasCycle, "Expected a cycle but none was detected.");

        // Find cycle entry
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Compute index of cycle entry
        ListNode cur = head;
        int idx = 0;
        while (cur != slow) {
            idx++;
            cur = cur.next;
        }
        assertEquals(pos, idx, "Cycle entry index does not match expected position.");
    }
    
    @Test
    public void testToString() {
        ListNode head = ListNode.createLinkedListFromArray(new int[]{1, 2, 3});
        String expected = "1 -> 2 -> 3";
        assertEquals(expected, head.toString(), "Expected " + expected + " but got " + head.toString());
    }

    @Test
    public void testToStringWithCycle() {
        ListNode head = ListNode.createCycleList(new int[]{1, 2, 3}, 1);
        String expected = "1 -> 2 -> 3 -> (cycle to index 1)";
        assertEquals(expected, head.toString(), "Expected " + expected + " but got " + head.toString());
    }

}
