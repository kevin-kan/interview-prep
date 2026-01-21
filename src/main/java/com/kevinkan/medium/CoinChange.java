package com.kevinkan.medium;

import java.util.Arrays;


/**
* You are given an integer array candidates representing coins of different denominations and an integer target representing a total amount of money.
* Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
* You may assume that you have an infinite number of each kind of coin.
* 
* Constraints:
* 1 <= coins.length <= 12
* 1 <= coins[i] <= 2^31 - 1
* 0 <= amount <= 10^4
*/
public class CoinChange {

    /**
     * Dynamic Programming approach
     * Time Complexity: O(n * m) where n is the amount and m is the number of coin denominations.
     * Space Complexity: O(n) where n is the amount.
     */
    public int coinChange(int[] coins, int amount) {
        int amount1 = amount + 1;
        // Create a Dynamic Programming memory array of amount + 1, starting at an unreasonably high coin count
        // We will find the best coin combination for each amount leading to that final amount.
        int[] fewestCoins = new int[amount1];
        Arrays.fill(fewestCoins, amount1);

        // Base Case: $0 -> 0 coins
        fewestCoins[0] = 0;

        for (int i = 1; i < amount1; i ++) {
            for (int j = 0; j < coins.length; j++) {
                // Only check the coin if it's smaller than or equal to the amount we are checking
                if (coins[j] <= i) {
                    // Update our DP array with the best between current and the best at the difference + this coin (count 1)
                    // If no coins can be used to make up the amount, then the amount will stay at amount + 1
                    fewestCoins[i] = Math.min(fewestCoins[i], 1 + fewestCoins[i - coins[j]]);
                } 
            }
        }
        return fewestCoins[amount] == amount1 ? -1 : fewestCoins[amount];
    }

}
