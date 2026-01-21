package com.kevinkan.medium;

import java.util.Stack;

/**
* Given an encoded string, return its decoded string.
* The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is repeated exactly k times.
* Note that k is guaranteed to be a positive integer.
* You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
* Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
* For example, there will not be input like 3a or 2[4].
*
* Constraints:
* 1 <= s.length <= 300
* s consists of lowercase English letters, digits, and square brackets '[]'.
* s is guaranteed to be a valid input.
* All the integers in s are in the range [1, 300].
*/
public class DecodeString {

    /**
     * Dual Stack approach
     * Time: O(N) where N is the length of the string
     * Space: O(N) for the stacks
     */
    public String decodeString(String s) {
        char[] sChars = s.toCharArray();
        
        // Storage for tracking number of times to repeat
        Stack<Integer> timesStack = new Stack<>();
        int times = 0;

        // Storage for tracking what to repeat
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            // If it's a digit, we need to start accumulating to find the amount of times to add the string
            if (Character.isDigit(sChars[i])) {
                times = times * 10 + (sChars[i] - '0');
            }
            else if (sChars[i] == '[') {
                // Store the number of times to repeat and reset
                timesStack.push(times);
                times = 0;
                // Store the strings up until now
                stringStack.push(currentString);
                currentString = new StringBuilder();
            }
            else if (sChars[i] == ']') {
                // Retrieve the stored number of times to repeat
                int storedRepeater = timesStack.pop();
                // Save off separately the current string to be repeated
                StringBuilder stringToRepeat = currentString;
                // Get the latest string back from the stack to append to
                currentString = stringStack.pop();
                // Append the stringToRepeat storedRepeater times
                for (int j = 0; j < storedRepeater; j++) {
                    currentString.append(stringToRepeat);
                }
            }
            else {
                // For any letters, add it to the current String to be repeated or be a part of the final string
                currentString.append(sChars[i]);
            }
        }
        return currentString.toString();
    }
}
