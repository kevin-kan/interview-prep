package com.kevinkan.easy;

/**
* Given an integer x, return true if x is a palindrome, and false otherwise.
*
* Constraints:
* -2^31 <= x <= 2^31 - 1
*/
public class PalindromeNumber {

    /*
    * A number is a palindrome if its the same number when reversed.
    * Time Complexity: O(N) where N is the number of digits in x.
    * Space Complexity: O(1) as we are using only a constant amount of extra space.
    */
    public boolean isPalindrome(int x) {
        int reversed = 0;
        int xCopy = x;
        while (x > 0) {
            reversed = (reversed * 10) + (x % 10);
            x = x / 10;
        }

        return reversed == xCopy;
    }

}
