package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MinCostClimbingStairsTest {
    private final MinCostClimbingStairs minCostclimbingStairs = new MinCostClimbingStairs();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{10,15,20}, 15),
            Arguments.of(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}, 6),
            Arguments.of(new int[]{1, 2, 3}, 2),
            Arguments.of(new int[]{1,2,1,2,1,1,1}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMinCostClimbingStairs(int[] cost, int expected) {
        assertEquals(expected, minCostclimbingStairs.minCostClimbingStairs(cost));
    }

}
