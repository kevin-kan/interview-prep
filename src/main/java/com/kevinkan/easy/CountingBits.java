package com.kevinkan.easy;

/**
* Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
*
* Constraints:
* 0 <= n <= 10^5
*/
public class CountingBits {

    /*
    * Dynamic Programming Approach
    * Time Complexity: O(n) where n is the input number.
    * We compute the number of 1's for each number from 0 to n.
    * Space Complexity: O(n) for the output array.
    */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0; // Base case
        if (n == 0) return result;
        result[1] = 1; // Base case
        for (int i = 1; i < result.length; i++) {
            result[i] = (i % 2 == 0) ? result[i / 2] : result[i - 1] + 1;
        }
        return result;
    }
}
