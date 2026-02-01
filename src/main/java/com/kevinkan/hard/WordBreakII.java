package com.kevinkan.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
* Note that the same word in the dictionary may be reused multiple times in the segmentation.
* 
* Constraints:
* 1 <= s.length <= 20
* 1 <= wordDict.length <= 1000
* 1 <= wordDict[i].length <= 10
* s and wordDict[i] consist of only lowercase English letters.
* All the strings of wordDict are unique.
* Input is generated such that the length of the answer does not exceed 10^5.
*/
public class WordBreakII {

    /**
     * Backtracking Approach with Memoization
     * Time Complexity: O(2^n) to generate all possible sentences
     * Space Complexity: O(2^n * n) to store all possible sentences
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        // Convert wordDict to Set for O(1) searches
        Set<String> wordSet = new HashSet<>(wordDict);

        // Start with an empty HashMap to memoize the result and avoid redundant work
        return backtrack(s, wordSet, new HashMap<>());
    }

    private List<String> backtrack(String s, Set<String> wordSet, Map<String, List<String>> memo) {
        // Utilize the memo as soon as possible:
        if (memo.containsKey(s)) return memo.get(s);
        // Base Case
        if (s.length() == 0) return Arrays.asList("");

        List<String> res = new ArrayList<>();   
        // Try every possible prefix of the current string
        String prefix;
        for (int i = 1; i <= s.length(); i++) {
            prefix = s.substring(0, i);
            // Whenever there is a match, recursively call this again on the rest of the string. 
            if (wordSet.contains(prefix)) {
                List<String> subList = backtrack(s.substring(i), wordSet, memo);
                for (String sub : subList) {
                    // Combine this prefix to all the values in the subList generated from the remainder.
                    res.add(prefix + (sub.length() == 0 ? sub : " " + sub));
                    // This will build from "" -> "dog" -> "sand dog" -> "cat sand dog"
                    //                         -> "dog" -> "and dog" -> "cats and dog"
                }
            }
        }

        memo.put(s, res);
        return res;
    }
}
