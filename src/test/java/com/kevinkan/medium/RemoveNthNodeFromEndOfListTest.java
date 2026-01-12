package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.ListNode;

/**
 * Unit tests for RemoveNthNodeFromEndOfList class.
 */
public class RemoveNthNodeFromEndOfListTest {

    private final RemoveNthNodeFromEndOfList removeNthNodeFromEndOfList = new RemoveNthNodeFromEndOfList();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {1,2,3,4,5}, 2, new int[] {1,2,3,5}),
            Arguments.of(new int[] {1}, 1, new int[] {}),
            Arguments.of(new int[] {1,2}, 1, new int[] {1}),
            Arguments.of(new int[] {1,2}, 2, new int[] {2}),
            Arguments.of(new int[] {1,2,3}, 3, new int[] {2,3})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testRemoveNthNodeFromEndOfList(int[] values, int n, int[] expectedValues) {
        assertEquals(ListNode.createLinkedListFromArray(expectedValues), 
            removeNthNodeFromEndOfList.removeNthFromEnd(ListNode.createLinkedListFromArray(values), n));
    }

}
