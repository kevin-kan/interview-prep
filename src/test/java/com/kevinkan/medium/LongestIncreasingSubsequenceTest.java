package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for LongestIncreasingSubsequence class.
 */
public class LongestIncreasingSubsequenceTest {

    private final LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{1,2,3}, 3),
            Arguments.of(new int[]{1}, 1),
            Arguments.of(new int[]{5,1,2,4,3}, 3),
            Arguments.of(new int[]{9,1,4,2,3,3,7}, 4),
            Arguments.of(new int[]{0,3,1,3,2,3}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLongestPalindrome(int[] nums, int expected) {
        assertEquals(expected, longestIncreasingSubsequence.lengthOfLIS(nums));
    }
}
