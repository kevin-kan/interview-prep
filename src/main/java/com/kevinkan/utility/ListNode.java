package com.kevinkan.utility;

/*
* Utility Class for ListNode when creating singly-linked lists
*/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode createLinkedListFromArray(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ListNode other = (ListNode) obj;
        ListNode currentA = this;
        ListNode currentB = other;
        while (currentA != null && currentB != null) {
            if (currentA.val != currentB.val) return false;
            currentA = currentA.next;
            currentB = currentB.next;
        }
        return currentA == null && currentB == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        java.util.Map<ListNode, Integer> indexMap = new java.util.IdentityHashMap<>();
        ListNode cur = this;
        int idx = 0;
        while (cur != null) {
            if (idx > 0) sb.append(" -> ");
            if (indexMap.containsKey(cur)) {
                sb.append("(").append("cycle to index ").append(indexMap.get(cur)).append(")");
                break;
            }
            indexMap.put(cur, idx++);
            sb.append(cur.val);
            cur = cur.next;
        }
        return sb.toString();
    }

    public static ListNode createCycleList(int[] values, int pos) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleEntry = null;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
            if (i == pos) {
                cycleEntry = current;
            }
        }

        if (pos == 0) {
            cycleEntry = head;
        }

        if (cycleEntry != null) {
            current.next = cycleEntry;
        }

        return head;
    }
}
