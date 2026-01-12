package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
* Notice that the solution set must not contain duplicate triplets.
* 
* Constraints:
* 3 <= nums.length <= 3000
* -10^5 <= nums[i] <= 10^5
*/
public class ThreeSum {

    /**
     * Sorting and Two Pointer approach
     * Time Complexity: O(n^2) where n is the length of the nums array.
     * Space Complexity: O(log n) or O(n) depending on the sorting algorithm used.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // Edge Case of nums.length >= 3 confirmed by Constraints
        List<List<Integer>> result = new ArrayList<>();

        // Start by sorting the array: O(nlogn)
        // This allows us to utilize pointers and move with direction.
        Arrays.sort(nums);
        int numsLen = nums.length, left, right, total;
        for (int i = 0; i < numsLen - 2; i++) {
            // Add a quick check - if the combos have been tested already, skip
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            left = i+1;
            right = numsLen - 1;

            // Hold i stable, and check the total for the combinations
            while (left < right) {
                total = nums[i] + nums[left] + nums[right];
                if (total < 0) {
                    left++;
                }
                else if (total > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // Shift both pointers to avoid duplicates
                    // You could move only 1, but will do the same loop above anyway to move the other pointer anyway
                    int currentLeft = nums[left];
                    int currentRight = nums[right];
                    while (left < right && nums[left] == currentLeft) left++;
                    while (left < right && nums[right] == currentRight) right--;
                }
            }
        }

        return result;
    }
}
