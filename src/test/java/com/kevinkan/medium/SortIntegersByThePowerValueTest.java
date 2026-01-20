package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for SortIntegersByThePowerValue class.
 */
public class SortIntegersByThePowerValueTest {

    private final SortIntegersByThePowerValue sortIntegersByThePowerValue = new SortIntegersByThePowerValue();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(12, 15, 2, 13),
            Arguments.of(1, 1, 1, 1),
            Arguments.of(7, 11, 4, 7),
            Arguments.of(10, 20, 5, 13),
            Arguments.of(1, 1000, 777, 570)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testGetKth(int lo, int hi, int k, int expected) {
        assertEquals(expected, sortIntegersByThePowerValue.getKth(lo, hi, k));
    }
}
