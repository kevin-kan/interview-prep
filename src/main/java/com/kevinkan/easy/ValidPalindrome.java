package com.kevinkan.easy;

/**
* A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
* Given a string s, return true if it is a palindrome, or false otherwise.
*
* Constraints:
* 1 <= s.length <= 2 * 10^5
* s consists only of printable ASCII characters.
*/
public class ValidPalindrome {

    /*
    * Time Complexity: O(N) where N is the length of the string s.
    * We traverse the string once with two pointers.
    * Space Complexity: O(1) as we are using only a constant amount of extra space.
    */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            // Shift the left and right until both left and right are at an alphanumeric character
            if (!isAlphanumeric(chars[left])) {
                left++;
            }
            else if (!isAlphanumeric(chars[right])) {
                right--;
            }
            else if (Character.toLowerCase(chars[left]) != (Character.toLowerCase(chars[right]))) {
                return false;
            }
            else {
                left++;
                right--;
            }
        }
        return true;
    }

    // Manual Comparison is marginally faster than Character.isAlphanumeric()
    public boolean isAlphanumeric(char c) {
        return  (c >= '0' && c <= '9') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z');
    }
}
