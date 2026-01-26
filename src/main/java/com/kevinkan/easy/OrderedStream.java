package com.kevinkan.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Design an Ordered Stream
* There is a stream of n (id, value) pairs arriving in an arbitrary order, where id is an integer between 1 and n and value is a string. 
* No two pairs have the same id.
* Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion.
* The concatenation of all the chunks should result in a list of the sorted values.
* 
* Implement the OrderedStream class:
* OrderedStream() Constructs the stream (Variation: no-argument constructor).
* String[] insert(int id, String value) Inserts the pair (id, value) into the stream, then returns the largest possible chunk of currently inserted values that appear next in the order.
*
* Contraints:
* 1 <= n <= 1000
* 1 <= id <= n
* value.length == 5
* value consists only of lowercase letters.
* Each call to insert will have a unique id.
* Exactly n calls will be made to insert.
*/
public class OrderedStream {
    private int pointer;
    private Map<Integer, String> stream;

    public OrderedStream() {
        this.pointer = 1;
        this.stream = new HashMap<>();
    }

    public OrderedStream(int n) {
        // This is the constructor that is requested by default in the question
        // but we will be using the no-argument constructor for a more generic approach
        this();
    }

    public List<String> insert(int idKey, String value) {
        // Add the new value into our map
        stream.put(idKey, value);
        List<String> output = new ArrayList<>();
        // If the inserted id matches the pointer, we can start collecting values
        if (idKey == pointer) {
            while (stream.containsKey(pointer)) {
                // Remove the value from the map so that we don't return it again
                output.add(stream.remove(pointer));
                pointer++;
            }
        }
        return output;
    }
}
