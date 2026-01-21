package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for HouseRobber class.
 */
public class HouseRobberTest {

    private final HouseRobber houseRobber = new HouseRobber();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 1}, 4),
            Arguments.of(new int[]{2, 7, 9, 3, 1}, 12),
            Arguments.of(new int[]{2, 1, 4, 9}, 11)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testRob(int[] nums, int expected) {
        assertEquals(expected, houseRobber.rob(nums));
    }
}
