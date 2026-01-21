package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for MinimumLimitOfBallsInABag class.
 */
public class MinimumLimitOfBallsInABagTest {

    private final MinimumLimitOfBallsInABag minimumLimitOfBallsInABag = new MinimumLimitOfBallsInABag();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {9}, 2, 3),
            Arguments.of(new int[] {2,4,8,2}, 4, 2),
            Arguments.of(new int[] {7,17}, 2, 7),
            Arguments.of(new int[] {1,2,3,4,5}, 5, 2),
            Arguments.of(new int[] {1000000000}, 1000000000, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMinimumLimitOfBallsInABag(int[] balls, int maxOperations, int expected) {
        assertEquals(expected, minimumLimitOfBallsInABag.minimumSize(balls, maxOperations));
    }

}
