package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* Implement the RandomizedSet class:
* RandomizedSet() Initializes the RandomizedSet object.
* bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
* bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
* int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
* You must implement the functions of the class such that each function works in average O(1) time complexity.
* 
* Constraints:
* -2^31 <= val <= 2^31 - 1
* At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
* There will be at least one element in the data structure when getRandom is called.
*/
public class InsertDeleteGetRandomO1 { // RandomizedSet
    private Map<Integer, Integer> valToIndex;
    private List<Integer> valList;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        this.valToIndex = new HashMap<>();
        this.valList = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valToIndex.containsKey(val)) return false;

        valToIndex.put(val, valList.size());
        valList.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!valToIndex.containsKey(val)) return false;

        int index = valToIndex.get(val);
        // Update latest value to the Hashmap to the current index of the value we're looking to remove
        valToIndex.put(valList.get(valList.size() - 1), index);
        // Update the updated Index in the Set to point to latest Value to match the Hashmap 
        valList.set(index, valList.get(valList.size() - 1));

        // Remove the value from the Hashmap (currently 2 mapping to 1 index)
        valToIndex.remove(val);
        // Remove the latest value from Set (since it has been copied down to the index we were removing from)
        valList.remove(valList.size() - 1);

        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        return valList.get(rand.nextInt(valList.size()));
    }
}
