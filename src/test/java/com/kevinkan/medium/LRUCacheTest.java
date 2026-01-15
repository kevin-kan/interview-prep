package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for LRUCache class.
 */
public class LRUCacheTest {

    private final LRUCache lruCache = new LRUCache(2);
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new String[] {"put", "put", "get", "put", "get", "put", "get", "get"},
                            new int[][] {{1,1}, {2,2}, {1}, {3,3}, {2}, {4,4}, {1}, {3}},
                            new Integer[] {null, null, 1, null, -1, null, -1, 3})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLRUCache(String[] operations, int[][] values, Integer[] expected) {
        // Test the operations
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            Integer result = null;
            switch (operation) {
                case "put":
                    lruCache.put(values[i][0], values[i][1]);
                    result = null;
                    break;
                case "get":
                    result = lruCache.get(values[i][0]);
                    break;
            }

            assertEquals(expected[i], result);
        }
    }
}