package com.kevinkan.medium;

/**
* Design a data structure that supports adding new words and searching for existing words.
* Implement the WordDictionary class:
*  void addWord(word) Adds word to the data structure.
*  bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. 
*    word may contain dots '.' where dots can be matched with any letter.
* 
* Constraints:
* 1 <= word.length <= 20
* word in addWord consists of lowercase English letters.
* word in search consist of '.' or lowercase English letters.
* There will be at most 2 dots in word for search queries.
* At most 10,000 calls will be made to addWord and search.
*/
class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    public WordDictionary(TrieNode root) {
        this.root = root;
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.children[c-'a'] == null) {
                current.children[c-'a'] = new TrieNode();
            }
            current = current.children[c-'a'];
        }
        current.isWord = true;
    }
    
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int start, TrieNode root) {
        TrieNode current = root;
        char c;
        for (int i = start; i < word.length(); i++) {
            c = word.charAt(i);
            if (c == '.') {
                for (TrieNode child : current.children) {
                    if (child != null && search(word, i+1, child)) {
                        return true;
                    }
                }
                return false;
            }
            else {
                if (current.children[c-'a'] == null) {
                    return false;
                }
                current = current.children[c-'a'];
            }
        }
        return current.isWord;
    }
}

class TrieNode {
    boolean isWord;
    TrieNode[] children;

    public TrieNode() {
        this.isWord = false;
        this.children = new TrieNode[26];
    }
}
