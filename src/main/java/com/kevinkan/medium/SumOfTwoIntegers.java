package com.kevinkan.medium;

/**
* Given two integers a and b, return the sum of the two integers without using the operators + and -.
* 
* Constraints:
* -1000 <= a, b <= 1000
*/
public class SumOfTwoIntegers {
    /**
     * Bit Manipulation approach
     * Time Complexity: O(1) since the number of bits is fixed (32 bits for integers).
     * Space Complexity: O(1) since we are using only constant extra space.
     */
    public int getSum(int a, int b) {
        int carry;
        while (b != 0) {
            carry = a & b;      // Calculate carry bits
            a = a ^ b;          // Sum without carry
            b = carry << 1;     // Shift carry bits to the left
        }
        return a;

        /*
        For example, if a = 5 (0101) and b = 3 (0011):
        1. carry = a & b = 0101 & 0011 = 0001 (1 in decimal) 
          - This identifies the bits where both a and b have 1s, which will generate a carry.
        2. a = a ^ b = 0101 ^ 0011 = 0110 (6 in decimal)
          - This computes the sum of a and b without considering the carry.
        3. b = carry << 1 = 0001 << 1 = 0010 (2 in decimal)
          - This shifts the carry bits to the left, preparing them to be added in the next iteration.
        The process repeats until there are no carry bits left (b becomes 0).
        */
    }
}
