package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for LongestSubstringWithoutRepeat class.
 */
public class LongestSubstringWithoutRepeatTest {

    private final LongestSubstringWithoutRepeat longestSubstringWithoutRepeat = new LongestSubstringWithoutRepeat();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("abcabcbb", 3),
            Arguments.of("bbbbb", 1),
            Arguments.of("pwwkew", 3),
            Arguments.of("", 0),
            Arguments.of(" ", 1),
            Arguments.of("au", 2),
            Arguments.of("dvdf", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLongestSubstringWithoutRepeat(String s, int expected) {
        assertEquals(expected, longestSubstringWithoutRepeat.lengthOfLongestSubstring(s));
    }
}
