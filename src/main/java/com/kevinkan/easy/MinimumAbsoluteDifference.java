package com.kevinkan.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
* Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
* a, b are from arr, a < b and b - a equals to the minimum absolute difference of any two elements in arr.
*
* Constraints:
* 2 <= arr.length <= 10^5
* -10^6 <= arr[i] <= 10^6
*/
public class MinimumAbsoluteDifference {
    /**
     * Sorting approach
     * Time Complexity: O(n log n) due to the sorting step.
     * Space Complexity: O(1) if we ignore the space required for the output list 
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        // Start by sorting the array 
        Arrays.sort(arr);
        int minAbsDiff = Integer.MAX_VALUE;
        int currentDiff;

        // Iterate through the array and keep track of the minimum absolute difference
        for (int i = 0; i < arr.length - 1; i++) {
            currentDiff = arr[i+1] - arr[i];
            // If we find a new minimum absolute difference, clear the result set
            if (currentDiff < minAbsDiff) {
                minAbsDiff = currentDiff;
                result.clear();
            }
            // Add the value to the result set if it matches the minimum absolute difference
            if (currentDiff == minAbsDiff) {
                result.add(List.of(arr[i], arr[i+1]));
            }
        }

        return result;
    }
}
