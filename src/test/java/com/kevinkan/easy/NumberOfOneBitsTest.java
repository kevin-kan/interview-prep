package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for NumberOfOneBits class.
 */
public class NumberOfOneBitsTest {

    private final NumberOfOneBits numberOfOneBits = new NumberOfOneBits();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(11, 3),
            Arguments.of(128, 1),
            Arguments.of(2147483645, 30),
            Arguments.of(0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testHammingWeight(int n, int expected) {
        assertEquals(expected, numberOfOneBits.hammingWeight(n));
    }

}
