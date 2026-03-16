package com.kashyap.dsa.linked_list;

public class FloydDetectCycleStart {

    public ListNode detectCycleStart(ListNode head) {
        if(head==null || head.next==null)
            return null;

        ListNode slow=head;
        ListNode fast = head;
        boolean isCycleExist = false;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;

            if(slow==fast) {
                isCycleExist = true;
                break;
            }
        }

        if(isCycleExist){
            slow=head;
            while(slow!=fast){
                slow=slow.next;
                fast=fast.next;
            }
            return slow;
        }


        return null;
    }
}
