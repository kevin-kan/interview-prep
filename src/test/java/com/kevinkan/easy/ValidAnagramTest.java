package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ValidAnagram class.
 */
public class ValidAnagramTest {

    private final ValidAnagram validAnagram = new ValidAnagram();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("anagram", "nagaram", true),
            Arguments.of("rat", "car", false),
            Arguments.of("a", "a", true),
            Arguments.of("aaabbb", "aaaaaa", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testIsAnagramSorting(String s, String t, boolean expected) {
        assertEquals(expected, validAnagram.isAnagramSorting(s, t));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testIsAnagramHashMap(String s, String t, boolean expected) {
        assertEquals(expected, validAnagram.isAnagramHashMap(s, t));
    }

}
