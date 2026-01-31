package com.kevinkan.hard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of word (beginWord -> s1 -> s2 -> ... -> sk) such that:
* Every adjacent pair of words differs by a single letter.
* Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
* sk == endWord
* Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
* 
* Constraints:
* 1 <= beginWord.length <= 10
* endWord.length == beginWord.length
* 1 <= wordList.length <= 5000
* wordList[i].length == beginWord.length
* beginWord, endWord, and wordList[i] consist of lowercase English letters.
* beginWord != endWord
* All the words in wordList are unique.
*/
public class WordLadder {

    /**
     * The goal is to find the number of transformations (+1) needed to convert beginWord to endWord by changing one letter at a time, with each intermediate word being in the wordList.
     * We can use a bidirectional Breadth-First Search (BFS) to explore transformations level by level.
     * For this, we will maintain two sets: one for the current level of words from the begin side and another for the end side, and expand the smaller set at each step to optimize performance.
     * By using the bidirectional approach, we can significantly reduce the search space compared to a unidirectional BFS (Queue Approach).
     * Time Complexity: O(N * M^2) where N is the number of words in the wordList and M is the length of each word.
     * Space Complexity: O(N * M) for the wordSet, visitedSet, and the begin and end sets.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // We will need a quick way to access the wordList, so we will use a HashSet for its O(1) lookup:
        Set<String> wordSet = new HashSet<>(wordList); 
        // We need a visited HashSet for words we've transformed into (dont want to loop endlessly)
        Set<String> visitedSet = new HashSet<>();
        // We need two sets to represent the current level of words from both the begin and end sides 
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        // Base case: if the endWord is not in the wordList, we cannot transform to it
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        int transformations = 1; // Start with 1 to account for the beginWord itself
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // To always expand the smaller set, swap begin and end sets if necessary
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevelSet = new HashSet<>();
            char[] wordChars;
            for (String word : beginSet) {
                // For each word, try changing each character to every letter from 'a' to 'z' (26 possibilities)
                wordChars = word.toCharArray();
                for (int i = 0; i < wordChars.length; i++) {
                    char originalChar = wordChars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        // If we have the same word after change, immediate skip (continue)
                        if (c == originalChar) continue;

                        // Create a new word from the letter change
                        wordChars[i] = c;
                        String newWord = new String(wordChars);
                        // If the new word is in the endSet, then we've connected the two sets
                        if (endSet.contains(newWord)) {
                            return transformations + 1; // Found a connection
                        }
                        // If the new word is in the wordSet and not yet visited, add it to the next level
                        if (wordSet.contains(newWord) && !visitedSet.contains(newWord)) {
                            visitedSet.add(newWord);
                            nextLevelSet.add(newWord);
                        }
                    }
                    wordChars[i] = originalChar; // Restore original character
                }
            }
            // We've processed all words in the current level (beginSet), so we move to the next level
            // nextLevelSet contains all the valid transformations from the current level
            beginSet = nextLevelSet;
            transformations++;
        }


        return 0; // No transformation sequence found
    }
}
