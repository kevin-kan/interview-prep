package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.ListNode;
import static com.kevinkan.utility.ListNode.createCycleList;

/**
 * Unit tests for LinkedListCycle class.
 */
public class LinkedListCycleTest {

    private final LinkedListCycle linkedListCycle = new LinkedListCycle();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{3, 2, 0, -4}, 1, true),
            Arguments.of(new int[]{1, 2}, 0, true),
            Arguments.of(new int[]{1}, -1, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLinkedListCycle(int[] values, int pos, boolean expected) {
        ListNode head = createCycleList(values, pos);
        assertEquals(expected, linkedListCycle.hasCycle(head));
    }

}
