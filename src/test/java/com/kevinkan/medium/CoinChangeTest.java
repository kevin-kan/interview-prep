package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for CoinChange class.
 */
public class CoinChangeTest {

    private final CoinChange coinChange = new CoinChange();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {1,2,5}, 11, 3),
            Arguments.of(new int[] {2}, 3, -1),
            Arguments.of(new int[] {1}, 0, 0),
            Arguments.of(new int[] {1}, 1, 1),
            Arguments.of(new int[] {1}, 2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCoinChange(int[] candidates, int target, int expected) {
        int result = coinChange.coinChange(candidates, target);
        assertEquals(expected, result);
    }

}
