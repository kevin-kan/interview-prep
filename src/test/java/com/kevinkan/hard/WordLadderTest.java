package com.kevinkan.hard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for WordLadder class.
 */
public class WordLadderTest {

    private WordLadder wordLadder= new WordLadder();;
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"), 5),
            Arguments.of("hit", "cog", Arrays.asList("hot","dot","dog","lot","log"), 0),
            Arguments.of("a", "c", Arrays.asList("a","b","c"), 2),
            Arguments.of("game", "thee", Arrays.asList("fame", "fate", "date", "data", "dare", "care", "core", "cure", "cure", "cute", "thee"), 0),
            Arguments.of("talk", "tail", Arrays.asList("talk", "tons", "fall", "tail", "gale", "hall", "neil"), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testWordLadder(String beginWord, String endWord, List<String> wordList, int expected) {
        int result = wordLadder.ladderLength(beginWord, endWord, wordList);
        assertEquals(expected, result);
    }

}
