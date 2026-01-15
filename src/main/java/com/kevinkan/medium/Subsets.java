package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.List;

/**
* Given and integer array nums of unique elements, return all possible subsets (the power set).
* The solution set must not contain duplicate subsets. Return the solution in any order.
* 
* Constraints:
* 1 <= nums.length <= 10
* -10 <= nums[i] <= 10
* All the numbers of nums are unique.
*/
public class Subsets {

    /**
     * Backtracking approach
     * Time Complexity: O(n * 2^n) where n is the length of the nums array.
     * Space Complexity: O(2^n) for the recursion stack.  
     */
    public List<List<Integer>> subsetsRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();

        backtrack(nums, 0, subset, result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> subset, List<List<Integer>> result) {
        // Base case
        if (index == nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        // Case 1: include new number
        subset.add(nums[index]);
        backtrack(nums, index + 1, subset, result);

        // Case 2: do not include new number
        subset.remove(subset.size() - 1);
        backtrack(nums, index + 1, subset, result);

    }

    /**
     * Iterative approach
     * Time Complexity: O(n * 2^n) where n is the length of the nums array.
     * Space Complexity: O(2^n) for the resulting list of subsets.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<>()); // Start with the empty subset

        // For each element, take the current set in the result list, and add a copy of it including the current element
        for (int num: nums) {
            int currentResultSize = results.size();
            for (int i = 0; i < currentResultSize; i++) {
                List<Integer> subset = new ArrayList<>(results.get(i));
                subset.add(num);
                results.add(subset);
            }
        }

        return results;
    }

}