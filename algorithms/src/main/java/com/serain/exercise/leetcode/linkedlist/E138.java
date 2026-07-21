package com.serain.exercise.leetcode.linkedlist;

import com.serain.parameter.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.linkedlist
 * @Author: Serain
 * @CreateTime: 2026-03-28  18:39
 * @Description: LeetCode 第 138 题
 * @Version: 1.0
 */
public class E138 {
    Map<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }
        if(map.containsKey(head)) return map.get(head);
        Node node = new Node(head.val);
        map.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }
}
