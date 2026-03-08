package com.kashyap.dsa.linked_list;

/**
 * PATTERN   : Linked List — Fast/Slow Pointers
 * DIFFICULTY: Medium
 * DATE      : Mar 5, 2026
 *
 * PROBLEM:
 * Given head of linked list, return true if cycle exists.
 *
 * TRICK SENTENCE:
 * "Save before you flip, check before you move,
 *  always know where null is."
 *
 * TECHNIQUE — FAST/SLOW POINTERS:
 * Slow moves 1 step. Fast moves 2 steps.
 * If cycle exists → fast laps slow → they meet.
 * If no cycle     → fast hits null → loop ends.
 *
 * WHY IT WORKS (The Math):
 * Fast gains 1 step on slow every iteration.
 * In a cycle of length C → they meet after C iterations.
 * Like a fast runner lapping a slow runner on circular track.
 *
 * WHY == NOT .equals():
 * Need SAME NODE in memory — not same value.
 * Two nodes can have same val but be different objects.
 * == checks memory address. .equals() checks value.
 * Cycle detection always uses ==.
 *
 * WHY fast!=null && fast.next!=null BOTH NEEDED:
 * fast!=null      → fast itself hasn't gone off end
 * fast.next!=null → fast.next.next won't NPE
 * Both required together — neither alone is sufficient.
 *
 * DRY RUN — cycle: 1→2→3→4→(back to 2):
 * Start: slow=1, fast=1
 * Step1: slow=2, fast=3
 * Step2: slow=3, fast=2 (wrapped around)
 * Step3: slow=4, fast=4 → MEET → return true ✅
 *
 * DRY RUN — no cycle: 1→2→3→null:
 * Start: slow=1, fast=1
 * Step1: slow=2, fast=3
 * Step2: slow=3, fast=null → loop ends → return false ✅
 *
 * TIME : O(n) — fast pointer traverses at most 2n steps
 * SPACE: O(1) — only two pointers, no extra data structure
 *
 * SIMILAR PROBLEMS:
 * - Find Middle of Linked List (Fast/Slow)
 * - Find Start of Cycle (Fast/Slow + math)
 * - Happy Number (Fast/Slow on numbers)
 * - Palindrome Linked List (Fast/Slow + Reverse)
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // 1 step
            fast = fast.next.next;      // 2 steps

            if (slow == fast) return true;  // same node → cycle
        }

        return false;  // fast hit null → no cycle
    }
}