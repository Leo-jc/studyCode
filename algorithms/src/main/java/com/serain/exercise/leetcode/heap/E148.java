package com.serain.exercise.leetcode.heap;

import com.serain.parameter.ListNode;

import java.util.PriorityQueue;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.heap
 * @Author: Serain
 * @CreateTime: 2026-03-28  18:44
 * @Description: LeetCode 第 148 题
 * @Version: 1.0
 */
public class E148 {
    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        while(head!=null){
            pq.add(head);
            head = head.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(!pq.isEmpty()){
            cur.next = pq.poll();
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;
    }
}
