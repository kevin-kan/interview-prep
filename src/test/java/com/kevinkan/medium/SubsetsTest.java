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
 * Unit tests for Subsets class.
 */
public class SubsetsTest {

    private final Subsets subsets = new Subsets();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {1,2,3}, List.of(
                List.of(), 
                List.of(1), 
                List.of(2), 
                List.of(3), 
                List.of(1,2), 
                List.of(1,3), 
                List.of(2,3), 
                List.of(1,2,3)
            )),
            Arguments.of(new int[] {0}, List.of(
                List.of(),
                List.of(0)
            )),
            Arguments.of(new int[] {1,2}, List.of(
                List.of(),
                List.of(1),
                List.of(2),
                List.of(1,2)
            ))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSubsets(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> result = subsets.subsets(nums);
        List<List<Integer>> sortedResult = sortSubsets(result);
        List<List<Integer>> sortedExpected = sortSubsets(expected);

        assertEquals(sortedExpected, sortedResult);
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSubsetsRecursive(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> result = subsets.subsetsRecursive(nums);
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
