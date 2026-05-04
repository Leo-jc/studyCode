package com.serain.exercise.leetcode;

import com.serain.parameter.ListNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-05  21:20
 * @Description: TODO
 * @Version: 1.0
 */
public class E61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null) return head;
        ListNode dummy = head;
        int num=0;
        while(dummy!=null){
            num++;
            dummy = dummy.next;
        }
        k = k%num;
        ListNode tail=head;
        while(k-->0){
            tail = tail.next;
        }
        ListNode pre = head;
        while(tail.next!=null){
            pre = pre.next;
            tail = tail.next;
        }
        tail.next = head;
        head = pre.next;
        pre.next = null;
        return head;
    }
}

