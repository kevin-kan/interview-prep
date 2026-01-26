package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for PalindromicSubstrings class.
 */
public class PalindromicSubstringsTest {

    private final PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("abc", 3),
            Arguments.of("aaa", 6),
            Arguments.of("a", 1),
            Arguments.of("abccba", 9),
            Arguments.of("racecar", 10),
            Arguments.of("ababa", 9)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCountSubstrings(String s, int expected) {
        assertEquals(expected, palindromicSubstrings.countSubstrings(s));
    }
}
