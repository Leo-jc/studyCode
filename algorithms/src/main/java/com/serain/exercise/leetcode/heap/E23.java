package com.serain.exercise.leetcode.heap;

import com.serain.parameter.ListNode;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.heap
 * @Author: Serain
 * @CreateTime: 2026-03-28  18:47
 * @Description: LeetCode 第 23 题
 * @Version: 1.0
 */
public class E23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        for(ListNode node:lists){
            if(node!=null){
                pq.add(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(!pq.isEmpty()){
            cur.next = pq.poll();
            cur = cur.next;
            if(cur.next!=null){
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }
}
