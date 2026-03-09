package com.kevinkan.utility;

public class TrieNode {
    public boolean isWord;
    public TrieNode[] children;

    public TrieNode() {
        this.isWord = false;
        this.children = new TrieNode[26];
    }
    public TrieNode(boolean isWord) {
        this.isWord = isWord;
        this.children = new TrieNode[26];
    }
}