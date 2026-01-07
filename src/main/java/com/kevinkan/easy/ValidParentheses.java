package com.kevinkan.easy;

import java.util.Map;
import java.util.Stack;

/**
* Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
* An input string is valid if:
* 
* Open brackets must be closed by the same type of brackets.
* Open brackets must be closed in the correct order.
* Every close bracket has a corresponding open bracket of the same type.
* 
* Constraints:
* 1 <= s.length <= 104
* s consists of parentheses only '()[]{}'.
* 
*/
public class ValidParentheses {
    Map<Character,Character> parenMap = Map.of(
        ')', '(',
        ']', '[',
        '}', '{'
    );

    /*
    * Time: O(n) - We loop through the string once
    * Space: O(n) - In the worst case, we have all open parens
    */
    public boolean isValid(String s) {
        // First check if it's an even number of characters. If not, it won't match no matter what.
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] sCharArray = s.toCharArray(); // 1 time conversion is faster than a lot of charAt() calls
        // Loop through all characters
        for (int i = 0; i < s.length(); i++) {
            char c = sCharArray[i];
            // If it's an open paren, just add it to the stack
            if (c == '(' || c == '[' || c == '{') {
                stack.add(c);
            }
            // If it's a closed paren:
            else {
                // And the stack is empty, no match. 
                if (stack.isEmpty() ) 
                    return false;
                // And the stack's top value isn't the matching open paren to the current close paren, invalid match.  
                if (!stack.pop().equals(parenMap.get(c))) 
                    return false;
            }
        }
        
        // Finally, check if there's any leftover opens that weren't closed.
        return stack.isEmpty();
    }
}
