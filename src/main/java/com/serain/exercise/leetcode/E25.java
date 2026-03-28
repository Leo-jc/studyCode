package com.serain.exercise.leetcode;

import com.serain.parameter.ListNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  18:27
 * @Description: TODO
 * @Version: 1.0
 */
public class E25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        int count = 0;
        ListNode cur = head;
        while(cur!=null){
            count++;
            cur = cur.next;
        }
        ListNode p0=dummy;
        ListNode pre = null;
        cur=head;
        for(int i=count;i>=k;i-=k){
            for(int j=0;j<k;j++){
                ListNode nxt = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nxt;
            }
            ListNode nxt = p0.next;
            nxt.next = cur;
            p0.next = pre;
            p0 = nxt;
        }
        return dummy.next;
    }
}
