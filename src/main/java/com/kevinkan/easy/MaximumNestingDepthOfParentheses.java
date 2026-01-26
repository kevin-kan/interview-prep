package com.kevinkan.easy;

/**
* Given a valid parentheses string s, return the nesting depth of s. 
* The nesting depth is the maximum depth of balanced parentheses in the string.
*
* Constraints:
* 1 <= s.length <= 100
* s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
* It is guaranteed that parentheses expression is balanced.
*/
public class MaximumNestingDepthOfParentheses {
    /**
     * Counting approach
     * Time Complexity: O(n) where n is the length of the string.
     * Space Complexity: O(1) as we are using only a few integer variables.
     */
    public int maxDepth(String s) {
        int currentDepth = 0;
        int maxDepth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(currentDepth, maxDepth);
            }
            if (c == ')') {
                currentDepth--;
            }
        }

        return maxDepth;
    }
}
