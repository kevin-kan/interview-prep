package com.kevinkan.medium;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for InvalidTransactions class.
 */
public class InvalidTransactionsTest {

    private final InvalidTransactions invalidTransactions = new InvalidTransactions();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new String[] {"alice,20,800,mtv","alice,50,100,beijing"}, 
                         List.of("alice,20,800,mtv","alice,50,100,beijing")),
            Arguments.of(new String[] {"alice,20,800,mtv","alice,50,1200,mtv"}, 
                         List.of("alice,50,1200,mtv")),
            Arguments.of(new String[] {"alice,20,800,mtv","bob,50,1200,mtv"}, 
                         List.of("bob,50,1200,mtv"))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testInvalidTransactions(String[] operations, List<String> expected) {
        List<String> result = invalidTransactions.invalidTransactions(operations);
        assertEquals(expected, result);
    }
}