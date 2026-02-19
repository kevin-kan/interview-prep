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
 * Unit tests for SubsetsII class.
 */
public class SubsetsIITest {

    private final SubsetsII subsetsII = new SubsetsII();

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
            Arguments.of(new int[] {1,2,2}, List.of( // Dedupe 8 to 6
                List.of(),
                List.of(1),
                List.of(1,2),
                List.of(1,2,2),
                // List.of(1,2), - Skipped duplicate
                List.of(2),
                List.of(2,2)
                // List.of(2) - Skipped duplicate
            )),
            Arguments.of(new int[] {1,2,1,2}, List.of( // Dedupe 16 to 9
                List.of(), // []
                List.of(1), // [] + [1'], 
                    List.of(1,1), // [1'] + [1"],
                        List.of(1,1,2), // [1',1"] + [2'],
                            List.of(1,1,2,2), // [1',1",2'] + [2"]
                            // Backtrack - [1',1",2'] no more options than just the second 2
                        // Backtrack - [1',1"] + [2"] -> Skip Duplicate List.of(1,1,2) because 2" == 2' && 3(i) > 2(start)
                    // Backtrack - no more options
                    List.of(1,2), // [1'] + [2']
                        List.of(1,2,2), // [1', 2'] + [2"]
                        // Backtrack - no more options
                    // Backtrack - [1'] + [2"] -> Skip duplicate List.of(1,2) because 2" == 2' && 3(i) > 1(start)
                // [] + [1"] -> Skip duplicates[List.of(1"), List.of(1",2'), List.of(1",2',2"), List.of(1",2")] because 1" == 1' && 1(i) > 0(start)
                List.of(2), // [2']
                    List.of(2,2) // [2'] + [2"]
                    // Backtrack - no more options
                // [2"] -> Skip duplicate List.of(2) because 2" == 2' && 3(i) > 0(start)
            )),
            Arguments.of(new int[] {7,7,7}, List.of( // Dedupe 8 to 4
                List.of(), // []
                List.of(7), // [7']
                    List.of(7,7), // [7'] + [7"]
                        List.of(7,7,7) // [7',7"] + [7"']
                    // [7'] + [7"']
                // [7"]
                    // [7"] + [7"']
                // [7"']

            ))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSubsetsII(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> result = subsetsII.subsetsWithDup(nums);
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
