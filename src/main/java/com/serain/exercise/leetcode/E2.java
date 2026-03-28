package com.serain.exercise.leetcode;

import com.serain.parameter.ListNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  16:31
 * @Description: TODO
 * @Version: 1.0
 */
public class E2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        int carry = 0;
        ListNode l1Cur = l1;
        ListNode l2Cur = l2;
        ListNode cur = dummyHead;
        while (l1Cur != null || l2Cur != null || carry != 0) {
            int l1Val = l1Cur != null ? l1Cur.val : 0;
            int l2Val = l2Cur != null ? l2Cur.val : 0;
            int sum = l1Val + l2Val + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1Cur != null) l1Cur = l1Cur.next;
            if (l2Cur != null) l2Cur = l2Cur.next;
        }
        return dummyHead.next;
    }
}
