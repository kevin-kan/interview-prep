package com.kevinkan.easy;

/**
* Reverse bits of a given 32 bits signed integer.
*
* Constraints:
* 0 <= n < 2^32
* n is even
*/
public class ReverseBits {
    /*
    * Time Complexity: O(1) as the number of bits is fixed (32 bits).
    * Space Complexity: O(1) as we are using a constant amount of space.
    */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1; // left shift the result bits to make space for the new bit
            result = result + (n & 1); // add the rightmost bit of n using mask of 1. 
            n = n >> 1; // right shift n to update that rightmost bit.  
        }
        return result;
    }
}
