package com.serain.exam.huawei;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.huawei
 * @Author: Serain
 * @CreateTime: 2026-04-28  23:24
 * @Description: TODO
 * @Version: 1.0
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num=(int)Math.pow(2,n);
        int[] m=new int[num];
        int pos=0;
        while(sc.hasNext()){
            m[pos++]=sc.nextInt();
        }
        int len=pos;
        Queue<Node> q = new LinkedList<Node>();
        Node root=new Node(m[0]);
        q.add(root);
        pos=1;
        while(!q.isEmpty()){
            Node temp=q.poll();
            if(pos==len) break;
            Node leftNode=new Node(m[pos++]);
            temp.left=leftNode;
            q.add(leftNode);
            if(pos==len) break;
            Node rightNode=new Node(m[pos++]);
            temp.right=rightNode;
            q.add(rightNode);
        }
        dfs(root);
        q.clear();
        q.add(root);
        List<Integer> list=new ArrayList<>();
        while(!q.isEmpty()){
            Node temp=q.poll();
            list.add(temp.value);
            if(temp.left!=null) q.add(temp.left);
            if(temp.right!=null) q.add(temp.right);
        }
        for(int i=0;i<list.size()-1;i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.print(list.getLast());
    }

    private static int dfs(Node root) {
        if(root==null) return 0;
        System.out.println(root.value);
        if(root.value==-1) return 0;
        int leftValue=dfs(root.left);
        int rightValue=dfs(root.right);
        int total=leftValue+rightValue+root.value;
        root.value=total;
        return total;
    }
}

class Node{
    public int value;
    public Node left;
    public Node right;
    public Node(int value){
        this.value=value;
        this.left=null;
        this.right=null;
    }
}
