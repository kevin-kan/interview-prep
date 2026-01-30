package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for IsSubsequence class.
 */
public class IsSubsequenceTest {

    private final IsSubsequence isSubsequence = new IsSubsequence();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("ace", "abcde", true),
            Arguments.of("aec", "abcde", false),
            Arguments.of("", "abcde", true),
            Arguments.of("abc", "", false),
            Arguments.of("", "", true)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testIsSubsequence(String s, String t, boolean expected) {
        assertEquals(expected, isSubsequence.isSubsequence(s, t));
    }

}
