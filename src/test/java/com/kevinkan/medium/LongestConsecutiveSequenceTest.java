package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for LongestConsecutiveSequence class.
 */
public class LongestConsecutiveSequenceTest {

    private final LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {100,4,200,1,3,2}, 4),
            Arguments.of(new int[] {0,3,7,2,5,8,4,6,0,1}, 9),
            Arguments.of(new int[] {}, 0),
            Arguments.of(new int[] {1,2,0,1}, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLongestConsecutiveSequence(int[] nums, int expected) {
        assertEquals(expected, longestConsecutiveSequence.longestConsecutive(nums));
    }
}
