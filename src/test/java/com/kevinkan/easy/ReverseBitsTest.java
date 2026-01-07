package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ReverseBits class.
 */
public class ReverseBitsTest {

    private final ReverseBits reverseBits = new ReverseBits();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(43261596, 964176192),
            Arguments.of(2147483644, 1073741822)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testReverseBits(int n, int expected) {
        assertEquals(expected, reverseBits.reverseBits(n));
    }

}
