package com.kashyap.dsa.linked_list;

/**
 * PATTERN   : Linked List — Fast/Slow + Reverse + Two Pointers
 * DIFFICULTY: Medium
 * DATE      : Mar 5, 2026
 *
 * PROBLEM:
 * Return true if linked list is a palindrome. O(n) time O(1) space.
 *
 * TRICK SENTENCE:
 * "Find middle. Cut and reverse second half.
 *  Compare both halves. Mention restore in interviews."
 *
 * THREE TECHNIQUES COMBINED:
 * Step 1: Fast/Slow  → find middle node
 * Step 2: Reverse    → reverse second half in place
 * Step 3: Two Ptrs   → compare first and reversed second half
 *
 * KEY INSIGHT — slow.next = null:
 * Must cut list at middle before reversing.
 * Without cut: first half still connects to second half.
 * Comparison loop overshoots middle → wrong result.
 * Cut creates two clean independent halves. ✅
 *
 * ODD vs EVEN length:
 * Odd  [1,2,3,2,1]: slow stops at 3 (middle)
 *                   second half = 2→1, reversed = 1→2
 *                   compare 1→2 with 1→2 ✅
 * Even [1,2,2,1]:   slow stops at 2 (first middle)
 *                   second half = 2→1, reversed = 1→2
 *                   compare 1→2 with 1→2 ✅
 *
 * PRODUCTION NOTE:
 * Restore original list after check by reversing
 * second half again and reconnecting to first half.
 * Always mention this in interviews.
 *
 * DRY RUN [1,2,3,2,1]:
 * Step1: slow=3 (middle)
 * Step2: cut at 3, reverse 2→1 to get 1→2
 * Step3: compare 1→2→3 with 1→2 → true ✅
 *
 * TIME : O(n) — three linear passes
 * SPACE: O(1) — only pointer variables, no extra structure
 *
 * SIMILAR PROBLEMS:
 * - Reverse Linked List (pure reverse technique)
 * - Reorder List (Fast/Slow + Reverse + Merge)
 * - Middle of Linked List (Fast/Slow only)
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle using fast/slow pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Cut list and reverse second half
        ListNode curr = slow.next;
        slow.next = null;       // cut — creates two independent halves
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;   // flip arrow
            prev = curr;        // prev advances
            curr = next;        // curr advances
        }
        // prev = head of reversed second half

        // Step 3: Compare first half with reversed second half
        ListNode p1 = head;
        ListNode p2 = prev;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }

        // Production note: restore list here before returning
        // (reverse second half again and reconnect to first half)

        return true;
    }
}