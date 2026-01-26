package com.kevinkan.easy;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for MinimumAbsoluteDifference class.
 */
public class MinimumAbsoluteDifferenceTest {

    private final MinimumAbsoluteDifference minimumAbsoluteDifference = new MinimumAbsoluteDifference();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {4,2,1,3}, List.of(List.of(1,2), List.of(2,3), List.of(3,4))),
            Arguments.of(new int[] {1,3,6,10,15}, List.of(List.of(1,3))),
            Arguments.of(new int[] {3,8,-10,23,19,-4,-14,27}, List.of(List.of(-14,-10), List.of(19,23), List.of(23,27)))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMinimumAbsoluteDifference(int[] arr, List<List<Integer>> expected) {
        assertEquals(expected, minimumAbsoluteDifference.minimumAbsDifference(arr));
    }

}
