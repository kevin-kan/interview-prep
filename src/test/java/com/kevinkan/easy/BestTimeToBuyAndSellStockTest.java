package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for BestTimeToBuyAndSellStock class.
 */
public class BestTimeToBuyAndSellStockTest {

    private final BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {7,1,5,3,6,4}, 5),
            Arguments.of(new int[] {7,6,4,3,1}, 0),
            Arguments.of(new int[] {1,2,3,4,5}, 4),
            Arguments.of(new int[] {3,2,6,5,0,3}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMaxProfit(int[] prices, int expected) {
        assertEquals(expected, bestTimeToBuyAndSellStock.maxProfit(prices));
    }

}
