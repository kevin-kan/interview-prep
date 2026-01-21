package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for CombinationSum class.
 */
public class CombinationSumTest {

    private final CombinationSum combinationSum = new CombinationSum();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {2,3,6,7}, 7, List.of(
                List.of(7),
                List.of(2,2,3)
            )),
            Arguments.of(new int[] {2,3,5}, 8, List.of(
                List.of(2,2,2,2),
                List.of(2,3,3),
                List.of(3,5)
            )),
            Arguments.of(new int[] {2}, 1, List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCombinationSum(int[] candidates, int target, List<List<Integer>> expected) {
        List<List<Integer>> result = combinationSum.combinationSum(candidates, target);
        List<List<Integer>> sortedResult = sortSubsets(result);
        List<List<Integer>> sortedExpected = sortSubsets(expected);

        assertEquals(sortedExpected, sortedResult);
    }

    /**
     * Subets can be in any order, so sort them for comparison
     */
    private List<List<Integer>> sortSubsets(List<List<Integer>> subsets) {
        Comparator<List<Integer>> SUBSET_COMPARATOR = Comparator.comparingInt((List<Integer> l) -> l.size())
        .thenComparing((l1, l2) -> {
            int min = Math.min(l1.size(), l2.size());
            for (int i = 0; i < min; i++) {
                int cmp = Integer.compare(l1.get(i), l2.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });

        return subsets.stream()
            .map(list -> new ArrayList<>(list))
            .peek(list -> list.sort(Comparator.naturalOrder()))
            .sorted(SUBSET_COMPARATOR)
            .collect(Collectors.toList());
    }
}
