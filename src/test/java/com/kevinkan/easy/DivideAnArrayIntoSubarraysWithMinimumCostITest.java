package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for DivideAnArrayIntoSubarraysWithMinimumCostI class.
 */
public class DivideAnArrayIntoSubarraysWithMinimumCostITest {

    private final DivideAnArrayIntoSubarraysWithMinimumCostI divideAnArrayIntoSubarraysWithMinimumCostI = new DivideAnArrayIntoSubarraysWithMinimumCostI();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 12}, 6),
            Arguments.of(new int[]{5, 4, 3}, 12),
            Arguments.of(new int[]{10, 3, 1, 1}, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMinimumCostHeapApproach(int[] nums, int expected) {
        assertEquals(expected, divideAnArrayIntoSubarraysWithMinimumCostI.minimumCostHeapApproach(nums));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMinimumCostArrayApproach(int[] nums, int expected) {
        assertEquals(expected, divideAnArrayIntoSubarraysWithMinimumCostI.minimumCostArrayApproach(nums));
    }


}
