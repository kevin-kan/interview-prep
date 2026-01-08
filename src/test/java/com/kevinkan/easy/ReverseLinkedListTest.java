package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.ListNode;

/**
 * Unit tests for ReverseLinkedList class.
 */
public class ReverseLinkedListTest {

    private final ReverseLinkedList reverseLinkedList = new ReverseLinkedList();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}),
            Arguments.of(new int[]{1, 2}, new int[]{2, 1}),
            Arguments.of(new int[]{}, new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testReverseLinkedList(int[] values, int[] expectedValues) {
        ListNode head = ListNode.createLinkedListFromArray(values);
        ListNode reversedHead = reverseLinkedList.reverseList(head);
        assertEquals(ListNode.createLinkedListFromArray(expectedValues), reversedHead);
    }

        @ParameterizedTest
    @MethodSource("testData")
    public void testReverseLinkedListRecursive(int[] values, int[] expectedValues) {
        ListNode head = ListNode.createLinkedListFromArray(values);
        ListNode reversedHead = reverseLinkedList.reverseListRecursive(head);
        assertEquals(ListNode.createLinkedListFromArray(expectedValues), reversedHead);
    }

}
