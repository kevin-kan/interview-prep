package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
* You are given an array nums consisting of positive integers. Starting with score = 0, apply the following algorithm:
* - Choose the smallest unmarked element in nums. If there is a tie, choose the one with the smallest index.
* - Add the value of the chosen element to score.
* - Mark the chosen element and its adjacent elements (if they exist).
* - Repeat until all elements in nums are marked.
* Return the score you get after applying the above algorithm.
*
* Constraints:
* - 1 <= nums.length <= 1000
* - 1 <= nums[i] <= 1000
*/
public class FindScoreOfArrayAfterMarkingAllElements {

    /**
     * Sorting Approach
     * Add the elements of nums into a list along with their indices, and sort the list based on the element values.
     * Then use nums array itself to keep track of marked elements by setting them to -1.
     * Iterate through the sorted list, and for each unmarked element, add its value to score and mark it and its adjacent elements.
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(n) for the auxiliary data structures
     */
    public long findScoreSorting(int[] nums) {
        long score = 0;
        // Convert the nums array to NumWithIndex ArrayList
        List<NumWithIndex> numList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numList.add(new NumWithIndex(nums[i], i));
        }

        // Sort the new numList by its value, then by index.
        numList.sort(Comparator.comparingInt((NumWithIndex num) -> num.value)
                                .thenComparingInt(num -> num.index));
        
        // Iterate through numList, taking one value at a time unless its nums is set to -1. 
        // When taking an element, set it and its neighbors to -1. 
        for (NumWithIndex num : numList) {
            if (nums[num.index] != -1) {
                score += num.value;
                nums[num.index] = -1;
                if (num.index > 0) nums[num.index - 1] = -1;
                if (num.index < nums.length - 1) nums[num.index + 1] = -1;
            }
        }

        return score;
    }

    // Helper class to store the tuple
    public class NumWithIndex {
        int value;
        int index;

        public NumWithIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    /**
     * Local Minima approach:
     * Locate the valleys of the array and add alternating values leading up to it. 
     * Time Complexity: O(n) - Looks like 2 nested loops, but the tracking always moves forward.
     * Space Complexity: O(1)
     */
    public long findScoreLocalMinima(int[] nums) {
        long score = 0;
        int n = nums.length;

        long oddSum, evenSum;
        // Move by 2 to skip the right neighbor of the selected local minimum. 
        for (int i = 0; i < n; i+=2) {
            // Locate the next local minimum (valley) tracking the alternating sums along the way. 
            evenSum = 0;
            oddSum = 0;
            while ((i + 1) < n && nums[i] > nums[i+1]) {
                if (i % 2 == 0) evenSum += nums[i];
                else oddSum += nums[i];
                i++;
            }

            // Add the alternating sums based on the final position of the valley
            score += (i % 2 == 0 ? evenSum : oddSum) + nums[i];
        }

        return score;
    }

}
