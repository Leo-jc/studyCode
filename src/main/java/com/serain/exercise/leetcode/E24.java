package com.serain.exercise.leetcode;

import com.serain.parameter.ListNode;

import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  16:49
 * @Description: TODO
 * @Version: 1.0
 */
public class E24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while(head!=null && head.next!=null){
            ListNode newNode=head.next;
            cur.next=newNode;
            head.next=newNode.next;
            newNode.next=head;
            cur=head;
            head=head.next;
        }
        return dummy.next;
    }
}
