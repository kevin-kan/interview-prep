package com.kevinkan.medium;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for WordBreak class.
 */
public class WordBreakTest {

    private final WordBreak wordBreak = new WordBreak();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("catsanddog", List.of("cat","cats","and","sand","dog"), true),
            Arguments.of("pineapplepenapple", List.of("apple","pen","applepen","pine","pineapple"), true),
            Arguments.of("catsandog", List.of("cats","dog","sand","and","cat"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testWordBreak(String s, List<String> wordDict, boolean expected) {
        assertEquals(expected, wordBreak.wordBreak(s, wordDict));
    }
}
