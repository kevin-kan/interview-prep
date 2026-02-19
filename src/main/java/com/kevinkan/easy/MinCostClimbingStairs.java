package com.kevinkan.easy;

/**
* You are given an integer array cost where cost[i] is the cost of ith step on a staircase. 
* Once you pay the cost, you can either climb one or two steps.
* You can either start from the step with index 0, or the step with index 1.
* Return the minimum cost to reach the top of the floor.
* 
* Constraints:
* 2 <= cost.length <= 1000
* 0 <= cost[i] <= 999
*/
public class MinCostClimbingStairs {

    /**
     * Dynamic Programming Approach
     * Cost at 3 is equal to:
     * Cost at 2 + min cost at 2
     * Cost at 1 + min cost at 1
     * Therefore:
     * minCost[i] = min( minCost at previous step + the cost of the previous step,
     *                   minCost at 2 steps previous + cost of 2 steps previous)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int minCostClimbingStairs(int[] cost) {
        // int[] minCost = new int[cost.length + 1];
        int minCost1 = 0;
        int minCost2 = 0;
        int temp;

        int n = cost.length;

        if (n == 2) return Math.min(cost[0], cost[1]);

        for (int i = 2; i <= n; i++) {
            // minCost[i] = Math.min(  minCost[i-1] + cost[i-1], 
            //                         minCost[i-2] + cost[i-2]);
            temp = minCost1;
            minCost1 = minCost2;
            minCost2 = Math.min(temp + cost[i-2], minCost1 + cost[i-1]);
        }

        // return minCost[cost.length];
        return minCost2;
    }
}
