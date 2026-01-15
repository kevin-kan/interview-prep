package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for RemoveAllAdjacentDuplicatesInString2 class.
 */
public class RemoveAllAdjacentDuplicatesInString2Test {

    private final RemoveAllAdjacentDuplicatesInString2 removeAllAdjacentDuplicatesInString2 = new RemoveAllAdjacentDuplicatesInString2();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("abcd", 2, "abcd"),
            Arguments.of("deeedbbcccbdaa", 3, "aa"),
            Arguments.of("pbbcggttciiippooaais", 2, "ps"),
            Arguments.of("aaabbbcccddd", 3, ""),
            Arguments.of("", 3, "")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testRemoveAllAdjacentDuplicatesInString2(String s, int k, String expected) {
        assertEquals(expected, removeAllAdjacentDuplicatesInString2.removeDuplicates(s, k));
    }
}
