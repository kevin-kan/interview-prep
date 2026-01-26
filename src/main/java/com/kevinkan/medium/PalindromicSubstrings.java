package com.kevinkan.medium;

/**
* Given a string s, return the number of palindromic substrings in it.
* A substring is a contiguous sequence of characters within the string.
* A palindromic substring is a substring which reads the same backward as forward.
*
* Constraints:
* 1 <= s.length <= 1000
* s consist of lowercase English letters.
*/
public class PalindromicSubstrings {
    /*
    * Expand Around Center approach
    * Solution is derived from Longest Palindromic Substring problem
    * Time Complexity: O(n^2) where n is the length of the string.
    * Space Complexity: O(1) since we are using only constant extra space.
    */
    public int countSubstrings(String s) {
        // Base/Edge Case
        if (s.length() <= 1) {
            return s.length();
        }
        int count = 0;
        // For each character in the string (-1), expand outwards
        for (int i = 0; i < s.length() - 1; i++) {
            // Since palindrome can have 1 or 2 char center, expand both ways and add to total count
            count += expandAroundCenter(s, i, i) / 2 + 1; // +1 for the center
            count += expandAroundCenter(s, i, i+1) / 2;
        }
        return count+1; // +1 for the last single character palindrome
    }

    /**
     * Expand around center helper method
     * @param s the full string
     * @param left the left index
     * @param right the right index
     * @return the longest palindrome
     */
    private int expandAroundCenter(String s, int left, int right) {
        // Expand under 3 conditions:
        // 1. within left bounds
        // 2. within right bounds
        // 3. left and right characters match (palindromic)
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) { 
            left--;
            right++;
        }
        // For the longest palindromic substring, we would:
        // return the longest palindrome from the provided center 
        // Use left + 1 and right to go back to the last valid palindrome
        // return s.substring(left + 1, right);

        // For counting palindromic substrings, we just need the length
        return right - left - 1;
    }

}
