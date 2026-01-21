package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.List;

/**
* Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
* The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
* The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
* 
* Constraints:
* 1 <= candidates.length <= 30
* 2 <= candidates[i] <= 40
* All elements of candidates are distinct.
* 1 <= target <= 40
*/
public class CombinationSum {

    /**
     * Backtracking approach
     * Time Complexity: O(N^(T/M + 1)) where N is the number of candidates, T is the target, and M is the minimum value in candidates.
     * Space Complexity: O(T/M) where T is the target, and M is the minimum value in candidates.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Set up a result list and current combination list to track progress
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    public void backtrack(int[] candidates, int target, int index, List<Integer> currentCombo, List<List<Integer>> result) {
        // If we've hit the target, add the current combination to the result
        if (target == 0) {
            result.add(new ArrayList<>(currentCombo));
            return;
        }
        // If we've exceeded the target, no need to continue
        else if (target < 0) {
            return;
        }
        // Iterate through candidates starting from the current index
        for (int i = index; i < candidates.length; i++) {
            // If we were skipping duplicates:
            // if (i > index && candidates[i] == candidates[i - 1]) continue;

            // Include the candidate and continue the search with the updated target
            currentCombo.add(candidates[i]);
            
            // If we were skipping duplicates:
            // backtrack(candidates, target - candidates[i], i + 1, currentCombo, result);
            backtrack(candidates, target - candidates[i], i, currentCombo, result); 
            
            // Backtrack: remove the last added candidate in order to try the next candidate
            currentCombo.remove(currentCombo.size() - 1);
        }
    }
}
