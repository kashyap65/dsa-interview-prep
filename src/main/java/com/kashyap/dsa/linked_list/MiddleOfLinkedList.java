package com.kashyap.dsa.linked_list;

/**
 * PATTERN   : Linked List — Fast/Slow Pointers
 * DIFFICULTY: Easy
 * DATE      : Mar 6, 2026
 *
 * PROBLEM:
 * Return middle node of linked list.
 * If two middles exist → return second middle.
 *
 * TRICK SENTENCE:
 * "Fast moves 2, slow moves 1. When fast hits end,
 *  slow is at middle. && not || or NPE guaranteed."
 *
 * ODD vs EVEN — handled automatically:
 *   ODD:  fast lands ON  last node → fast.next=null → stops
 *         slow = exact middle
 *   EVEN: fast lands PAST last node → fast=null → stops
 *         slow = second middle ✅
 *
 * CRITICAL BUG TO AVOID:
 *   || instead of && → NPE when fast.next=null
 *   fast touches fast.next AND fast.next.next
 *   BOTH must exist → use &&
 *
 * DRY RUN [1→2→3→4]:
 *   Start: slow=1, fast=1
 *   Step1: slow=2, fast=3
 *   Step2: slow=3 fast=5... wait n=4
 *   Start: slow=1, fast=1
 *   Step1: slow=2, fast=3  (fast.next=4, ok)
 *   Step2: slow=3, fast=5? No — fast=3.next.next=5? No.
 *   [1→2→3→4]: fast=3, fast.next=4, fast.next.next=null
 *   Step2: slow=3, fast=null → next check: fast=null → STOP
 *   return slow=3... wait problem says return 4 (second middle)
 *
 *   Recheck: Step1: slow=2,fast=3. fast.next=4≠null, ok.
 *            Step2: slow=3, fast=3.next.next=null
 *            fast=null → STOP. return slow=3 ❌?
 *
 *   Correct trace [1→2→3→4]:
 *   Start: slow=1, fast=1
 *   Check: fast=1≠null, fast.next=2≠null → enter
 *   Step1: fast=1.next.next=3, slow=2
 *   Check: fast=3≠null, fast.next=4≠null → enter
 *   Step2: fast=3.next.next=null, slow=3
 *   Check: fast=null → STOP
 *   return slow=3... but expected=4
 *
 *   Hmm — both start at head, so slow needs one more step?
 *   FIX: start fast = head.next for even lists? No —
 *   Standard LC solution returns node 3 for [1,2,3,4]?
 *   LC #876: [1,2,3,4] → output node 3 (index 2) ✅
 *   Problem says second middle = node 4?
 *   Re-read: [1,2,3,4,5,6] → second middle = 4 ✅
 *            [1,2,3,4]     → second middle = 3 ✅ (indices 1,2 → return index 2)
 *   So return slow=3 for [1,2,3,4] IS correct ✅
 *
 * TIME : O(n)
 * SPACE: O(1)
 *
 * SIMILAR PROBLEMS:
 * - Palindrome Linked List (uses this to find middle)
 * - Reorder List
 * - Delete Middle Node
 */
public class MiddleOfLinkedList {

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {  // && not ||
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}