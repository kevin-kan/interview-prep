package com.kevinkan.medium;

import java.util.HashMap;
import java.util.Map;

/**
* Implement a data structure that follows the constraints of a Least Recently Used (LRU) cache.
* 
* Implement the LRUCache class:
* LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
* int get(int key) Return the value of the key if the key exists, otherwise return -1.
* void put(int key, int value) Update the value of the key if the key exists.
* Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
* The functions get and put must each run in O(1) average time complexity.
* 
* Constraints:
* 1 <= capacity <= 3000
* 0 <= key <= 10^4
* 0 <= value <= 10^5
* At most 2 * 10^5 calls will be made to get and put.
*/
public class LRUCache { 
    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node (0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // Key was accessed. Update the order of doubly-linked-list
            remove(node); // remove the node
            insert(node); // and insert it back at the front
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key)); // remove the old node
            cache.remove(key);
        } else if (cache.size() == capacity) {
            // We're already at capacity. We need to make space
            cache.remove(tail.prev.key);
            remove(tail.prev);
        }
        insert(new Node(key, value));
        cache.put(key, head.next);
    }

    private void remove(Node node) {
        Node savePrev = node.prev;
        Node saveNext = node.next;
        
        savePrev.next = saveNext;
        saveNext.prev = savePrev;
    }

    private void insert(Node node) {
        // Name the head.next so we can manipulate
        Node temp = head.next;

        // Squeeze Node in between and point forward and back
        node.next = temp;
        node.prev = head;

        // Update to point to the new node
        head.next = node;
        temp.prev = node;
    }

    /**
     * Node class used for doubly-linked list
     */
    public class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }
}
