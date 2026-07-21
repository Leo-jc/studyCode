package com.serain.exercise.leetcode.linkedlist;

import com.serain.parameter.ListNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.linkedlist
 * @Author: Serain
 * @CreateTime: 2026-03-28  16:15
 * @Description: LeetCode 第 206 题
 * @Version: 1.0
 */
public class E206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
