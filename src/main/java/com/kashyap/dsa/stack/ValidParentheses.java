package com.kashyap.dsa.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * PATTERN   : Stack
 * DIFFICULTY: Easy
 * DATE      : Mar 6, 2026
 *
 * PROBLEM:
 * Determine if bracket string is valid.
 *
 * TRICK SENTENCE:
 * "Open → push. Close → check top matches or fail immediately.
 *  End → stack must be empty."
 *
 * 3 FAILURE CONDITIONS:
 * 1. Close bracket + stack empty     → no match possible
 * 2. Close bracket + top mismatch    → wrong order
 * 3. End of string + stack not empty → unmatched opens remain
 *
 * WHY STACK:
 * "Most recent unmatched open" = top of stack = LIFO.
 * Stack gives you the most recent thing automatically.
 *
 * WHY MATCHING MAP:
 * Cleaner than if-else chains. Scales to any bracket type.
 * map.containsKey(ch) tells you open vs close instantly.
 *
 * TIME : O(n) — single pass
 * SPACE: O(n) — stack holds at most n/2 open brackets
 *
 * SIMILAR PROBLEMS:
 * - Min Add to Make Parentheses Valid
 * - Longest Valid Parentheses
 * - Remove Invalid Parentheses
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (map.containsKey(ch)) {
                // close bracket — check top or fail
                if (stack.isEmpty() || stack.peek() != map.get(ch))
                    return false;
                stack.pop();
            } else {
                // open bracket — always push
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}