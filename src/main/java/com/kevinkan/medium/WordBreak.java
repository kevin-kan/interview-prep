package com.kevinkan.medium;

import java.util.List;

/**
* Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
* Note that the same word in the dictionary may be reused multiple times in the segmentation.
* 
* Constraints:
* 1 <= s.length <= 300
* 1 <= wordDict.length <= 1000
* 1 <= wordDict[i].length <= 20
* s and wordDict[i] consist of only lowercase English letters.
* All the strings of wordDict are unique.
*/
public class WordBreak {

    /**
     * Bottom-up Dynamic Programming Approach
     * Time Complexity: O(n * m), where n is the length of the string s and m is the maximum length of words in wordDict.
     * Space Complexity: O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (String word : wordDict) {
                // If the substring is not OOB and matches the word in wordDict, then set it to what it was before 
                if (i + word.length() <= s.length() &&
                    s.substring(i, i + word.length()).equals(word)) {
                    dp[i] = dp[i + word.length()];

                    // If we found a match that works, we can stop looking through wordDict
                    if (dp[i]) break;
                }
            }
        }

        // Final value is stored into the first element 
        return dp[0];
    }
}
