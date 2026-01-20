package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for WordSearch class.
 */
public class WordSearchTest {

    private final WordSearch wordSearch = new WordSearch();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new char[][] { {'A','B','C','E'}, 
                                        {'S','F','C','S'}, 
                                        {'A','D','E','E'}}, "ABCCED", true),
            Arguments.of(new char[][] { {'A','B','C','E'}, 
                                        {'S','F','C','S'}, 
                                        {'A','D','E','E'}}, "SEE", true),
            Arguments.of(new char[][] { {'A','B','C','E'}, 
                                        {'S','F','C','S'}, 
                                        {'A','D','E','E'}}, "ABCB", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testWordSearch(char[][] board, String word, boolean expected) {
        assertEquals(expected, wordSearch.exist(board, word));
    }
}
