package com.kevinkan.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for WordBreakII class.
 */
public class WordBreakIITest {

    private final WordBreakII wordBreakII = new WordBreakII();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("catsanddog", List.of("cat","cats","and","sand","dog"), List.of("cats and dog","cat sand dog")),
            Arguments.of("pineapplepenapple", List.of("apple","pen","applepen","pine","pineapple"), List.of("pine apple pen apple","pineapple pen apple","pine applepen apple")),
            Arguments.of("catsandog", List.of("cats","dog","sand","and","cat"), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testWordBreak(String s, List<String> wordDict, List<String> expected) {
        List<String> actual = new ArrayList<>(wordBreakII.wordBreak(s, wordDict));
        List<String> expectedCopy = new ArrayList<>(expected);
        Collections.sort(actual);
        Collections.sort(expectedCopy);

        assertEquals(expectedCopy, actual);
    }
}
