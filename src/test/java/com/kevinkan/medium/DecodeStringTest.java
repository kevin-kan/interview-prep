package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for DecodeString class.
 */
public class DecodeStringTest {

    private final DecodeString decodeString = new DecodeString();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("3[a]2[bc]", "aaabcbc"),
            Arguments.of("3[a2[c]]", "accaccacc"),
            Arguments.of("2[abc]3[cd]ef", "abcabccdcdcdef"),
            Arguments.of("abc3[cd]xyz", "abccdcdcdxyz")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testDecodeString(String s, String expected) {
        assertEquals(expected, decodeString.decodeString(s));
    }
}
