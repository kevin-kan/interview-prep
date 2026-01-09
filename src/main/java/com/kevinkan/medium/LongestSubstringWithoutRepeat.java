package com.kevinkan.medium;

import java.util.HashMap;
import java.util.Map;

/**
* Given a string s, find the length of the longest substring without repeating characters.
* 
* Constraints:
* 0 <= s.length <= 5 * 10^4
* s consists of English letters, digits, symbols and spaces.
*/
public class LongestSubstringWithoutRepeat {

    /*
    * Sliding Window approach
    * Time Complexity: O(n) where n is the length of the string.
    * Space Complexity: O(1) since the character set is fixed (ASCII).
    */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int left = 0, maxLength = 0;
        char[] sChars = s.toCharArray();
        char current;
        
        for (int right = 0; right < s.length(); right++) {
            current = sChars[right];
            // If our map contains the key AND it's a repeat (between left and right pointers)
            if (charIndexMap.containsKey(current) && charIndexMap.get(current) >= left) {
                left = charIndexMap.get(current) + 1;
            }
            // Update your map and maxLength
            charIndexMap.put(current, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
