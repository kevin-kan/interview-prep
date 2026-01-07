package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.ListNode;

/**
 * Unit tests for MergeTwoSortedLists class.
 */
public class MergeTwoSortedListsTest {

    private final MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(ListNode.createLinkedListFromArray(new int[]{1, 2, 4}), ListNode.createLinkedListFromArray(new int[]{1, 3, 4}), ListNode.createLinkedListFromArray(new int[]{1, 1, 2, 3, 4, 4})),
            Arguments.of(ListNode.createLinkedListFromArray(new int[]{}), ListNode.createLinkedListFromArray(new int[]{}), ListNode.createLinkedListFromArray(new int[]{})),
            Arguments.of(ListNode.createLinkedListFromArray(new int[]{}), ListNode.createLinkedListFromArray(new int[]{0}), ListNode.createLinkedListFromArray(new int[]{0}))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMergeTwoSortedListsRecursion(ListNode list1, ListNode list2, ListNode expected) {
        assertEquals(expected, mergeTwoSortedLists.mergeTwoListsRecursion(list1, list2));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMergeTwoSortedListsIteration(ListNode list1, ListNode list2, ListNode expected) {
        assertEquals(expected, mergeTwoSortedLists.mergeTwoListsIteration(list1, list2));
    }
}
