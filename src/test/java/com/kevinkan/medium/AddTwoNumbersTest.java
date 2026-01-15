package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.ListNode;

/**
 * Unit tests for AddTwoNumbers class.
 */
public class AddTwoNumbersTest {

    private final AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {2,4,3}, new int[] {5,6,4}, new int[] {7,0,8}),
            Arguments.of(new int[] {0}, new int[] {0}, new int[] {0}),
            Arguments.of(new int[] {9,9,9,9,9,9,9}, new int[] {9,9,9,9}, new int[] {8,9,9,9,0,0,0,1}),
            Arguments.of(new int[] {1,8}, new int[] {0}, new int[] {1,8}),
            Arguments.of(new int[] {5}, new int[] {5}, new int[] {0,1})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testAddTwoNumbers(int[] values1, int[] values2, int[] expectedValues) {
        assertEquals(ListNode.createLinkedListFromArray(expectedValues), 
            addTwoNumbers.addTwoNumbers(ListNode.createLinkedListFromArray(values1), ListNode.createLinkedListFromArray(values2)));
    }

}
