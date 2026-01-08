package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for CountingBits class.
 */
public class CountingBitsTest {

    private final CountingBits countingBits = new CountingBits();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(2, new int[]{0, 1, 1}),
            Arguments.of(5, new int[]{0, 1, 1, 2, 1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCountingBits(int n, int[] expected) {
        assertArrayEquals(expected, countingBits.countBits(n));
    }

}
