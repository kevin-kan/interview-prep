package com.kevinkan.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
* You are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and remove the letter at that index from word so that the frequency of every letter present in word is equal.
* Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and false otherwise.
*
* Note: The frequency of a letter x is the number of times it occurs in the string. You must remove exactly one letter and cannot choose to do nothing.
* 
* Constraints:
* 2 <= word.length <= 100
* word consists of lowercase English letters.
*/
public class RemoveLetterToEqualizeFrequency {

    /**
     * Time Complexity: O(n) where n is the length of the input string word.
     * Space Complexity: O(1) since the character count map will have at most 26 entries (one for each lowercase English letter).
     */
    public boolean equalFrequency(String word) {
        Map<Character, Integer> charCounts = new HashMap<>();
        Map<Integer, Integer> freqCounts = new HashMap<>();

        // Fill in the map with the character counts
        for (char c : word.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }
        // Fill in the map with the frequency counts
        for (int freq : charCounts.values()) {
            freqCounts.put(freq, freqCounts.getOrDefault(freq, 0) + 1);
        }

        // If too many different frequencies, it cannot be equalized. 
        // ie. abbccc -> a:1 b:2 c:3 -> 1:1 2:1 3:1 -> false
        if (freqCounts.size() > 2) {
            return false;
        }
        // If they're all the same frequency, then it should be all frequency 1 OR char types should be 1
        // ie. aabbccdd -> a:2 b:2 c:2 -> 2:3 -> false
        // ie. abc -> a:1 b:1 c:1 -> 1:3 -> true (ab)
        // ie. aaa -> a:3 -> 3:1 -> true (aa)
        else if (freqCounts.size() == 1) {
            return (freqCounts.containsKey(1) || charCounts.size() == 1);
        }
        // Otherwise theres 2 frequencies: 
        else {
            // One of them needs to be 1:1 for it to be removable
            // ie. aabbbccc -> a:2 b:3 c:3 -> 2:1 3:2 -> false
            // ie. abbbccc -> a:1 b:3 c:3 -> 1:1 3:2 -> true (bbbccc)
            if (freqCounts.containsKey(1) && freqCounts.get(1) == 1) return true;

            // OR The larger frequency can only be greater by 1, and it also only has 1 occurence
            // ie. aabbccc -> a:2 b:2 c:3 -> 2:2 3:1 -> true (aabbcc)
            int saveFirstKey = 0;
            int saveFirstValue = 0;
            boolean filled = false;
            for (Entry<Integer, Integer> e : freqCounts.entrySet()) {
                if (!filled) {
                    saveFirstKey = e.getKey();
                    saveFirstValue = e.getValue();
                    filled = true;
                }
                if (e.getKey() - saveFirstKey > 0) {
                    // 2nd value is larger
                    if (e.getKey() - saveFirstKey == 1 && e.getValue() == 1) return true;
                } else {
                    // 1st value is larger
                    if (saveFirstKey - e.getKey() == 1 && saveFirstValue == 1) return true;
                }
            }
        }

        return false;
    }
}
