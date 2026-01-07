package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ClimbingStairsTest {
    private final ClimbingStairs climbingStairs = new ClimbingStairs();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(2, 2),
            Arguments.of(3, 3),
            Arguments.of(4, 5),
            Arguments.of(5, 8),
            Arguments.of(10, 89),
            Arguments.of(20, 10946)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testClimbingStairsDP(int n, int expected) {
        assertEquals(expected, climbingStairs.climbStairsDP(n));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testClimbingStairsRecursive(int n, int expected) {
        assertEquals(expected, climbingStairs.climbStairsRecursive(n));
    }
}
