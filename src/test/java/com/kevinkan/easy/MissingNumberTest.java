package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for MissingNumber class.
 */
public class MissingNumberTest {

    private final MissingNumber missingNumber = new MissingNumber();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{3, 0, 1}, 2),
            Arguments.of(new int[]{0, 1}, 2),
            Arguments.of(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}, 8)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMissingNumber(int[] nums, int expected) {
        assertEquals(expected, missingNumber.missingNumber(nums));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMissingNumberXOR(int[] nums, int expected) {
        assertEquals(expected, missingNumber.missingNumberXOR(nums));
    }
}
