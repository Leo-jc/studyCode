package com.serain.exercise.leetcode.stack;

import java.util.Stack;

import com.serain.parameter.ListNode;

public class E2130 {
    public int pairSum(ListNode head) {
        Stack <Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        int ans = 0;
        cur = head;
        while (cur != null) {
            ans = Math.max(ans, cur.val + stack.pop());
            cur = cur.next;
        }
        return ans;
    }
}
