package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for SumOfTwoIntegers class.
 */
public class SumOfTwoIntegersTest {

    private final SumOfTwoIntegers sumOfTwoIntegers = new SumOfTwoIntegers();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(1, 2, 3),
            Arguments.of(-1, 1, 0),
            Arguments.of(15, 27, 42),
            Arguments.of(-5, -7, -12),
            Arguments.of(0, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSumOfTwoIntegers(int a, int b, int expected) {
        assertEquals(expected, sumOfTwoIntegers.getSum(a, b));
    }
}
