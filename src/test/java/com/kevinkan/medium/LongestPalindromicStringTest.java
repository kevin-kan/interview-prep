package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for LongestPalindromicString class.
 */
public class LongestPalindromicStringTest {

    private final LongestPalindromicString longestPalindromicString = new LongestPalindromicString();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("babad", "bab"), // or "aba"
            Arguments.of("cbbd", "bb"),
            Arguments.of("", ""),
            Arguments.of("a", "a"),
            Arguments.of("ac", "a"), // or "c"
            Arguments.of("racecar", "racecar"),
            Arguments.of("forgeeksskeegfor", "geeksskeeg"),
            Arguments.of("abccba", "abccba")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLongestPalindrome(String s, String expected) {
        assertEquals(expected, longestPalindromicString.longestPalindrome(s));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLongestPalindromeManacher(String s, String expected) {
        assertEquals(expected, longestPalindromicString.longestPalindromeManacher(s));
    }
}
