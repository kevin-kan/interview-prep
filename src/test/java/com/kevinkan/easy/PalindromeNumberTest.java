package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for PalindromeNumber class.
 */
public class PalindromeNumberTest {

    private final PalindromeNumber palindromeNumber = new PalindromeNumber();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(121, true),
            Arguments.of(-121, false),
            Arguments.of(10, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testIsPalindrome(int x, boolean expected) {
        assertEquals(expected, palindromeNumber.isPalindrome(x));
    }

}
