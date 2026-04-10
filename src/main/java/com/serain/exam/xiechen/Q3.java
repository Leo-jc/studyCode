package com.serain.exam.xiechen;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.xiechen
 * @Author: Serain
 * @CreateTime: 2026-04-12  09:57
 * @Description: TODO
 * @Version: 1.0
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        while (T-- > 0){
            int n=sc.nextInt();
            int k=sc.nextInt();
            List<Node> list=new ArrayList<>();
            int[] x=new int[n];
            for (int i = 0; i < n; i++) {
                x[i]=sc.nextInt();
            }
            String str=sc.next();
            for (int i = 0; i < n; i++) {
                list.add(new Node(x[i],str.charAt(i)=='1'?1:-1));
            }
            list.sort((o1, o2) -> Long.compare(o1.x,o2.x));
            Queue<Long> queue=new LinkedList<>();
            int ans=0;
            for(int i=0;i<n;i++){
                Node node=list.get(i);
                if(node.dict==1) queue.add(node.x);
                else{
                    while(!queue.isEmpty()&&queue.peek()<node.x-k){
                        queue.poll();
                    }
                    ans+=queue.size();
;                }
            }
        }
    }
}

class Node{
    public long x;
    public long dict;
    public Node(long x,long dict){
        this.x=x;
        this.dict=dict;
    }
}
