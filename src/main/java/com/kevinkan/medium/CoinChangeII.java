package com.kevinkan.medium;

import java.util.Arrays;


/**
* You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.
* Return the number of distinct combinations that total up to amount. 
* If it's impossible to make up the amount, return 0.
* You may assume that you have an unlimited number of each coin and that each value in coins is unique.
* 
* Constraints:
* 1 <= coins.length <= 100
* 1 <= coins[i] <= 5000
* 0 <= amount <= 5000
*/
public class CoinChangeII {

    /**
     * Dynamic Programming approach
     * Time Complexity: O(n * m) where n is the amount and m is the number of coin denominations.
     * Space Complexity: O(n) where n is the amount.
     */
    public int change(int amount, int[] coins) {
        // Dynamic Programming array (we need amount+1 because we will be returning dp[amount])
        int[] dp = new int[amount +1];
        // Base case: 0 amount can be formed 1 way (0 coins)
        dp[0] = 1;

        // Loop coins on the outside in order to avoid duplicates
        for (int coin: coins) {
            for (int i = coin; i <= amount; i++) {
                // the number of combinations is:
                // number of combinations made with ONLY previous coins +
                // number of combinations you can make (amount - coin), since we will add this coin once. 
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }

}
