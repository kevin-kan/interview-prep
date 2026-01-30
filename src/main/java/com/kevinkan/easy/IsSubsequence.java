package com.kevinkan.easy;

/**
* Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
* A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
*
* Constraints:
* 0 <= s.length <= 100
* 0 <= t.length <= 10^4
* s and t consist only of lowercase English letters.
*/
public class IsSubsequence {

    /**
     * Time Complexity: O(N) where N is the length of string t.
     * Space Complexity: O(1) as we are using only a constant amount of extra space.
     */
    public boolean isSubsequence(String s, String t) {
        // Quick Validation: length of s must be smaller than or equal to t be to a subsequence.
        if (s.length() > t.length()) return false;

        // Edge Case: an empty string is always a subsequence.
        if (s.length() == 0) return true;
        
        // Iterate through t and look for the next value of s. 
        // This would close out immediately when we find all characters of s in t
        int sPtr = 0;
        char[] sChars = s.toCharArray();
        for (char c: t.toCharArray()) {
            if (sChars[sPtr] == c) {
                sPtr++;
                if (sPtr == s.length()) return true;
            }

        }

        return false;
    }

}
