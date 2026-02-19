package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for CoinChangeII class.
 */
public class CoinChangeIITest {

    private final CoinChangeII coinChangeII = new CoinChangeII();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {1,2,3}, 4, 4),
            Arguments.of(new int[] {2,4}, 7, 0),
            Arguments.of(new int[] {1,2,5}, 5, 4),
            Arguments.of(new int[] {2}, 3, 0),
            Arguments.of(new int[] {10}, 10, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCoinChange(int[] candidates, int target, int expected) {
        int result = coinChangeII.change(target, candidates);
        assertEquals(expected, result);
    }

}
