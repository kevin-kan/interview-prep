package com.kevinkan.medium;

import java.util.Arrays;

/**
* Given a string s, return the longest palindromic substring in s.
*
* Constraints:
* 1 <= s.length <= 1000
* s consist of only digits and English letters.
*/
public class LongestIncreasingSubsequence {
    
    /**
     * Dynamic Programming Approach
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxSubsequence = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    maxSubsequence = Math.max(dp[i], maxSubsequence);
                }
            }
        }

        return maxSubsequence;
    }
}
