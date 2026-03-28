package com.serain.exercise.leetcode;

import com.serain.parameter.ListNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  16:39
 * @Description: TODO
 * @Version: 1.0
 */
public class E19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        while(n-->0){
            first = first.next;
        }
        ListNode second = dummy;
        while(first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
