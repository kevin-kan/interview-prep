package com.kevinkan.easy;

/**
* Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
*
* Constraints:
* n == nums.length
* 1 <= n <= 10^4
* 0 <= nums[i] <= n
* All the numbers of nums are unique.
*/
public class MissingNumber {
    /*
    * Time Complexity: O(N) where N is the length of the array nums.
    * We traverse the array once and compare against math formula.
    * Space Complexity: O(1) as we are using only a constant amount of extra space.
    */ 
    public int missingNumber(int[] nums) {
        int n = nums.length;
        // Sum of first n natural numbers formula: n * (n + 1) / 2
        int expectedSum = n * (n + 1) / 2;

        int actualSum = 0;
        for (int i = 0; i < n; i++) {
            actualSum += nums[i];
        }
        return expectedSum - actualSum;
    }

    /* 
    * XOR Approach
    * This approach relies on the fact that x ^ x = 0 and x ^ 0 = x.
    * Starts off as all x ^ x = 0, but ends with the missing number since all other numbers cancel out
    * Time Complexity: O(N) where N is the length of the array nums.
    * We traverse the array once.
    * Space Complexity: O(1) as we are using only a constant amount of extra space. 
    */
    public int missingNumberXOR(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= i ^ nums[i];
        }
        return xor ^ n;
    }

}