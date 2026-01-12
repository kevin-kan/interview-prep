package com.kevinkan.medium;


/**
* Given a string s, return the longest palindromic substring in s.
*
* Constraints:
* 1 <= s.length <= 1000
* s consist of only digits and English letters.
*/
public class LongestPalindromicString {
    /*
    * Expand Around Center approach
    * Time Complexity: O(n^2) where n is the length of the string.
    * Space Complexity: O(1) since we are using only constant extra space.
    */
    public String longestPalindrome(String s) {
        // Base/Edge Case
        if (s.length() <= 1) {
            return s;
        }
        // Keep your best String
        String longest = "";
        String oddExpansion, evenExpansion;
        // For each character in the string (-1), expand outwards
        for (int i = 0; i < s.length() - 1; i++) {
            // Since palindrome can have 1 or 2 char center, expand both ways, and take the best
            oddExpansion = expandAroundCenter(s, i, i);
            evenExpansion = expandAroundCenter(s, i, i+1);

            // Update the tracked longest with any longer expansion
            if (oddExpansion.length() > longest.length()) {
                longest = oddExpansion;
            }
            if (evenExpansion.length() > longest.length()) {
                longest = evenExpansion;
            }
        }
        return longest;
    }

    /**
     * Expand around center helper method
     * @param s the full string
     * @param left the left index
     * @param right the right index
     * @return the longest palindrome
     */
    private String expandAroundCenter(String s, int left, int right) {
        // Expand under 3 conditions:
        // 1. within left bounds
        // 2. within right bounds
        // 3. left and right characters match (palindromic)
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) { 
            left--;
            right++;
        }
        // return the longest palindrome from the provided center 
        // Use left + 1 and right to go back to the last valid palindrome
        return s.substring(left + 1, right);
    }

    /**
     * Manacher's Algorithm
     * Time Complexity: O(n) where n is the length of the string.
     * Space Complexity: O(n) for the transformed string and the palindrome lengths array.
     */
    public String longestPalindromeManacher(String s) {
        // Pad the characters with a symbol
        // This makes all palindromes odd by default, cuttting the number of expansions in half.
        StringBuilder transformed = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            transformed.append("#").append(c);
        }
        transformed.append("#$");

        int tLen = transformed.length();
        int[] dpArray = new int[tLen]; // Array to store the length of the palindrome radius
        int center = 0, right = 0; // Current center and right edge of the palindrome
        for (int i = 1; i < tLen - 1; i++) {
            int mirror = 2 * center - i; // Calculate the mirror index of i

            // If i is within the right boundary, use the mirror's palindrome length
            if (i < right) {
                dpArray[i] = Math.min(right - i, dpArray[mirror]);
            }

            // Attempt to expand the palindrome centered at i
            while (transformed.charAt(i + (1 + dpArray[i])) == transformed.charAt(i - (1 + dpArray[i]))) {
                dpArray[i]++;
            }

            // If the palindrome centered at i expands beyond right, adjust center and right
            if (i + dpArray[i] > right) {
                center = i;
                right = i + dpArray[i];
            }
        }
        // Find the maximum element in p
        int maxLength = 0;
        int centerIndex = 0;
        for (int i = 1; i < tLen - 1; i++) {
            if (dpArray[i] > maxLength) {
                maxLength = dpArray[i];
                centerIndex = i;
            }
        }

        return s.substring((centerIndex - maxLength) / 2, (centerIndex + maxLength) / 2);
    }
}
