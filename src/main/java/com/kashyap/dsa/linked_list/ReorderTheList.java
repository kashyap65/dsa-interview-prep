package com.kashyap.dsa.linked_list;

public class ReorderTheList {
    public void reorderList(ListNode head) {
        if(head==null || head.next==null)
            return;

        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode curr = slow.next;
        slow.next = null;
        ListNode prev = null;


        while(curr!=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        ListNode p1= head;
        ListNode p2=prev;

        //1-2-3-null and 5-4-null
        //1-5-2-4-3-null
        while(p1!=null && p2!=null){
            ListNode next1=p1.next;
            ListNode next2=p2.next;
            p1.next = p2;
            p2.next = next1;
            //1-5-2-3-null
            //4-null
            p1=next1;
            p2=next2;
        }
    }
}
