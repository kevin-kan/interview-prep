package com.kevinkan.easy;

import java.util.HashMap;
import java.util.Map;

/**
* You are climbing a staircase. It takes n steps to reach the top.
* 
* Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
* 
* Constraints:
* 1 <= n <= 45
*/
public class ClimbingStairs {

    /*
    * Dynamic Programming approach
    * Time Complexity: O(n) where n is the number of steps.
    * Space Complexity: O(1) as we are only using a constant amount of space.
    */
    public int climbStairsDP(int n) {
        // Only need to save the last 2 step maximums
        int previous1 = 2; // when n=2
        int previous2 = 1; // when n=1

        if (n == 1) return previous2;
        if (n == 2) return previous1;

        int temp;
        // Iterate upwards until we reach the end of the stairs. 
        for (int i = 3; i <= n; i++ ) {
            temp = previous2;
            previous2 = previous1;
            previous1 = previous2 + temp;
        }

        return previous1;
    }

    /* 
    * Recursive approach with memoization
    * Time Complexity: O(n) where n is the number of steps.
    * Space Complexity: O(n) due to the memoization array.
    */
    public int climbStairsRecursive(int n) {
        // Pass a HashMap around to memoize
        Map<Integer, Integer> memo = new HashMap<>();
        // Base case of 1 step and 2 steps
        memo.put(1, 1);
        memo.put(2, 2);
        // Kick off the recursion
        return climbStairsRecursiveHelper(n, memo);
    }

    private int climbStairsRecursiveHelper(int n, Map<Integer, Integer> memo) {
        // If not memoized, then call recursion to work your way from top down. 
        return (memo.containsKey(n))? memo.get(n) : climbStairsRecursiveHelper(n-1, memo) + climbStairsRecursiveHelper(n-2, memo);
    }
}
