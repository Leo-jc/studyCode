package com.serain.parameter;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.parameter
 * @Author: Serain
 * @CreateTime: 2026-03-28  18:37
 * @Description: TODO
 * @Version: 1.0
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
