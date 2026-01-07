package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ValidPalindrome class.
 */
public class ValidPalindromeTest {

    private final ValidPalindrome validPalindrome = new ValidPalindrome();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("A man, a plan, a canal: Panama", true),
            Arguments.of("race a car", false),
            Arguments.of(" ", true)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testIsPalindrome(String s, boolean expected) {
        assertEquals(expected, validPalindrome.isPalindrome(s));
    }

}
