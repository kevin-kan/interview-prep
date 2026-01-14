package com.kevinkan.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
* Given two strings s and t, return true if t is an anagram of s, and false otherwise.
*
* Constraints:
* 1 <= s.length, t.length <= 5 * 10^4
* s and t consist of lowercase English letters.
*/
public class ValidAnagram {

    /**
     * Sorting Approach
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(1) if sorting in place, otherwise O(n)
     */
    public boolean isAnagramSorting(String s, String t) {
        // Quick fail:
        if (s.length() != t.length()) return false;

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    /**
     * Hash Map Approach
     * Time Complexity: O(n)
     * Space Complexity: O(1) since the character set is limited to lowercase English letters or all Unicode letters
     * This can be simplified to just a limited array of size 26 if limited to lowercase English letters. 
     */
    public boolean isAnagramHashMap(String s, String t) {
        // Quick fail:
        if (s.length() != t.length()) return false;

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        // HashMap to store letter counts;
        Map<Character, Integer> charCount = new HashMap<>();
        // Add character counts for s
        for (int i = 0; i < s.length(); i++) {
            charCount.put(sChars[i], charCount.getOrDefault(sChars[i], 0) + 1);
        }
        // Subtract character counts for t 
        for (int i = 0; i < t.length(); i++) {
            if (charCount.get(tChars[i]) == null || charCount.get(tChars[i]) == 0) {
                return false;
            }
            else {
                charCount.put(tChars[i], charCount.get(tChars[i]) - 1);
            }
        }
        return true;
    }
}