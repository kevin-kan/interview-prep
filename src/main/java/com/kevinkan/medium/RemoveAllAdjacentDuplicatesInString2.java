package com.kevinkan.medium;

import java.util.Stack;

/**
* You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
* We repeatedly make k duplicate removals on s until we no longer can.
* Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
* 
* Constraints:
* 0 <= s.length <= 10^5
* 2 <= k <= 10^4
* s consists of English letters.
*/
public class RemoveAllAdjacentDuplicatesInString2 {

    /**
     * Stack and StringBuilder approach
     * Time Complexity: O(n) where n is the length of the string.
     * Space Complexity: O(n) in the worst case for the stack.
     */
    public String removeDuplicates(String s, int k) {
        // Base cases
        if (s.length() < k || s.length() == 0) return s;
        if (k < 2) return "";

        // Two stacks to track the character and the count.
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();

        char[] sChars = s.toCharArray();
        for (char c : sChars) {
            // If it's empty or not matching previous, restart the count at 1
            // Otherwise, increment the count
            if (charStack.isEmpty() || !(charStack.peek().equals(c))) {
                countStack.add(1);
            } else {
                countStack.add(countStack.peek() + 1);
            }
            charStack.add(c);
            
            // What if we hit k?
            if (countStack.peek() == k) {
                for (int i = 0; i < k; i++) {
                    countStack.pop();
                    charStack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!charStack.isEmpty()) sb.append(charStack.pop());

        return sb.reverse().toString();
    }
}
