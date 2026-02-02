package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for KthLargestElementInAnArray class.
 */
public class KthLargestElementInAnArrayTest {

    private final KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {3, 2, 1, 5, 6, 4}, 2, 5 ),
            Arguments.of(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4 )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testKthLargestElementInAnArray(int[] nums, int k, int expected) {
        assertEquals(expected, kthLargestElementInAnArray.findKthLargest(nums, k));
    }
}