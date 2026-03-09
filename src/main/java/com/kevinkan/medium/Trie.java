package com.kevinkan.medium;

import com.kevinkan.utility.TrieNode;

/**
* A prefix tree (also known as a trie) is a tree data structure used to efficiently store and retrieve keys in a set of strings. 
* Some applications of this data structure include auto-complete and spell checker systems.
* 
* Implement the PrefixTree class:
* PrefixTree() Initializes the prefix tree object.
*  void insert(String word) Inserts the string word into the prefix tree.
*  boolean search(String word) Returns true if the string word is in the prefix tree (i.e., was inserted before), and false otherwise.
*  boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
* 
* Constraints:
* 1 <= word.length, prefix.length <= 1000
* word and prefix are made up of lowercase English letters.
*/
public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c-'a'] == null) {
                return false;
            }
            curr = curr.children[c-'a'];
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c-'a'] == null) {
                return false;
            }
            curr = curr.children[c-'a'];
        }
        return true;
    }

}
