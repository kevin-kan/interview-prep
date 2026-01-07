package com.kevinkan.easy;

/**
* You are given an array prices where prices[i] is the price of a given stock on the ith day.
* You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
* Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
*
* Constraints:
* 1 <= prices.length <= 10^5
* 0 <= prices[i] <= 10^4
*/
public class BestTimeToBuyAndSellStock {

    /*
    * Kadane's Algorithm Approach
    * Time Complexity: O(N) where N is the number of days (length of prices array).
    * We traverse the prices array once to find the minimum price and calculate the maximum profit.
    * Space Complexity: O(1) as we are using only a constant amount of extra space.
    */
    public int maxProfit(int[] prices) {
        // Lay out 2 variables to track the minimum price we can buy at and the maximum profit since then that we can sell at. 
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        // Iterate through once and update the two variables as you go, avoiding the need to check every single combination
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);
            maxProfit = Math.max(prices[i] - minPrice, maxProfit);
        }
        
        return maxProfit;
    }
}
