package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for MergeIntervals class.
 */
public class MergeIntervalsTest {

    private final MergeIntervals mergeIntervals = new MergeIntervals();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[][] {{1,3},{2,6},{8,10},{15,18}}, new int[][] {{1,6},{8,10},{15,18}}),
            Arguments.of(new int[][] {{1,4},{4,5}}, new int[][] {{1,5}}),
            Arguments.of(new int[][] {{1,4},{0,2},{3,5}}, new int[][] {{0,5}}),
            Arguments.of(new int[][] {{1,4}}, new int[][] {{1,4}}),
            Arguments.of(new int[][] {{1,4},{5,6}}, new int[][] {{1,4},{5,6}}),
            Arguments.of(new int[][] {{1,4},{2,3}}, new int[][] {{1,4}})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMergeIntervals(int[][] intervals, int[][] expected) {
        assertArrayEquals(expected, mergeIntervals.merge(intervals));
    }
}
