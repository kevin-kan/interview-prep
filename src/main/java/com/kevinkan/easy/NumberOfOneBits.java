package com.kevinkan.easy;

/**
* Given a positive integer n, write a function that returns the number of set bits in its binary representation (also known as the Hamming weight).
*
* Constraints:
* 1 <= n < 2^31 - 1
*/
public class NumberOfOneBits {
    /*
    * Time Complexity: O(1) as the number of bits is fixed (32 bits).
    * Space Complexity: O(1) as we are using a constant amount of space.
    */
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1; // add the rightmost digit (either 0 or 1)
            n >>= 1; // right shift the number
        }
        return count;
    }
}
