package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for TwoSum class.
 */
public class TwoSumTest {

    private final TwoSum twoSum = new TwoSum();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {2, 7, 11, 15}, 9, new int[] {0, 1}),
            Arguments.of(new int[] {3, 2, 4}, 6, new int[] {1, 2} ),
            Arguments.of(new int[] {3, 3}, 6, new int[] {0, 1} ),
            Arguments.of(new int[] {1, 2}, 3, new int[] {0, 1} ),
            Arguments.of(new int[] {0, 4, 3, 0}, 0, new int[] {0, 3} )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testTwoSumHashMap(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, twoSum.twoSum_hashMap(nums, target));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testTwoSumBruteForce(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, twoSum.twoSum_bruteForce(nums, target));
    }
}
